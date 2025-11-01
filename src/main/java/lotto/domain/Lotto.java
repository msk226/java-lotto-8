package lotto.domain;

import static lotto.common.constants.LottoConstants.LOTTO_COUNT;
import static lotto.common.constants.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.common.constants.LottoConstants.MIN_LOTTO_NUMBER;
import static lotto.common.exception.ExceptionMessage.DUPLICATE_LOTTO_NUMBERS;
import static lotto.common.exception.ExceptionMessage.INVALID_LOTTO_NUMBER_COUNT;
import static lotto.common.exception.ExceptionMessage.LOTTO_NUMBER_OUT_OF_RANGE;

import java.util.List;

public class Lotto {
    private final List<Integer> numbers;

    public Lotto(List<Integer> numbers) {
        validate(numbers);
        this.numbers = numbers;
    }

    public int matchCount(Lotto other) {
        return (int) numbers.stream()
                .filter(other.numbers::contains)
                .count();
    }

    public boolean contains(int number) {
        return numbers.contains(number);
    }

    public List<Integer> getNumbers() {
        return List.copyOf(numbers);
    }

    private void validate(List<Integer> numbers) {
        validateIsValidNumberCount(numbers);
        validateNoDuplicateNumbers(numbers);
        validateIsNumbersInRange(numbers);
    }

    private void validateIsValidNumberCount(List<Integer> numbers) {
        if (numbers.size() != LOTTO_COUNT) {
            throw new IllegalArgumentException(INVALID_LOTTO_NUMBER_COUNT);
        }
    }

    public void validateNoDuplicateNumbers(List<Integer> numbers) {
        long distinctCount = numbers.stream().distinct().count();
        if (distinctCount != numbers.size()) {
            throw new IllegalArgumentException(DUPLICATE_LOTTO_NUMBERS);
        }
    }

    private void validateIsNumbersInRange(List<Integer> numbers) {
        for (Integer num : numbers) {
            if (num < MIN_LOTTO_NUMBER || num > MAX_LOTTO_NUMBER) {
                throw new IllegalArgumentException(LOTTO_NUMBER_OUT_OF_RANGE);
            }
        }
    }
}
