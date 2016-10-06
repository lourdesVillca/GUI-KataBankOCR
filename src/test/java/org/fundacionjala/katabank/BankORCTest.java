package org.fundacionjala.katabank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link BankORC}.
 */
public class BankORCTest {

    /**
     * Method to parse all numbers.
     */
    @Test
    public void testParseAllNumbers() {
        final StringBuilder number = new StringBuilder();
        number.append("    _  _     _  _  _  _  _ ");
        number.append("  | _| _||_||_ |_   ||_||_|");
        number.append("  ||_  _|  | _||_|  ||_| _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("123456789", result);
    }

    /**
     * Method to validate the result of the Invalid parsed account number.
     */
    @Test
    public void testParseInvalidAccountNumber() {
        final StringBuilder number = new StringBuilder();
        number.append(" _  _     _  _        _  _ ");
        number.append("|_ |_ |_| _|  |  ||_||_||_ ");
        number.append("|_||_|  | _|  |  |  | _| _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("664371495 ERR", result);
    }

    /**
     * Method to validate valid account number.
     */
    @Test
    public void testValidAccountNumber() {
        final String accountNumber = "345882865";
        boolean actualResult = BankORC.validateAccountNumber(accountNumber);
        assertTrue(actualResult);
    }

    /**
     * Method to validate invalid account number.
     */
    @Test
    public void testInvalidAccountNumber() {
        final String accountNumber = "664371495";
        boolean actualResult = BankORC.validateAccountNumber(accountNumber);
        assertFalse(actualResult);
    }

    /**
     * Method to validate illegible account number.
     */
    @Test
    public void testIllegibleAccountNumber() {
        final StringBuilder number = new StringBuilder();
        number.append("    _  _  _  _  _  _     _ ");
        number.append("|_||_|| || ||_   |  |  | _ ");
        number.append("  | _||_||_||_|  |  |  | _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("49006771? ILL", result);
    }

    /**
     * Method to validate the value given a key.
     */
    @Test
    public void testGetTheKeyForANumber() {
        final String numberToValidate = " _  _||_ ";
        final int result = BankORC.getKeysForNumber(numberToValidate);
        assertEquals(2, result);
    }

    /**
     * Method to validate the generated format number for valid account number.
     */
    @Test
    public void testGenerateFormatNumberForValidAccountNumber() {
        final StringBuilder accountNumber = new StringBuilder("457508000");
        final String result = BankORC.generateFormatNumber(accountNumber);
        assertEquals("457508000", result);
    }

    /**
     * Method to validate the generated format number for invalid account number.
     */
    @Test
    public void testGenerateFormatNumberForInvalidAccountNumber() {
        final StringBuilder accountNumber = new StringBuilder("664371495");
        final String result = BankORC.generateFormatNumber(accountNumber);
        assertEquals("664371495 ERR", result);
    }

    /**
     * Method to validate the generated format number for account number with illegible characters.
     */
    @Test
    public void testGenerateFormatNumberForAccountNumberWithIllegibleCharacters() {
        final StringBuilder accountNumber = new StringBuilder("86110??36");
        final String result = BankORC.generateFormatNumber(accountNumber);
        assertEquals("86110??36 ILL", result);
    }
}
