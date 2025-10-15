package calculator.domain;

import calculator.exception.ErrorMessage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private final String[] splitString;

    public Parser(String[] splitString) {
        this.splitString = splitString;
    }

    public List<Integer> parseIntegers() {
        List<Integer> numbers = new ArrayList<>();
        Arrays.stream(splitString).forEach(string -> {
            String replacedAll = string.replaceAll("[^0-9-]", "");
            if (replacedAll.isEmpty()) replacedAll = "0";
            validateNumber(replacedAll);
            numbers.add(Integer.parseInt(replacedAll));
        });
        return numbers;
    }

    private void validateNumber(String string) {
        if (Integer.parseInt(string) < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED.getErrorMessage());
        }
    }
}
