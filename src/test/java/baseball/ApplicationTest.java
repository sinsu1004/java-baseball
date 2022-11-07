package baseball;

import baseball.controller.BaseballCountController;
import baseball.model.ComputerNums;
import baseball.model.UserNums;
import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.List;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {

    @Test
    void 게임종료_후_재시작() {
        assertRandomNumberInRangeTest(() -> {
            run("246", "135", "1", "597", "589", "2");
            assertThat(output()).contains("낫싱", "3스트라이크", "1볼 1스트라이크", "3스트라이크", "게임 종료");
        }, 1, 3, 5, 5, 8, 9);

    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> assertThatThrownBy(() -> runException("1234")).isInstanceOf(
            IllegalArgumentException.class));
    }

    @Test
    @DisplayName("랜덤함수 개수가 맞게 생성됬는지 테스트")
    void 랜덤함수_테스트() {
        int num = 3;
        ComputerNums randomnum = new ComputerNums(num);
        assertThat(randomnum.getNumbers().size()).isEqualTo(num);

    }

    @Nested
    class 사용자_입력_검증_테스트 {

        @Test
        void 사용자_입력_검증_길이가다른_예외() {
            assertSimpleTest(
                () -> assertThatThrownBy(() -> new UserNums("1233")).isInstanceOf(
                    IllegalArgumentException.class));
        }

        @Test
        void 사용자_입력_검증_서로다른수_예외() {
            assertSimpleTest(
                () -> assertThatThrownBy(() -> new UserNums("111")).isInstanceOf(
                    IllegalArgumentException.class));
        }

        @Test
        void 사용자_입력_검증_숫자가아닌수_예외() {
            assertSimpleTest(
                () -> assertThatThrownBy(() -> new UserNums("ㅁㅁㅁ")).isInstanceOf(
                    IllegalArgumentException.class));
            assertSimpleTest(
                () -> assertThatThrownBy(() -> new UserNums("aaa")).isInstanceOf(
                    IllegalArgumentException.class));
        }


    }

    @Nested
    class 입력받은수_저장된수_비교_검증후_볼_스트라이크_개수_테스트 {

        @Test
        void 볼_테스트() {
            List<Integer> usernums = List.of(1, 2, 3);
            List<Integer> computernums = List.of(2, 3, 1);
            BaseballCountController baseballCountController = new BaseballCountController(computernums, usernums);
            assertThat(baseballCountController.getBallcount()).isEqualTo(3);

            List<Integer> usernums2 = List.of(1, 2, 3);
            List<Integer> computernums2 = List.of(1, 2, 3);
            BaseballCountController baseballCountController2 = new BaseballCountController(computernums2, usernums2);
            assertThat(baseballCountController2.getBallcount()).isEqualTo(0);

           /* assertRandomNumberInRangeTest(() -> {
                run("312", "123", "1", "645", "456", "2");
                assertThat(output()).contains("3볼", "3스트라이크", "3볼", "3스트라이크", "게임 종료");
            }, 1, 2, 3, 4, 5, 6);*/
        }

        @Test
        void 스트라이크_테스트() {
            List<Integer> usernums = List.of(1, 2, 3);
            List<Integer> computernums = List.of(2, 3, 1);
            BaseballCountController baseballCountController = new BaseballCountController(computernums, usernums);
            assertThat(baseballCountController.getStrikecount()).isEqualTo(0);

            List<Integer> usernums2 = List.of(1, 2, 3);
            List<Integer> computernums2 = List.of(1, 2, 3);
            BaseballCountController baseballCountController2 = new BaseballCountController(computernums2, usernums2);
            assertThat(baseballCountController2.getStrikecount()).isEqualTo(3);

            assertRandomNumberInRangeTest(() -> {
                run("124", "123", "1", "476", "456", "2");
                assertThat(output()).contains("2스트라이크", "3스트라이크", "2스트라이크", "3스트라이크", "게임 종료");
            }, 1, 2, 3, 4, 5, 6);

        }


    }


    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
