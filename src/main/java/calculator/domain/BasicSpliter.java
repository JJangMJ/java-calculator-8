package calculator.domain;

public enum BasicSpliter {
    COMMA(","),
    COLON(":");

    private final String spliter;

    BasicSpliter(String spliter) {
        this.spliter = spliter;
    }

    public String getSpliter() {
        return spliter;
    }
}
