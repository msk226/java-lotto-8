package lotto;

import lotto.app.LottoProcessor;
import lotto.io.input.InputView;
import lotto.io.output.OutputView;
import lotto.policy.random.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        new LottoProcessor(
                new InputView(),
                new OutputView(),
                new RandomNumberGenerator()
        ).run();
    }
}
