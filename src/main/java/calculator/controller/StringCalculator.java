package calculator.controller;

import calculator.domain.BasicDelimiter;
import calculator.domain.CustomDelimiter;
import calculator.view.InputView;
import calculator.view.OutputView;
import java.util.ArrayList;
import java.util.List;

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
        String string = inputView.readString();
        List<Integer> numbers = new ArrayList<>();

        if (hasCustomSpliter(string)) {
            String substring = string.substring(BEGIN_INDEX_OF_SUBSTRING);
            String[] splitString = splitSubString(substring, string);
            parseInteger(splitString, numbers);
        }
    }

    private boolean hasCustomSpliter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }

    private static String[] splitSubString(String substring, String string) {
        return substring.split(BasicDelimiter.COMMA.getDelimiter() + "|"
                + BasicDelimiter.COLON.getDelimiter() + "|"
                + CustomDelimiter.createDelimiter(string.charAt(INDEX_OF_CUSTOM_DELIMITER)));
    }

    private void parseInteger(String[] splitString, List<Integer> numbers) {
        for (String s : splitString) {
            numbers.add(Integer.parseInt(s));
        }
    }
}
