package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.enums.Rank;

public class LottoResult {
    private static final int DEFAULT_VALUE = 0;
    private static final int ONE = 1;
    private static final int PERCENTAGE = 100; // 수익률 % 변환용
    private static final int ROUND_FACTOR = 100; // 소수점 둘째 자리 반올림용
    private static final double ROUND_DIVISOR = 100.0;
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public LottoResult(List<Rank> ranks) {
        initialize();
        update(ranks);
    }

    public long totalPrize() {
        return counts.entrySet().stream()
                .mapToLong(e -> (long) e.getKey().prize() * e.getValue())
                .sum();
    }

    public int countOf(Rank rank) {
        return counts.get(rank);
    }

    public double yield(Money spent) {
        double raw = (double) totalPrize() / spent.amount() * PERCENTAGE;
        return Math.round(raw * ROUND_FACTOR) / ROUND_DIVISOR;
    }

    public Map<Rank, Integer> view() {
        return Map.copyOf(counts);
    }

    private void initialize() {
        for (Rank r : Rank.values()) {
            counts.put(r, DEFAULT_VALUE);
        }
    }

    private void update(List<Rank> ranks) {
        for (Rank r : ranks) {
            counts.put(r, counts.get(r) + ONE);
        }
    }
}
