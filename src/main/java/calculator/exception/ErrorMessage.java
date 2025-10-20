package calculator.exception;

public enum ErrorMessage {
    INVALID_CUSTOM_DELIMITER("구분자는 숫자나 공백이 될 수 없습니다."),
    NEGATIVE_NUMBER_NOT_ALLOWED("음수는 허용되지 않습니다."),
    DOT_NOT_ALLOWED("'.'은 소수점 표기와 충돌하므로 구분자로 사용할 수 없습니다."),;

    private final String errorMessage;

    ErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return errorMessage;
    }
}
