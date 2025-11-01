package lotto.io.input;

import camp.nextstep.edu.missionutils.Console;

public class ConsoleInputReader implements InputReader {
    private static final String MESSAGE_PURCHASE_AMOUNT = "구입금액을 입력해 주세요.";
    private static final String MESSAGE_WINNING_NUMBERS = "\n당첨 번호를 입력해 주세요.";
    private static final String MESSAGE_BONUS_NUMBER = "\n보너스 번호를 입력해 주세요.";

    public String readPurchaseAmount() {
        System.out.println(MESSAGE_PURCHASE_AMOUNT);
        return Console.readLine();
    }

    public String readWinningNumbers() {
        System.out.println(MESSAGE_WINNING_NUMBERS);
        return Console.readLine();
    }

    public String readBonusNumber() {
        System.out.println(MESSAGE_BONUS_NUMBER);
        return Console.readLine();
    }
}