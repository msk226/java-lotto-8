package lotto.domain;

public class Money {
    private static final int UNIT = 1000;
    private final int amount;

    public Money(int amount) {
        validateIsAmountInThousands(amount);
        this.amount = amount;
    }

    private static void validateIsAmountInThousands(int amount) {
        if (amount < UNIT || amount % UNIT != 0) {
            throw new IllegalArgumentException("[ERROR] 금액은 1000원 단위여야 합니다.");
        }
    }

    public int ticketCount() {
        return (amount / UNIT);
    }

    public int amount() {
        return amount;
    }
}
