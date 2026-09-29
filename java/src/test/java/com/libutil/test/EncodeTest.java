package com.libutil.test;

import com.libutil.BSB64;

public class EncodeTest {

  public static void main(String args[]) {
    encodeTest();
    Log.out("----");
    encodeTestFr();
    Log.out("----");
    encodeTestJa();
    Log.out("----");
    encodeTestZh();
    Log.out("----");
    shiftBitsTest();
    Log.out("----");
    shiftBitsTestJa();
  }

  private static void encodeTest() {
    test("abc", 0, "np2c");
    test("abc", 1, "wsTG");
    test("abc", 2, "hYmN");
    test("abc", 3, "CxMb");
    test("abc", 4, "FiY2");
    test("abc", 5, "LExs");
    test("abc", 6, "WJjY");
    test("abc", 7, "sDGx");
  }

  private static void encodeTestFr() {
    test("Français", 0, "uY2ekTxYnpaM");
    test("Français", 1, "jOTC3IdPwtLm");
    test("Français", 2, "GcmFuQ+ehaXN");
    test("Français", 3, "MpMLcx49C0ub");
    test("Français", 4, "ZCcW5jx6FpY3");
    test("Français", 5, "yE4szXj0LC1u");
    test("Français", 6, "kZxYm/DpWFrc");
    test("Français", 7, "IzmwN+HTsLS5");
  }

  private static void encodeTestJa() {
    test("あいうえお", 0, "HH59HH57HH55HH53HH51");
    test("あいうえお", 1, "xwMFxwMJxwMNxwMRxwMV");
    test("あいうえお", 2, "jwYKjwYSjwYajwYijwYq");
    test("あいうえお", 3, "HwwUHwwkHww0HwxEHwxU");
    test("あいうえお", 4, "PhgoPhhIPhhoPhiIPhio");
    test("あいうえお", 5, "fDBQfDCQfDDQfDARfDBR");
    test("あいうえお", 6, "+GCg+GAh+GCh+GAi+GCi");
    test("あいうえお", 7, "8cBB8cBC8cBD8cBE8cBF");
  }

  private static void encodeTestZh() {
    test("华语", 0, "GnJxF1BS");
    test("华语", 1, "yxsd0V9b");
    test("华语", 2, "lzY6o762");
    test("华语", 3, "L2x0R31t");
    test("华语", 4, "Xtjojvra");
    test("华语", 5, "vLHRHfW1");
    test("华语", 6, "eWOjOutr");
    test("华语", 7, "8sZHdNfW");
  }

  private static void shiftBitsTest() {
    byte[] src = { (byte) 0x61, (byte) 0x62, (byte) 0x63 };

    byte[] exp0 = { (byte) 0x9E, (byte) 0x9D, (byte) 0x9C };

    byte[] exp1 = { (byte) 0xC2, (byte) 0xC4, (byte) 0xC6 };

    test(src, 0, exp0);
    test(src, 1, exp1);
  }

  private static void shiftBitsTestJa() {
    byte[] src = { (byte) 0xE3, (byte) 0x81, (byte) 0x82, (byte) 0xE3, (byte) 0x81, (byte) 0x84, (byte) 0xE3, (byte) 0x81, (byte) 0x86 };

    byte[] exp = { (byte) 0xC7, (byte) 0x03, (byte) 0x05, (byte) 0xC7, (byte) 0x03, (byte) 0x09, (byte) 0xC7, (byte) 0x03, (byte) 0x0D };

    test(src, 1, exp);
  }

  private static void test(String s, int n, String expected) {
    String r = BSB64.encode(s, n);
    assertEquals("src=\"" + s + "\" n=" + n, expected, r);
  }

  private static void test(byte[] src, int n, byte[] expected) {
    byte[] r = BSB64.shiftBits(src, n);
    assertEquals("shiftBits n=" + n, expected, r);
  }

  public static boolean assertEquals(String message, Object expected, Object actual) {
    boolean ok = false;
    String op = "!=";
    String strExpected = "" + expected;
    String strActual = "" + actual;

    if (equals(strExpected, strActual)) {
      ok = true;
      op = "==";
    }

    Log.out("[" + (ok ? "PASS" : "FAIL") + "] " + message + ("".equals(message) ? "" : ": ") + "EXP=" + strExpected + " " + op + " ACTUAL=" + strActual);

    return ok;
  }

  public static boolean assertEquals(String message, byte[] expected, byte[] actual) {
    boolean ok = true;

    if ((expected == null) || (actual == null)) {
      ok = (expected == actual);
    } else if (expected.length != actual.length) {
      ok = false;
    } else {
      for (int i = 0; i < expected.length; i++) {
        if (expected[i] != actual[i]) {
          ok = false;
          break;
        }
      }
    }

    Log.out("[" + (ok ? "PASS" : "FAIL") + "] " + message);
    return ok;
  }

  public static boolean equals(String s1, String s2) {
    if ((s1 == null) && (s2 == null)) {
      return true;
    }
    if (s1 == null) {
      return false;
    }
    return s1.equals(s2);
  }

}
