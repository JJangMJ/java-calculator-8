package calculator.domain;

import java.util.regex.Pattern;

public class Splitter {
    private final static int BEGIN_INDEX_OF_SUBSTRING = 5;
    private static final String COMMA = Pattern.quote(BasicDelimiter.COMMA.getDelimiter());
    private static final String COLON = Pattern.quote(BasicDelimiter.COLON.getDelimiter());
    private final String string;

    public Splitter(String string) {
        this.string = string;
    }

    public String[] split(CustomDelimiter delimiter) {
        if (delimiter != null) {
            String substring = string.substring(BEGIN_INDEX_OF_SUBSTRING);
            String custom = Pattern.quote(delimiter.getDelimiter());
            return substring.split(COMMA + "|" + COLON + "|" + custom);
        }
        return string.split(COMMA + "|" + COLON);
    }
}
