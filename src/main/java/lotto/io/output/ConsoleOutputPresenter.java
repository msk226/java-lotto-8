package lotto.io.output;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.enums.Rank;

public class ConsoleOutputPresenter implements OutputPresenter {
    public static final int DEFAULT_VALUE = 0;
    private static final String MESSAGE_PURCHASED = "%d개를 구매했습니다.";
    private static final String MESSAGE_STATISTICS_HEADER = "당첨 통계";
    private static final String MESSAGE_DIVIDER = "---";
    private static final String MESSAGE_YIELD_FORMAT = "총 수익률은 %.1f%%입니다.%n";
    private static final String MATCH_3 = "3개 일치 (5,000원) - %d개";
    private static final String MATCH_4 = "4개 일치 (50,000원) - %d개";
    private static final String MATCH_5 = "5개 일치 (1,500,000원) - %d개";
    private static final String MATCH_5_BONUS = "5개 일치, 보너스 볼 일치 (30,000,000원) - %d개";
    private static final String MATCH_6 = "6개 일치 (2,000,000,000원) - %d개";

    public void printPurchased(List<Lotto> tickets) {
        System.out.printf((MESSAGE_PURCHASED) + "%n", tickets.size());
        for (Lotto t : tickets) {
            System.out.println(t.numbers());
        }
    }

    public void printStatistics(LottoResult result, double yield) {
        System.out.println(MESSAGE_STATISTICS_HEADER);
        System.out.println(MESSAGE_DIVIDER);

        Map<Rank, Integer> m = result.view();
        System.out.printf((MATCH_3) + "%n", m.getOrDefault(Rank.FIFTH, DEFAULT_VALUE));
        System.out.printf((MATCH_4) + "%n", m.getOrDefault(Rank.FOURTH, DEFAULT_VALUE));
        System.out.printf((MATCH_5) + "%n", m.getOrDefault(Rank.THIRD, DEFAULT_VALUE));
        System.out.printf((MATCH_5_BONUS) + "%n", m.getOrDefault(Rank.SECOND, DEFAULT_VALUE));
        System.out.printf((MATCH_6) + "%n", m.getOrDefault(Rank.FIRST, DEFAULT_VALUE));

        System.out.printf(MESSAGE_YIELD_FORMAT, yield);
    }
}