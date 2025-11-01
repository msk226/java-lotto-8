package lotto.domain;

import static lotto.domain.enums.Rank.FIRST;
import static lotto.domain.enums.Rank.SECOND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.params.provider.Arguments.of;

import java.util.List;
import java.util.stream.Stream;
import lotto.domain.enums.Rank;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class LottoResultTest {
    private static Stream<Arguments> provideLottoResultCases() {
        return Stream.of(
                of(List.of(FIRST, SECOND, SECOND), 2_060_000_000L),
                of(List.of(SECOND, SECOND), 60_000_000L),
                of(List.of(FIRST), 2_000_000_000L),
                of(List.of(), 0L)
        );
    }

    @Test
    void 로또_결과를_생성할_수_있다() {
        LottoResult lottoResult = new LottoResult(List.of(FIRST, SECOND));
        assertNotNull(lottoResult);
    }

    @Test
    void 등수별_집계와_수익률을_집계할_수_있다() {
        LottoResult result = new LottoResult(List.of(
                Rank.FIFTH, Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.MISS
        ));
        assertThat(result.countOf(Rank.FIFTH)).isEqualTo(2);
        assertThat(result.countOf(Rank.FOURTH)).isEqualTo(1);
        assertThat(result.countOf(Rank.THIRD)).isEqualTo(1);
        assertThat(result.countOf(Rank.SECOND)).isEqualTo(1);

        long prize = result.totalPrize(); // 2*5천 + 5만 + 150만 + 3천만
        assertThat(prize).isEqualTo(31_560_000);

        double yield = result.yield(new Money(10_000));
        assertThat(Math.round(yield)).isEqualTo(Math.round(315_600.0)); // 퍼센트
    }

    @ParameterizedTest(name = "{index} ⇒ ranks={0}, expectedPrize={1}")
    @MethodSource("provideLottoResultCases")
    void 로또_당첨_금액을_계산할_수_있다(
            List<Rank> ranks, long expectedPrize
    ) {
        LottoResult lottoResult = new LottoResult(ranks);
        long totalPrize = lottoResult.totalPrize();

        assertThat(totalPrize).isEqualTo(expectedPrize);
    }

}