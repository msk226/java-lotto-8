package lotto.domain;

import lotto.domain.enums.Rank;

public class WinningLotto {
    private final Lotto winning;
    private final int bonusNumber;

    public WinningLotto(Lotto winning, int bonusNumber) {
        validateBonusNumberInRange(bonusNumber);
        validateWinningLottoHasDuplicateBonusNumber(winning, bonusNumber);
        this.winning = winning;
        this.bonusNumber = bonusNumber;
    }

    public void validateBonusNumberInRange(int bonusNumber) {
        if (bonusNumber < Lotto.MIN_LOTTO_NUMBER || bonusNumber > Lotto.MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    "[ERROR] 보너스 번호는 " + Lotto.MIN_LOTTO_NUMBER + "부터 " + Lotto.MAX_LOTTO_NUMBER + " 사이의 숫자여야 합니다.");
        }
    }

    public void validateWinningLottoHasDuplicateBonusNumber(Lotto winning, int bonusNumber) {
        if (winning.contains(bonusNumber)) {
            throw new IllegalArgumentException("[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.");
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.matchCount(winning);
        boolean hasBonusNumber = lotto.contains(bonusNumber);

        return Rank.of(matchCount, hasBonusNumber);
    }
}
