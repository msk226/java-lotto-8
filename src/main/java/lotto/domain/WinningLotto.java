package lotto.domain;

import lotto.domain.enums.Rank;

public class WinningLotto {
    private final Lotto winning;
    private final int bonusNumber;

    public WinningLotto(Lotto winning, int bonusNumber) {
        winning.hasDuplicateBonusNumber(bonusNumber);
        this.winning = winning;
        this.bonusNumber = bonusNumber;
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.matchCount(winning);
        boolean hasBonusNumber = lotto.contains(bonusNumber);

        return Rank.of(matchCount, hasBonusNumber);
    }
}
