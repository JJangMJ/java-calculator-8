package calculator.controller;

import calculator.domain.CustomDelimiter;
import calculator.view.InputView;
import calculator.view.OutputView;

public class StringCalculator {
    private final static int INDEX_OF_CUSTOM_SPLITER = 2;
    private final InputView inputView;
    private final OutputView outputView;

    public StringCalculator() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        String string = inputView.readString();

        if (hasCustomSpliter(string)) {
            CustomDelimiter customDelimiter = new CustomDelimiter(string.charAt(INDEX_OF_CUSTOM_SPLITER));
        }

    }

    private boolean hasCustomSpliter(String string) {
        return string.startsWith("//") && (string.startsWith("\\n", 3));
    }
}
