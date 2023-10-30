package racingcar.domain;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.List;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import racingcar.constant.IllegalArgumentExceptionType;

class GameCarsTest {

    @Test
    void 빈_리스트로_객체_생성_실패_테스트() {
        List<Car> cars = List.of();

        assertThatThrownBy(() -> new GameCars(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.EMPTY_CARS_ERROR_MESSAGE.getMessage());
    }

    @Test
    void 중복_차량_이름으로_객체_생성_실패_테스트() {
        List<Car> cars = List.of(
                new GameCar("붕붕카"),
                new GameCar("붕붕카")
        );

        assertThatThrownBy(() -> new GameCars(cars))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IllegalArgumentExceptionType.DUPLICATED_CARS_ERROR_MESSAGE.getMessage());

    }

    @Test
    void 객체_생성_성공_테스트() {
        List<Car> cars = List.of(
                new GameCar("붕붕카"),
                new GameCar("범퍼카")
        );

        assertThatCode(() -> new GameCars(cars)).doesNotThrowAnyException();
    }
    @Test
    void 불변리스트_객체_추가_테스트() {
        Cars instance = new GameCars(
                List.of(
                        new GameCar("붕붕카"),
                        new GameCar("범퍼카")
                )
        );
        List<Car> innerGameCars = instance.getCars();

        assertThatThrownBy(() -> innerGameCars.add(new GameCar("불법카")))
                .isInstanceOf(UnsupportedOperationException.class);
    }

    // moveCars 메소드 테스트

    @Test
    void 차량이동_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    // given
                    Car win1 = new GameCar("붕붕카");
                    Car win2 = new GameCar("범퍼카");
                    Car lose = new GameCar("불법카");
                    Cars instance = new GameCars(
                            List.of(win1, win2, lose)
                    );
                    // when
                    instance.moveCars();
                    // then
                    SoftAssertions softAssertions = new SoftAssertions();
                    softAssertions.assertThat(win1.getDistance()).isEqualTo(1);
                    softAssertions.assertThat(win2.getDistance()).isEqualTo(1);
                    softAssertions.assertThat(lose.getDistance()).isEqualTo(0);
                    softAssertions.assertAll();
                },
                // 랜덤넘버 지정
                4, 4, 3
        );

    }
}