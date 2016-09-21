package org.fundacionjala.katabank;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * Test for {@link BankORC}
 */
public class BankORCTest {
    @Test
    public void test_parseAllNumbers() {
        final StringBuilder number = new StringBuilder();
        number.append("    _  _     _  _  _  _  _ ");
        number.append("  | _| _||_||_ |_   ||_||_|");
        number.append("  ||_  _|  | _||_|  ||_| _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("123456789", result);
    }
    @Test
    public void test_parseInvalidAccountNumber() {
        final StringBuilder number = new StringBuilder();
        number.append(" _  _     _  _        _  _ ");
        number.append("|_ |_ |_| _|  |  ||_||_||_ ");
        number.append("|_||_|  | _|  |  |  | _| _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("664371495 ERR", result);
    }
    @Test
    public void test_validAccountNumber() {
        final String accountNumber = "345882865";
        boolean actualResult = BankORC.validateAccountNumber(accountNumber);
        assertTrue(actualResult);
    }

    @Test
    public void test_invalidAccountNumber() {
        final String accountNumber = "664371495";
        boolean actualResult = BankORC.validateAccountNumber(accountNumber);
        assertFalse(actualResult);
    }

    @Test
    public void test_illegibleAccountNumber() {
        final StringBuilder number = new StringBuilder();
        number.append("    _  _  _  _  _  _     _ ");
        number.append("|_||_|| || ||_   |  |  | _ ");
        number.append("  | _||_||_||_|  |  |  | _|");
        String result = BankORC.parseNumber(number.toString());
        assertEquals("49006771? ILL", result);
    }

    @Test
    public void test_getTheKeyForANumber() {
        final String numberToValidate = " _  _||_ ";
        final int result = BankORC.getKeysForNumber(numberToValidate);
        assertEquals(2, result);
    }
}
