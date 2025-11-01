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

    @Test
    void 기본_반올림() {
        LottoResult result = new LottoResult(List.of(
                Rank.FIFTH, Rank.FIFTH, Rank.FOURTH, Rank.THIRD, Rank.SECOND, Rank.MISS
        ));
        double yield = result.yield(new Money(9000));
        assertThat(yield).isEqualTo(350666.67);
    }

    @Test
    void 경계값_올림() {
        LottoResult result = new LottoResult(List.of(
                Rank.FIFTH, Rank.FIFTH, Rank.FIFTH
        ));
        double yield = result.yield(new Money(5000));
        assertThat(yield).isEqualTo(300.00);
    }

    @Test
    void 경계값_내림() {
        LottoResult result = new LottoResult(List.of(
                Rank.FOURTH, Rank.FOURTH
        ));
        double yield = result.yield(new Money(39000));
        assertThat(yield).isEqualTo(256.41);
    }

    @Test
    void 수익률_0퍼센트() {
        LottoResult result = new LottoResult(List.of(
                Rank.MISS, Rank.MISS, Rank.MISS
        ));
        double yield = result.yield(new Money(3000));
        assertThat(yield).isEqualTo(0.00);
    }

    @Test
    void 수익률_정확히_100() {
        LottoResult result = new LottoResult(List.of(
                Rank.FIFTH, Rank.FIFTH
        ));
        double yield = result.yield(new Money(10000));
        assertThat(yield).isEqualTo(100.00);
    }

    @Test
    void 매우_작은_수익률() {
        LottoResult result = new LottoResult(List.of(
                Rank.FIFTH // 5,000원
        ));
        double yield = result.yield(new Money(1200000));
        assertThat(yield).isEqualTo(0.42);
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
