package calculator.domain;

public class CustomDelimiter {
    private final Character delimiter;

    public CustomDelimiter(Character delimiter) {
        this.delimiter = delimiter;
    }

    public Character getDelimiter() {
        return delimiter;
    }
}
