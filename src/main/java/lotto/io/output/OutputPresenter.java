package lotto.io.output;

import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoResult;

public interface OutputPresenter {
    void printPurchased(List<Lotto> tickets);
    void printStatistics(LottoResult result, double yield);
}
