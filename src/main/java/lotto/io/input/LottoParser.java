package lotto.io.input;

import static lotto.common.exception.ExceptionMessage.EMPTY_INPUT;
import static lotto.common.exception.ExceptionMessage.ONLY_NUMBERS_ALLOWED;

import java.util.Arrays;
import java.util.List;

public abstract class LottoParser {
    private LottoParser() {
    }

    public static List<Integer> parseNumbersByComma(String input) {
        if (input == null || input.isBlank()) {
            throw new IllegalArgumentException(EMPTY_INPUT);
        }

        try {
            return Arrays.stream(input.split(","))
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