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
        String[] splitString = splitString(originalString);
        List<Integer> numbers = parseIntegers(splitString);
        int totalSum = sum(numbers);
        outputView.printResult(totalSum);
    }

    private String[] splitString(String originalString) {
        if (hasCustomDelimiter(originalString)) {
            String substring = originalString.substring(BEGIN_INDEX_OF_SUBSTRING);
            char customDelimiter = originalString.charAt(INDEX_OF_CUSTOM_DELIMITER);
            return splitStringByDelimiters(substring, customDelimiter);
        }
        return splitStringByDelimiters(originalString, null);
    }

    private boolean hasCustomDelimiter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }

    private String[] splitStringByDelimiters(String string, Character customDelimiter) {
        if (customDelimiter == null) {
            return string.split(BasicDelimiter.COMMA.getDelimiter() + "|"
                    + BasicDelimiter.COLON.getDelimiter());
        }
        return string.split(BasicDelimiter.COMMA.getDelimiter() + "|"
                + BasicDelimiter.COLON.getDelimiter() + "|"
                + CustomDelimiter.createDelimiter(customDelimiter));
    }

    private List<Integer> parseIntegers(String[] splitString) {
        List<Integer> numbers = new ArrayList<>();
        for (String s : splitString) {
            String replacedAll = s.replaceAll("[^0-9]", "");
            if (replacedAll.isEmpty()) {
                replacedAll = "0";
            }
            numbers.add(Integer.parseInt(replacedAll));
        }
        return numbers;
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum).get();
    }
}
