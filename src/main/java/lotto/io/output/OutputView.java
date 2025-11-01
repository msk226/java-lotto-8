package lotto.io.output;

import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.enums.Rank;

public class OutputView {
    public void printPurchased(List<Lotto> tickets) {
        System.out.println(tickets.size() + "개를 구매했습니다.");
        for (Lotto t : tickets) {
            System.out.println(t.numbers());
        }
    }

    public void printStatistics(LottoResult result, double yield) {
        System.out.println("당첨 통계");
        System.out.println("---");
        Map<Rank, Integer> m = result.view();

        System.out.println("3개 일치 (5,000원) - " + m.get(Rank.FIFTH) + "개");
        System.out.println("4개 일치 (50,000원) - " + m.get(Rank.FOURTH) + "개");
        System.out.println("5개 일치 (1,500,000원) - " + m.get(Rank.THIRD) + "개");
        System.out.println("5개 일치, 보너스 볼 일치 (30,000,000원) - " + m.get(Rank.SECOND) + "개");
        System.out.println("6개 일치 (2,000,000,000원) - " + m.get(Rank.FIRST) + "개");

        System.out.printf("총 수익률은 %.1f%%입니다.%n", yield);
    }
}