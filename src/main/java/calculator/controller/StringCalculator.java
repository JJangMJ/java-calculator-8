package calculator.controller;

import calculator.domain.BasicDelimiter;
import calculator.domain.CustomDelimiter;
import calculator.exception.ErrorMessage;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

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
            validateCustomDelimiter(customDelimiter);
            return splitStringByDelimiters(substring, customDelimiter);
        }
        return splitStringByDelimiters(originalString, null);
    }

    private boolean hasCustomDelimiter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }

    private String[] splitStringByDelimiters(String string, Character customDelimiter) {
        String comma = Pattern.quote(BasicDelimiter.COMMA.getDelimiter());
        String colon = Pattern.quote(BasicDelimiter.COLON.getDelimiter());
        String custom = Pattern.quote(String.valueOf(CustomDelimiter.getDelimiter(customDelimiter)));

        if (customDelimiter == null) {
            return string.split(comma + "|" + colon);
        }
        return string.split(comma + "|" + colon + "|" + custom);
    }

    private List<Integer> parseIntegers(String[] splitString) {
        List<Integer> numbers = new ArrayList<>();
        for (String string : splitString) {
            validateNumber(string);
            String replacedAll = string.replaceAll("[^0-9]", "");
            if (replacedAll.isEmpty()) replacedAll = "0";
            int number = Integer.parseInt(replacedAll);
            numbers.add(number);
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
