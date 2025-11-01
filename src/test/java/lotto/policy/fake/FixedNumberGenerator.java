package lotto.policy.fake;

import java.util.List;
import lotto.policy.NumberGenerator;

public class FixedNumberGenerator implements NumberGenerator {
    private final List<Integer> numbers;

    public FixedNumberGenerator(List<Integer> numbers) {
        this.numbers = numbers;
    }

    @Override
    public List<Integer> generate() {
        return numbers;
    }
}
