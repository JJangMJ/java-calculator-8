package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.domain.Spliter;
import calculator.exception.ErrorMessage;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.ArrayList;
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
        Spliter spliter = new Spliter(originalString);
        CustomDelimiter delimiter = null;
        if (hasCustomDelimiter(originalString)) {
            char customDelimiter = originalString.charAt(INDEX_OF_CUSTOM_DELIMITER);
            validateCustomDelimiter(customDelimiter);
            delimiter = new CustomDelimiter(customDelimiter);
            return spliter.split(delimiter);
        }
        return spliter.split(delimiter);
    }

    private boolean hasCustomDelimiter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }

    private List<Integer> parseIntegers(String[] splitString) {
        List<Integer> numbers = new ArrayList<>();
        for (String string : splitString) {
            String replacedAll = string.replaceAll("[^0-9-]", "");
            if (replacedAll.isEmpty()) replacedAll = "0";
            validateNumber(replacedAll);
            numbers.add(Integer.parseInt(replacedAll));
        }
        return numbers;
    }

    private int sum(List<Integer> numbers) {
        return numbers.stream().reduce(Integer::sum).get();
    }

    private void validateCustomDelimiter(Character customDelimiter) {
        if (Character.isDigit(customDelimiter) || Character.isWhitespace(customDelimiter)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOM_DELIMITER.getErrorMessage());
        }
    }

    private void validateNumber(String string) {
        if (Integer.parseInt(string) < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED.getErrorMessage());
        }
    }
}
