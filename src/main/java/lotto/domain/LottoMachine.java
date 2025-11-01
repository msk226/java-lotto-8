package lotto.domain;

import java.util.List;
import java.util.stream.IntStream;
import lotto.policy.NumberGenerator;

public class LottoMachine {
    private final NumberGenerator generator;

    public LottoMachine(NumberGenerator generator) {
        this.generator = generator;
    }

    public Lotto create() {
        return new Lotto(generator.generate());
    }

    public List<Lotto> createMany(int count) {
        return IntStream.range(0, count)
                .mapToObj(i -> create())
                .toList();
    }
}
