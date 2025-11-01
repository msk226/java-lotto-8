package lotto.domain;

import static lotto.common.exception.ExceptionMessage.MONEY_NOT_MULTIPLE_OF_1000;

public class Money {
    private static final int ZERO = 0;
    private static final int UNIT = 1000;
    private final int amount;

    public Money(int amount) {
        validateIsAmountInThousands(amount);
        this.amount = amount;
    }

    public int ticketCount() {
        return (amount / UNIT);
    }

    public int amount() {
        return amount;
    }

    private void validateIsAmountInThousands(int amount) {
        if (amount < UNIT || amount % UNIT != ZERO) {
            throw new IllegalArgumentException(MONEY_NOT_MULTIPLE_OF_1000);
        }
    }
}
