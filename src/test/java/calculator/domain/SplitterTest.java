package calculator.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SplitterTest {
    @Test
    void 기본_구분자_콤마와_콜론으로_분리된다() {
        Splitter splitter = new Splitter("1,2:3");
        String[] splitString = splitter.split(null);

        assertThat(splitString).contains("1", "2", "3");
    }

    @Test
    void 기본_구분자_사용시_문자와_숫자가_섞여도_그대로_분리된다() {
        Splitter splitter = new Splitter("nn1,a2$,3$$");
        String[] splitString = splitter.split(null);

        assertThat(splitString).contains("nn1", "a2$", "3$$");
    }

    @Test
    void 연속_구분자는_중간에_빈_문자열을_생성한다() {
        Splitter splitter = new Splitter("1,,2");
        String[] splitString = splitter.split(null);

        assertThat(splitString).contains("1", "", "2");
    }

    @Test
    void 공백은_분리하지_않고_그대로_남는다() {
        Splitter splitter = new Splitter(" 1 , 2 ");
        String[] splitString = splitter.split(null);

        assertThat(splitString).containsExactly(" 1 ", " 2 ");
    }

    @Test
    void 커스텀_구분자_한_글자() {
        Splitter splitter = new Splitter("//;\\n1;2,3:4");
        String[] splitString = splitter.split(new CustomDelimiter(';'));

        assertThat(splitString).contains("1", "2", "3", "4");
    }

    @Test
    void 커스텀_구분자_특수문자도_정상_동작한다() {
        Splitter splitter = new Splitter("//^\\n1^2^3,4:5");
        String[] splitString = splitter.split(new CustomDelimiter('^'));

        assertThat(splitString).contains("1", "2", "3", "4", "5");
    }

    @Test
    void 커스텀_구분자와_기본_구분자를_함께_사용할_수_있다() {
        Splitter splitter = new Splitter("//#\\n1#2,3:4#5");
        String[] splitString = splitter.split(new CustomDelimiter('#'));

        assertThat(splitString).contains("1", "2", "3", "4", "5");
    }

    @Test
    void 커스텀_구분자가_한_글자가_아니면_무시하고_기본_구분자만_적용한다() {
        Splitter splitter = new Splitter("//ab\\n1ab2,3");
        String[] splitString = splitter.split(null);

        assertThat(splitString).containsExactly("//ab\\n1ab2", "3");
    }
}
