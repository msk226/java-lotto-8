package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.enums.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class WinningLottoTest {
    private static Stream<Arguments> provideLottoCases() {
        return Stream.of(
                of(List.of(1, 2, 3, 4, 5, 7), Rank.SECOND),
                of(List.of(1, 2, 3, 4, 5, 6), Rank.FIRST),
                of(List.of(1, 2, 3, 4, 10, 11), Rank.FOURTH),
                of(List.of(1, 2, 3, 20, 21, 22), Rank.FIFTH),
                of(List.of(1, 2, 10, 20, 30, 40), Rank.MISS)
        );
    }

    @Test
    void 당첨_로또를_생성할_수_있다() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
    }

    @Test
    void 보너스_번호가_로또_번호와_겹치면_예외가_발생한다() {
        assertThatThrownBy(() -> new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 6))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @ParameterizedTest(name = "{index} ⇒ myNumbers={0}, expectedRank={1}")
    @MethodSource("provideLottoCases")
    void 로또_번호를_통해_내_로또가_몇_개_일치하는지_확인할_수_있다(
            List<Integer> myNumbers, Rank expectedRank
    ) {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);

        Rank match = winningLotto.match(new Lotto(myNumbers));

        assertThat(match).isEqualTo(expectedRank);
    }

}