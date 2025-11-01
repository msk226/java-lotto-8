package lotto.domain;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import lotto.domain.enums.Rank;

public class LottoResult {
    public static final int DEFAULT_VALUE = 0;
    public static final int ONE = 1;
    private final Map<Rank, Integer> counts = new EnumMap<>(Rank.class);

    public LottoResult(List<Rank> ranks) {
        initialize();
        update(ranks);
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
