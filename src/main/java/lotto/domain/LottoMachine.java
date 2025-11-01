package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.policy.NumberGenerator;

public class LottoMachine {
    public static final int START_INCLUSIVE = 0;
    private final NumberGenerator generator;

    public LottoMachine(NumberGenerator generator) {
        this.generator = generator;
    }

    public Lotto create() {
        return new Lotto(generator.generate());
    }

    public List<Lotto> createMany(int count) {
        return IntStream.range(START_INCLUSIVE, count)
                .mapToObj(i -> create())
                .toList();
    }
}
