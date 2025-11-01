package lotto.app;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.enums.Rank;
import lotto.io.input.InputReader;
import lotto.io.input.LottoParser;
import lotto.io.output.OutputPresenter;
import lotto.policy.NumberGenerator;

public class LottoProcessor {
    private final InputReader input;
    private final OutputPresenter output;
    private final LottoMachine machine;

    public LottoProcessor(InputReader input, OutputPresenter output, NumberGenerator generator) {
        this.input = input;
        this.output = output;
        this.machine = new LottoMachine(generator);
    }

    static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void run() {
        Money money = readMoneyWithRetry();
        List<Lotto> tickets = purchaseTickets(money);
        output.printPurchased(tickets);

        Lotto winning = readWinningWithRetry();
        WinningLotto winningLotto = readWinningLottoWithRetry(winning);

        LottoResult result = judge(tickets, winningLotto);
        double yield = result.yield(money);

        printResult(result, yield);
    }

    private Money readMoneyWithRetry() {
        return retry(() -> {
            String raw = input.readPurchaseAmount();
            int amount = LottoParser.parseSingleInt(raw);
            return new Money(amount);
        });
    }

    private List<Lotto> purchaseTickets(Money money) {
        return machine.createMany(money.ticketCount());
    }

    private Lotto readWinningWithRetry() {
        return retry(() -> {
            String raw = input.readWinningNumbers();
            return new Lotto(LottoParser.parseNumbersByComma(raw));
        });
    }

    private WinningLotto readWinningLottoWithRetry(Lotto winning) {
        return retry(() -> {
            String raw = input.readBonusNumber();
            int bonus = LottoParser.parseSingleInt(raw);
            return new WinningLotto(winning, bonus);
        });
    }

    private LottoResult judge(List<Lotto> tickets, WinningLotto winningLotto) {
        List<Rank> ranks = tickets.stream().map(winningLotto::match).toList();
        return new LottoResult(ranks);
    }

    private void printResult(LottoResult result, double yield) {
        output.printStatistics(result, yield);
    }
}
