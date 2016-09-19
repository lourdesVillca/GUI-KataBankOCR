package org.fundacionjala.katabank;

import java.util.HashMap;
import java.util.Map;

/**
 * Class to format the number
 */
public class BankORC {

    private static final Map<Integer, String> numberData = new HashMap<Integer, String>();

    static {
        numberData.put(1, "     |  |");
        numberData.put(2, " _  _||_ ");
        numberData.put(3, " _  _| _|");
        numberData.put(4, "   |_|  |");
        numberData.put(5, " _ |_  _|");
        numberData.put(6, " _ |_ |_|");
        numberData.put(7, " _   |  |");
        numberData.put(8, " _ |_||_|");
        numberData.put(9, " _ |_| _|");
        numberData.put(0, " _ | ||_|");
    }

    private BankORC() {

    }

    /**
     * Method to Parse the number read from the file
     *
     * @param number the string number
     * @return the parse number
     */
    public static String parseNumber(String number) {
        String numberLine1 = number.substring(0, 27);
        String numberLine2 = number.substring(27, 54);
        String numberLine3 = number.substring(54);
        StringBuilder formatNumber = new StringBuilder();

        for (int i = 0; i < numberLine1.length() - 2; i += 3) {
            String result = numberLine1.substring(i, i + 3) + numberLine2.substring(i, i + 3) + numberLine3.substring(i, i + 3);
            formatNumber = (getKeysForNumber(result) == -1) ? formatNumber.append("?") : formatNumber.append(getKeysForNumber(result));

        }
        return formatNumber.toString();
    }

    /**
     * Method that search the key for a given Value
     *
     * @param value the number expression in String format
     * @return the key of the value if the given value doesn't exist return -1
     */
    public static int getKeysForNumber(String value) {
        return numberData.entrySet()
                .stream()
                .filter(entry -> entry.getValue().equals(value))
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(-1);
    }

    /**
     * Validate if the given account is valid
     *
     * @param accountNumber account number to validate
     * @return true if the account is valid
     */
    public static boolean validateAccountNumber(String accountNumber) {
        int checksum = 0;
        for (int i = 0; i < accountNumber.length(); i++) {
            checksum += Character.getNumericValue(accountNumber.charAt(i)) * (accountNumber.length() - i);
        }
        return checksum % 11 == 0;
    }
}
