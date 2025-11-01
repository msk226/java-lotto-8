package lotto.policy.random;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashSet;
import java.util.List;
import lotto.policy.NumberGenerator;
import org.junit.jupiter.api.Test;

class RandomNumberGeneratorTest {
    @Test
    void 생성된_로또_번호는_여섯개_중복없고_범위내에_있어야_한다() {
        NumberGenerator gen = new RandomNumberGenerator();
        List<Integer> nums = gen.generate();
        
        assertThat(nums).hasSize(6);
        assertThat(new HashSet<>(nums)).hasSize(6);
        assertThat(nums).allMatch(n -> n >= 1 && n <= 45);
        assertThat(nums).isSorted();
    }

}