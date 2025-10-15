package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.domain.Splitter;
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
        Splitter splitter = new Splitter(originalString);
        CustomDelimiter delimiter = null;
        if (hasCustomDelimiter(originalString)) {
            char customDelimiter = originalString.charAt(INDEX_OF_CUSTOM_DELIMITER);
            delimiter = new CustomDelimiter(customDelimiter);
            return splitter.split(delimiter);
        }
        return splitter.split(delimiter);
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

    private void validateNumber(String string) {
        if (Integer.parseInt(string) < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED.getErrorMessage());
        }
    }
}
