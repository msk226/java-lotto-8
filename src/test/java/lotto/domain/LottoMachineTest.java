package lotto.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;
import lotto.policy.fake.FixedNumberGenerator;
import org.junit.jupiter.api.Test;

class LottoMachineTest {
    @Test
    void 로또_번호_생성기는_6개의_번호를_반환한다() {
        LottoMachine lottoMachine = new LottoMachine(new FixedNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
        Lotto ticket = lottoMachine.create();
        assertEquals(6, ticket.matchCount(new Lotto(List.of(1, 2, 3, 4, 5, 6))));
    }

    @Test
    void 로또_번호_생성기는_원하는_갯수_만큼의_로또_번호를_생성할_수_있다() {
        LottoMachine lottoMachine = new LottoMachine(new FixedNumberGenerator(List.of(1, 2, 3, 4, 5, 6)));
        List<Lotto> tickets = lottoMachine.createMany(3);
        assertEquals(3, tickets.size());
    }

}