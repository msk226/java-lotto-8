package lotto.domain.enums;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class RankTest {
    @Test
    void 여섯개_일치면_1등() {
        assertThat(Rank.of(6, false)).isEqualTo(Rank.FIRST);
        assertThat(Rank.of(6, true)).isEqualTo(Rank.FIRST);
    }

    @Test
    void 다섯개_보너스일치면_2등() {
        assertThat(Rank.of(5, true)).isEqualTo(Rank.SECOND);
    }

    @Test
    void 다섯개_보너스불일치면_3등() {
        assertThat(Rank.of(5, false)).isEqualTo(Rank.THIRD);
    }

    @Test
    void 네개면_4등() {
        assertThat(Rank.of(4, false)).isEqualTo(Rank.FOURTH);
    }

    @Test
    void 세개면_5등() {
        assertThat(Rank.of(3, false)).isEqualTo(Rank.FIFTH);
    }

    @Test
    void 그외는_꽝() {
        assertThat(Rank.of(2, false)).isEqualTo(Rank.MISS);
    }
}
