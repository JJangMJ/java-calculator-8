package calculator.controller;

import calculator.domain.BasicDelimiter;
import calculator.domain.CustomDelimiter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.ArrayList;
import java.util.List;

public class StringCalculator {
    private final static int INDEX_OF_CUSTOM_DELIMITER = 2;
    private final static int BEGIN_INDEX_OF_SUBSTRING = 5;
    private final InputView inputView;
    private final OutputView outputView;

    public StringCalculator() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        String originalString = inputView.readString();
        String[] splitString;

        if (hasCustomDelimiter(originalString)) {
            String substring = originalString.substring(BEGIN_INDEX_OF_SUBSTRING);
            char customDelimiter = originalString.charAt(INDEX_OF_CUSTOM_DELIMITER);
            splitString = splitString(substring, customDelimiter);
        } else {
            splitString = splitString(originalString, null);
        }

        List<Integer> numbers = parseInteger(splitString);
        int totalSum = sum(numbers);
        outputView.printResult(totalSum);
    }

    private boolean hasCustomDelimiter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }

    private String[] splitString(String string, Character customDelimiter) {
        if (customDelimiter == null) {
            return string.split(BasicDelimiter.COMMA.getDelimiter() + "|"
                    + BasicDelimiter.COLON.getDelimiter());
        }
        return string.split(BasicDelimiter.COMMA.getDelimiter() + "|"
                + BasicDelimiter.COLON.getDelimiter() + "|"
                + CustomDelimiter.createDelimiter(customDelimiter));
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum).get();
    }

    private List<Integer> parseInteger(String[] splitString) {
        List<Integer> numbers = new ArrayList<>();
        for (String s : splitString) {
            numbers.add(Integer.parseInt(s.replaceAll("[^0-9]", "")));
        }
        return numbers;
    }
}
