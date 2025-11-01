package lotto.domain;

import static lotto.common.constants.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.common.constants.LottoConstants.MIN_LOTTO_NUMBER;
import static lotto.common.exception.ExceptionMessage.BONUS_NUMBER_CANNOT_DUPLICATE;
import static lotto.common.exception.ExceptionMessage.BONUS_NUMBER_OUT_OF_RANGE;

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
        if (bonusNumber < MIN_LOTTO_NUMBER || bonusNumber > MAX_LOTTO_NUMBER) {
            throw new IllegalArgumentException(
                    String.format(BONUS_NUMBER_OUT_OF_RANGE, MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER));
        }
    }

    public void validateWinningLottoHasDuplicateBonusNumber(Lotto winning, int bonusNumber) {
        if (winning.contains(bonusNumber)) {
            throw new IllegalArgumentException(BONUS_NUMBER_CANNOT_DUPLICATE);
        }
    }

    public Rank match(Lotto lotto) {
        int matchCount = lotto.matchCount(winning);
        boolean hasBonusNumber = lotto.contains(bonusNumber);

        return Rank.of(matchCount, hasBonusNumber);
    }
}
