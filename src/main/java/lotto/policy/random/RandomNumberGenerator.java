package lotto.policy.random;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.List;
import lotto.policy.NumberGenerator;

public class RandomNumberGenerator implements NumberGenerator {
    public static final int START_INCLUSIVE = 1;
    public static final int END_INCLUSIVE = 45;
    public static final int COUNT = 6;

    @Override
    public List<Integer> generate() {
        return Randoms.pickUniqueNumbersInRange(START_INCLUSIVE, END_INCLUSIVE, COUNT);
    }
}
