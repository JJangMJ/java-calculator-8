package calculator.controller;

import calculator.domain.CustomSpliter;
import calculator.view.InputView;
import calculator.view.OutputView;

public class StringCalculator {
    private final InputView inputView;
    private final OutputView outputView;

    public StringCalculator() {
        this.inputView = new InputView();
        this.outputView = new OutputView();
    }

    public void start() {
        String string = inputView.readString();

        if (string.startsWith("//") && (string.startsWith("\\n", 3))) {
            CustomSpliter customSpliter = new CustomSpliter(string.charAt(2));
        }
    }
}
