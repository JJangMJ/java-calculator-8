package calculator.domain;

public enum BasicDelimiter {
    COMMA(","),
    COLON(":");

    private final String delimiter;

    BasicDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getDelimiter() {
        return delimiter;
    }
}
