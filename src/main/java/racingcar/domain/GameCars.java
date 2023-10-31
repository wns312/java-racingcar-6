package racingcar.domain;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import racingcar.constant.IllegalArgumentExceptionType;

public final class GameCars implements Cars {
    private final List<Car> cars;

    public GameCars(List<Car> cars) {
        this.cars = cars;
        validateCarsAmount();
        validateDuplicatedCarNames();
    }

    @Override
    public List<Car> getCars() {
        return Collections.unmodifiableList(cars);
    }

    private Set<String> getCarNameSet() {
        List<String> carNames = cars.stream().map(Car::getName).toList();

        return Set.copyOf(carNames);
    }

    private void validateCarsAmount() {
        if (cars.isEmpty()) {
            throw IllegalArgumentExceptionType.EMPTY_CARS_ERROR_MESSAGE.getException();
        }
    }

    private void validateDuplicatedCarNames() {
        Set<String> carNameSet = getCarNameSet();
        if (carNameSet.size() != cars.size()) {
            throw IllegalArgumentExceptionType.DUPLICATED_CARS_ERROR_MESSAGE.getException();
        }
    }

    public void moveCars() {
        cars.forEach(Car::attemptToMove);
    }
}
