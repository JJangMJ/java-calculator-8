package calculator.domain;

import calculator.exception.ErrorMessage;

public class CustomDelimiter {
    private final char delimiter;

    public CustomDelimiter(char delimiter) {
        validateCustomDelimiter(delimiter);
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return String.valueOf(delimiter);
    }

    private void validateCustomDelimiter(Character customDelimiter) {
        if (Character.isDigit(customDelimiter) || Character.isWhitespace(customDelimiter)) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_CUSTOM_DELIMITER.getErrorMessage());
        }
    }
}
