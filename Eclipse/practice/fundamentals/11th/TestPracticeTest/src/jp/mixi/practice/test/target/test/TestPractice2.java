package jp.mixi.practice.test.target.test;

import android.test.AndroidTestCase;

import jp.mixi.practice.test.target.TestTarget2;

public class TestPractice2 extends AndroidTestCase {
    public void testValidLength() throws Exception {
        TestTarget2 target = new TestTarget2();
        assertFalse(target.isValidLength(""));
        assertTrue(target.isValidLength("h"));
        assertTrue(target.isValidLength("hogehoge"));
        assertTrue(target.isValidLength("hogehoge12"));
        assertFalse(target.isValidLength("hogehoge123"));
    }

    public void testFormatTextCount() throws Exception {
        TestTarget2 target = new TestTarget2();
        assertEquals("0 / 10", target.formatTextCount(0, 10));
        assertEquals("1 / 10", target.formatTextCount(1, 10));
        assertEquals("10 / 10", target.formatTextCount(10, 10));
        assertEquals("11 / 10", target.formatTextCount(11, 10));
    }
}