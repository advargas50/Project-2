package util;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void isValid() {
        Date invalidDate1 = new Date("13/10/2021");
        Date invalidDate2 = new Date("2/29/2005");
        Date invalidDate3 = new Date("0/10/2004");
        Date invalidDate4 = new Date("11/31/2022");

        assertFalse("Month should be invalid", invalidDate1.isValid());
        assertFalse("Day should be invalid for February", invalidDate2.isValid());
        assertFalse("Month should be invalid (0)", invalidDate3.isValid());
        assertFalse("Day should be invalid for November", invalidDate4.isValid());

        Date validDate1 = new Date("12/31/2023");
        Date validDate2 = new Date("2/29/2020");

        assertTrue("Date should be valid", validDate1.isValid());
        assertTrue("Date should be valid (leap year)", validDate2.isValid());
    }
}