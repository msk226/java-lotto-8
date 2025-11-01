package lotto.domain;

import static lotto.domain.enums.Rank.FIRST;
import static lotto.domain.enums.Rank.SECOND;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class LottoResultTest {

    @Test
    void 로또_결과를_생성할_수_있다() {
        LottoResult lottoResult = new LottoResult(List.of(FIRST, SECOND));
        assertNotNull(lottoResult);
    }

}