package lotto;

import lotto.app.LottoProcessor;
import lotto.io.input.ConsoleInputReader;
import lotto.io.output.ConsoleOutputPresenter;
import lotto.policy.random.RandomNumberGenerator;

public class Application {
    public static void main(String[] args) {
        new LottoProcessor(
                new ConsoleInputReader(),
                new ConsoleOutputPresenter(),
                new RandomNumberGenerator()
        ).run();
    }
}
