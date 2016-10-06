package org.fundacionjala.katabank;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to format the number.
 */
public final class BankORC {

    private static final Map<Integer, String> NUMBER_DATA = new HashMap<Integer, String>();
    private static final String ILLEGIBLE_CHARACTER = "?";
    private static final String ILL = " ILL";
    private static final String ERR = " ERR";

    private static final int ONE = 1;

    private static final int TWO = 2;

    private static final int THREE = 3;

    private static final int FOUR = 4;

    private static final int FIVE = 5;

    private static final int SIX = 6;

    private static final int SEVEN = 7;

    private static final int EIGHT = 8;

    private static final int NINE = 9;

    private static final int ZERO = 0;


    static {
        NUMBER_DATA.put(ONE, "     |  |");
        NUMBER_DATA.put(TWO, " _  _||_ ");
        NUMBER_DATA.put(THREE, " _  _| _|");
        NUMBER_DATA.put(FOUR, "   |_|  |");
        NUMBER_DATA.put(FIVE, " _ |_  _|");
        NUMBER_DATA.put(SIX, " _ |_ |_|");
        NUMBER_DATA.put(SEVEN, " _   |  |");
        NUMBER_DATA.put(EIGHT, " _ |_||_|");
        NUMBER_DATA.put(NINE, " _ |_| _|");
        NUMBER_DATA.put(ZERO, " _ | ||_|");
    }

    /**
     * Private Constructor.
     */
    private BankORC() {

    }

    /**
     * Method to Parse the number read from the file.
     *
     * @param number the string number.
     * @return the parse number.
     */
    public static String parseNumber(final String number) {
        final int intervalSpace = 3;
        final int endIndexFirstLine = 27;
        final int endIndexSecondLine = 54;
        String numberLine1 = number.substring(0, endIndexFirstLine);
        String numberLine2 = number.substring(endIndexFirstLine, endIndexSecondLine);
        String numberLine3 = number.substring(endIndexSecondLine);
        StringBuilder formatNumber = new StringBuilder();
        for (int i = 0; i < numberLine1.length() - 2; i += intervalSpace) {
            String result = numberLine1.substring(i, i + intervalSpace) + numberLine2.substring(i, i + intervalSpace)
                    + numberLine3.substring(i, i + intervalSpace);
            if (getKeysForNumber(result) == -1) {
                formatNumber = formatNumber.append(ILLEGIBLE_CHARACTER);
            } else {
                formatNumber = formatNumber.append(getKeysForNumber(result));
            }
        }
        return generateFormatNumber(formatNumber);
    }

    /**
     * Method to generate the correct format for a parsed account number.
     *
     * @param formatNumber parsed account number.
     * @return the parsed number account.
     */
    public static String generateFormatNumber(final StringBuilder formatNumber) {
        if (String.valueOf(formatNumber).contains(ILLEGIBLE_CHARACTER)) {
            formatNumber.append(ILL);
        } else if (!validateAccountNumber(formatNumber.toString())) {
            formatNumber.append(ERR);
        }
        return formatNumber.toString();
    }

    /**
     * Method that search the key for a given Value.
     *
     * @param value the number expression in String format.
     * @return the key of the value if the given value doesn't exist return -1.
     */
    public static int getKeysForNumber(final String value) {
        return NUMBER_DATA.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Validate if the given account is valid.
     *
     * @param accountNumber account number to validate.
     * @return true if the account is valid.
     */
    public static boolean validateAccountNumber(final String accountNumber) {
        int checksum = 0;
        final int checksumDivider = 11;
        for (int i = 0; i < accountNumber.length(); i++) {
            checksum += Character.getNumericValue(accountNumber.charAt(i)) * (accountNumber.length() - i);
        }
        return checksum % checksumDivider == 0;
    }
}
