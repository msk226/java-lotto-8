package lotto.policy.random;

import static lotto.common.constants.LottoConstants.LOTTO_COUNT;
import static lotto.common.constants.LottoConstants.MAX_LOTTO_NUMBER;
import static lotto.common.constants.LottoConstants.MIN_LOTTO_NUMBER;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.policy.NumberGenerator;

public class RandomNumberGenerator implements NumberGenerator {
    @Override
    public List<Integer> generate() {
        List<Integer> numbers = Randoms.pickUniqueNumbersInRange(MIN_LOTTO_NUMBER, MAX_LOTTO_NUMBER, LOTTO_COUNT);
        return numbers.stream().sorted().toList();
    }
}
