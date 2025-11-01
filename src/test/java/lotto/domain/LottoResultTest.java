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