package lotto.domain;

import java.util.List;
import org.junit.jupiter.api.Test;

class WinningLottoTest {
    @Test
    void 당첨_로또를_생성할_수_있다() {
        WinningLotto winningLotto = new WinningLotto(
                new Lotto(List.of(1, 2, 3, 4, 5, 6)), 7);
    }

}