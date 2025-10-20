package calculator.domain;

import calculator.exception.ErrorMessage;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Parser {
    private final String[] splitString;

    public Parser(String[] splitString) {
        this.splitString = splitString;
    }

    public List<BigDecimal> parseIntegers() {
        List<BigDecimal> numbers = new ArrayList<>();
        Arrays.stream(splitString).forEach(string -> {
            String replacedAll = string.replaceAll("[^0-9. \\-]", "");
            if (replacedAll.isEmpty()) replacedAll = "0";
            validateNumber(replacedAll);
            numbers.add(new BigDecimal(replacedAll.trim()));
        });
        return numbers;
    }

    private void validateNumber(String string) {
        String replacedAll = string.replaceAll("\\s+", "");
        BigDecimal value = new BigDecimal(replacedAll);
        if (value.signum() < 0) {
            throw new IllegalArgumentException(ErrorMessage.NEGATIVE_NUMBER_NOT_ALLOWED.getErrorMessage());
        }
    }
}
