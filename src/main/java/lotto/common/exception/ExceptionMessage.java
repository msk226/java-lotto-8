package lotto.common.exception;

public final class ExceptionMessage {
    // domain
    public static final String INVALID_LOTTO_NUMBER_COUNT = "[ERROR] 로또 번호는 6개여야 합니다.";
    public static final String DUPLICATE_LOTTO_NUMBERS = "[ERROR] 로또 번호에 중복된 숫자가 있을 수 없습니다.";
    public static final String LOTTO_NUMBER_OUT_OF_RANGE = "[ERROR] 로또 번호는 1부터 45 사이의 숫자여야 합니다.";
    public static final String MONEY_NOT_MULTIPLE_OF_1000 = "[ERROR] 금액은 1000원 단위여야 합니다.";

    public static final String BONUS_NUMBER_CANNOT_DUPLICATE = "[ERROR] 보너스 번호는 당첨 번호와 중복될 수 없습니다.";
    public static final String BONUS_NUMBER_OUT_OF_RANGE = "[ERROR] 보너스 번호는 %d부터 %d 사이의 숫자여야 합니다.";


    // io
    public static final String EMPTY_INPUT = "[ERROR] 입력값이 비어있습니다.";
    public static final String ONLY_NUMBERS_ALLOWED = "[ERROR] 숫자만 입력할 수 있습니다.";

}
