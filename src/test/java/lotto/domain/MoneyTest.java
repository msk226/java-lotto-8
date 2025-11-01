package lotto.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Money(1500));
        assertThrows(IllegalArgumentException.class, () -> new Money(999));
        assertThrows(IllegalArgumentException.class, () -> new Money(-1000));
    }

    @Test
    void 구매_금액에_따라_티켓_수를_계산할_수_있다() {
        Money money = new Money(5000);
        int ticketCount = money.ticketCount();
        assertThat(ticketCount).isEqualTo(5);
    }
}
