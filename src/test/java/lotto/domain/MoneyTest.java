package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class MoneyTest {
    @Test
    void 금액이_1000원_단위가_아니면_예외가_발생한다() {
        assertThrows(IllegalArgumentException.class, () -> new Money(1500));
        assertThrows(IllegalArgumentException.class, () -> new Money(999));
        assertThrows(IllegalArgumentException.class, () -> new Money(-1000));
    }
}