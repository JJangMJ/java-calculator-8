package calculator.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.junit.jupiter.api.Test;

public class ParserTest {
    @Test
    void 문자와_숫자_섞인_문자열에서_숫자만_추출되어_정수로_파싱된다() {
        Parser parser = new Parser(new String[]{"nn1", "a2$", "3$$"});
        List<Integer> numbers = parser.parseIntegers();

        assertThat(numbers).contains(1, 2, 3);
    }

    @Test
    void 숫자가_하나도_없으면_0으로_치환된다() {
        Parser parser = new Parser(new String[]{"", "abc", "$$"});
        List<Integer> numbers = parser.parseIntegers();

        assertThat(numbers).contains(0, 0, 0);
    }

    @Test
    void 문자열_내_여러_숫자는_연결되어_하나의_정수로_해석된다() {
        Parser parser = new Parser(new String[]{"qwer1er3", "dk0dk1"});
        List<Integer> numbers = parser.parseIntegers();

        assertThat(numbers).contains(13, 1);
    }

    @Test
    void 음수가_하나라도_포함되면_예외() {
        Parser p1 = new Parser(new String[]{"-1", "2"});
        assertThatThrownBy(p1::parseIntegers)
                .isInstanceOf(IllegalArgumentException.class);

        Parser p2 = new Parser(new String[]{"a-3b", "2"});
        assertThatThrownBy(p2::parseIntegers)
                .isInstanceOf(IllegalArgumentException.class);
    }
}
