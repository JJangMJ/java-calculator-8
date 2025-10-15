package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.domain.Parser;
import calculator.domain.Splitter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.List;

public class StringCalculator {
    private final static int INDEX_OF_CUSTOM_DELIMITER = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public StringCalculator() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        String originalString = inputView.readString();
        String[] splitString = splitString(originalString);
        List<Integer> numbers = parseIntegers(splitString);
        int totalSum = sum(numbers);
        outputView.printResult(totalSum);
    }

    private String[] splitString(String originalString) {
        Splitter splitter = new Splitter(originalString);
        CustomDelimiter delimiter = null;
        if (hasCustomDelimiter(originalString)) {
            char customDelimiter = originalString.charAt(INDEX_OF_CUSTOM_DELIMITER);
            delimiter = new CustomDelimiter(customDelimiter);
            return splitter.split(delimiter);
        }
        return splitter.split(delimiter);
    }

    private List<Integer> parseIntegers(String[] splitString) {
        Parser parser = new Parser(splitString);
        return parser.parseIntegers();
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream().mapToInt(Integer::intValue).sum();
    }

    private boolean hasCustomDelimiter(String string) {
        return string != null && string.startsWith("//") && string.indexOf("\\n") > 2;
    }
}
