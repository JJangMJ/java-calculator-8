package calculator.domain;

import java.util.regex.Pattern;

public class Spliter {
    private final static int BEGIN_INDEX_OF_SUBSTRING = 5;
    private static final String COMMA = Pattern.quote(BasicDelimiter.COMMA.getDelimiter());
    private static final String COLON = Pattern.quote(BasicDelimiter.COLON.getDelimiter());

    private final String string;
    private CustomDelimiter customDelimiter;

    public Spliter(String string) {
        this.string = string;
    }

    public String[] split(CustomDelimiter delimiter) {
        this.customDelimiter = delimiter;
        if (customDelimiter != null) {
            String substring = string.substring(BEGIN_INDEX_OF_SUBSTRING);
            String custom = Pattern.quote(customDelimiter.getDelimiter());
            return substring.split(COMMA + "|" + COLON + "|" + custom);
        }
        return string.split(COMMA + "|" + COLON);
    }
}
