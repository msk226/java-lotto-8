package lotto.io;

import java.util.ArrayDeque;
import java.util.Deque;
import lotto.io.input.InputReader;

public class FakeInputReader implements InputReader {
    private final Deque<String> q = new ArrayDeque<>();

    public FakeInputReader enqueue(String... inputs) {
        for (String s : inputs) {
            q.addLast(s);
        }
        return this;
    }

    @Override
    public String readPurchaseAmount() {
        return q.removeFirst();
    }

    @Override
    public String readWinningNumbers() {
        return q.removeFirst();
    }

    @Override
    public String readBonusNumber() {
        return q.removeFirst();
    }
}
