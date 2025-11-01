package lotto.app;

import java.util.List;
import java.util.function.Supplier;
import lotto.domain.Lotto;
import lotto.domain.LottoMachine;
import lotto.domain.LottoResult;
import lotto.domain.Money;
import lotto.domain.WinningLotto;
import lotto.domain.enums.Rank;
import lotto.io.input.InputView;
import lotto.io.input.LottoParser;
import lotto.io.output.OutputView;
import lotto.policy.NumberGenerator;

public class LottoProcessor {
    private final InputView input;
    private final OutputView output;
    private final LottoMachine machine;

    public LottoProcessor(InputView input, OutputView output, NumberGenerator generator) {
        this.input = input;
        this.output = output;
        this.machine = new LottoMachine(generator);
    }

    private static <T> T retry(Supplier<T> supplier) {
        while (true) {
            try {
                return supplier.get();
            } catch (IllegalArgumentException | IllegalStateException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void run() {
        // 1) 금액 입력 (해당 단계 재시도)
        Money money = retry(() -> {
            String raw = input.readPurchaseAmount();
            int amount = LottoParser.parseSingleInt(raw);
            return new Money(amount);
        });

        // 2) 자동 발행
        List<Lotto> tickets = machine.createMany(money.ticketCount());
        output.printPurchased(tickets);

        // 3) 당첨 번호 입력 (해당 단계 재시도)
        Lotto winning = retry(() -> {
            String raw = input.readWinningNumbers();
            return new Lotto(LottoParser.parseNumbersByComma(raw));
        });

        // 4) 보너스 번호 입력 + 검증 (해당 단계 재시도)
        WinningLotto winningLotto = retry(() -> {
            String raw = input.readBonusNumber();
            int bonus = LottoParser.parseSingleInt(raw);
            // 보너스 중복/범위 등은 WinningLotto 생성 시 검증됨
            return new WinningLotto(winning, bonus);
        });

        // 5) 판정/집계/출력
        List<Rank> ranks = tickets.stream().map(winningLotto::match).toList();
        LottoResult result = new LottoResult(ranks);
        double yield = result.yield(money);
        output.printStatistics(result, yield);
    }
}