package lotto.io.input;

import static lotto.common.exception.ExceptionMessage.EMPTY_INPUT;
import static lotto.common.exception.ExceptionMessage.ONLY_NUMBERS_ALLOWED;

import java.util.Arrays;
import java.util.List;

public final class LottoParser {
    private static final String LOTTO_DELIMITER = ",";

    private LottoParser() {
    }

    public static List<Integer> parseNumbersByComma(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }

        try {
            return Arrays.stream(input.split(LOTTO_DELIMITER))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .toList();
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_NUMBERS_ALLOWED);
        }
    }

    public static int parseSingleInt(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }
        try {
            return Integer.parseInt(input.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ONLY_NUMBERS_ALLOWED);
        }
    }
}