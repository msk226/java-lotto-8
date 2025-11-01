// test/java/lotto/io/FakeOutputPresenter.java
package lotto.io;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;
import lotto.domain.enums.Rank;
import lotto.io.output.OutputPresenter;

public class FakeOutputPresenter implements OutputPresenter {
    public final List<List<Integer>> printedTickets = new ArrayList<>();
    public int printedPurchasedCount;
    public LottoResult lastResult;
    public Double lastYield;

    @Override
    public void printPurchased(List<Lotto> tickets) {
        printedPurchasedCount = tickets.size();
        for (Lotto t : tickets) {
            printedTickets.add(t.getNumbers());
        }
    }

    @Override
    public void printStatistics(LottoResult result, double yield) {
        this.lastResult = result;
        this.lastYield = yield;
        Map<Rank, Integer> m = result.view();
    }
}
