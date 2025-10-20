package calculator.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import calculator.exception.ErrorMessage;
import org.junit.jupiter.api.Test;

public class CustomDelimiterTest {
    @Test
    void 숫자는_커스텀_구분자로_쓸_수_없다() {
        assertThatThrownBy(() -> new CustomDelimiter('1'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CUSTOM_DELIMITER.getErrorMessage());
    }

    @Test
    void 공백은_커스텀_구분자로_쓸_수_없다() {
        assertThatThrownBy(() -> new CustomDelimiter(' '))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.INVALID_CUSTOM_DELIMITER.getErrorMessage());
    }

    @Test
    void 소수점은_커스텀_구분자로_쓸_수_없다() {
        assertThatThrownBy(() -> new CustomDelimiter('.'))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(ErrorMessage.DOT_NOT_ALLOWED.getErrorMessage());
    }

    @Test
    void 일반_문자는_허용된다() {
        new CustomDelimiter(';');
        new CustomDelimiter('#');
    }
}
