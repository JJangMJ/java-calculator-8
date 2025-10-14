package calculator.controller;

import calculator.view.InputView;
import calculator.view.OutputView;

public class StringCalculator {
    private final InputView inputView;
    private final OutputView outputView;

    public StringCalculator(InputView inputView, OutputView outputView) {
        this.inputView = inputView;
        this.outputView = outputView;
    }

    public void start() {
        String string = inputView.readString();
    }
}
