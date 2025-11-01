package lotto.app;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import lotto.domain.enums.Rank;
import lotto.io.FakeInputReader;
import lotto.io.FakeOutputPresenter;
import lotto.policy.NumberGenerator;
import lotto.policy.fake.FixedNumberGenerator;
import org.junit.jupiter.api.Test;

class LottoProcessorTest {
    @Test
    void 금액_입력이_잘못되면_재시도_한다() {
        // given
        FakeInputReader in = new FakeInputReader()
                .enqueue("abc", "8000", "1,2,3,4,5,6", "7");
        FakeOutputPresenter out = new FakeOutputPresenter();
        NumberGenerator gen = new FixedNumberGenerator(List.of(10, 11, 12, 13, 14, 15));

        // when
        new LottoProcessor(in, out, gen).run();

        // then
        assertThat(out.printedPurchasedCount).isEqualTo(8);
        assertThat(out.lastResult).isNotNull();
        assertThat(out.lastYield).isNotNull();
    }

    @Test
    void 당첨_번호가_6개가_아니면_재시도한다() {
        // given
        FakeInputReader in = new FakeInputReader()
                .enqueue("2000", "1,2,3,4,5", "1,2,3,4,5,6", "7");
        FakeOutputPresenter out = new FakeOutputPresenter();
        NumberGenerator gen = new FixedNumberGenerator(List.of(8, 9, 10, 11, 12, 13));

        // when
        new LottoProcessor(in, out, gen).run();

        // then
        assertThat(out.printedPurchasedCount).isEqualTo(2);
        assertThat(out.lastResult).isNotNull();
    }

    @Test
    void 보너스_번호_중복_시_재시도_후_정상_입력으로_진행된다() {
        // given
        FakeInputReader in = new FakeInputReader()
                .enqueue("1000", "1,2,3,4,5,6", "6", "7");
        FakeOutputPresenter out = new FakeOutputPresenter();
        NumberGenerator gen = new FixedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        // when
        new LottoProcessor(in, out, gen).run();

        // then
        int firstCnt = out.lastResult.view().getOrDefault(Rank.FIRST, 0);
        assertThat(out.printedPurchasedCount).isEqualTo(1);
        assertThat(firstCnt).isEqualTo(1);
    }

    @Test
    void 일등한_경우_검증() {
        // given
        FakeInputReader in = new FakeInputReader()
                .enqueue("1000", "1,2,3,4,5,6", "7");
        FakeOutputPresenter out = new FakeOutputPresenter();
        NumberGenerator gen = new FixedNumberGenerator(List.of(1, 2, 3, 4, 5, 6));

        // when
        new LottoProcessor(in, out, gen).run();

        // then
        int firstCnt = out.lastResult.view().getOrDefault(Rank.FIRST, 0);
        assertThat(out.printedPurchasedCount).isEqualTo(1);
        assertThat(firstCnt).isEqualTo(1);
    }

    @Test
    void 여러_장_구매_시_등수_집계_및_수익률이_계산된다() {
        // given
        FakeInputReader in = new FakeInputReader()
                .enqueue("5000", "1,2,3,4,5,6", "7");
        FakeOutputPresenter out = new FakeOutputPresenter();
        NumberGenerator gen = new FixedNumberGenerator(List.of(1, 2, 3, 4, 10, 11));

        // when
        new LottoProcessor(in, out, gen).run();

        // then
        int fourthCnt = out.lastResult.view().getOrDefault(Rank.FOURTH, 0);
        assertThat(out.printedPurchasedCount).isEqualTo(5);
        assertThat(fourthCnt).isEqualTo(5);
        assertThat(out.lastYield).isNotNull();
        assertThat(out.lastYield).isGreaterThan(0.0);
    }
}
