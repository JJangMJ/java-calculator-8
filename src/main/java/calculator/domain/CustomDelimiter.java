package calculator.domain;

public class CustomDelimiter {
    private final char delimiter;

    public CustomDelimiter(char delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return String.valueOf(delimiter);
    }
}
