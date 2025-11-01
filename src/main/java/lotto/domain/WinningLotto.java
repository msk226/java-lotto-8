package lotto.domain;

public class WinningLotto {
    private final Lotto winning;
    private final int bonusNumber;

    public WinningLotto(Lotto winning, int bonusNumber) {
        winning.hasDuplicateBonusNumber(bonusNumber);
        this.winning = winning;
        this.bonusNumber = bonusNumber;
    }
}
