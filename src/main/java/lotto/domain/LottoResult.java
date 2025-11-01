package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.enums.Rank;

public class LottoResult {
    public static final int DEFAULT_VALUE = 0;
    public static final int ONE = 1;
    public static final int PERCENTAGE = 100;
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
        return (double) totalPrize() / spent.amount() * PERCENTAGE;
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
