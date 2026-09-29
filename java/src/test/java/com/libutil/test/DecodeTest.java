package com.libutil.test;

import com.libutil.BSB64;

public class DecodeTest {

  public static void main(String args[]) {
    decodeTest();
    Log.out("----");
    decodeTestFr();
    Log.out("----");
    decodeTestJa();
    Log.out("----");
    decodeTestZh();
    Log.out("----");
    decodeBytesTest();
  }

  private static void decodeTest() {
    test("np2c", 0, "abc");
    test("wsTG", 1, "abc");
    test("hYmN", 2, "abc");
    test("CxMb", 3, "abc");
    test("FiY2", 4, "abc");
    test("LExs", 5, "abc");
    test("WJjY", 6, "abc");
    test("sDGx", 7, "abc");
  }

  private static void decodeTestFr() {
    test("uY2ekTxYnpaM", 0, "Français");
    test("jOTC3IdPwtLm", 1, "Français");
    test("GcmFuQ+ehaXN", 2, "Français");
    test("MpMLcx49C0ub", 3, "Français");
    test("ZCcW5jx6FpY3", 4, "Français");
    test("yE4szXj0LC1u", 5, "Français");
    test("kZxYm/DpWFrc", 6, "Français");
    test("IzmwN+HTsLS5", 7, "Français");
  }

  private static void decodeTestJa() {
    test("HH59HH57HH55HH53HH51", 0, "あいうえお");
    test("xwMFxwMJxwMNxwMRxwMV", 1, "あいうえお");
    test("jwYKjwYSjwYajwYijwYq", 2, "あいうえお");
    test("HwwUHwwkHww0HwxEHwxU", 3, "あいうえお");
    test("PhgoPhhIPhhoPhiIPhio", 4, "あいうえお");
    test("fDBQfDCQfDDQfDARfDBR", 5, "あいうえお");
    test("+GCg+GAh+GCh+GAi+GCi", 6, "あいうえお");
    test("8cBB8cBC8cBD8cBE8cBF", 7, "あいうえお");
  }

  private static void decodeTestZh() {
    test("GnJxF1BS", 0, "华语");
    test("yxsd0V9b", 1, "华语");
    test("lzY6o762", 2, "华语");
    test("L2x0R31t", 3, "华语");
    test("Xtjojvra", 4, "华语");
    test("vLHRHfW1", 5, "华语");
    test("eWOjOutr", 6, "华语");
    test("8sZHdNfW", 7, "华语");
  }

  private static void decodeBytesTest() {
    byte[] exp = { (byte) 0x61, (byte) 0x62, (byte) 0x63 };

    test("np2c", 0, exp);
    test("wsTG", 1, exp);
    test("hYmN", 2, exp);
    test("CxMb", 3, exp);
    test("FiY2", 4, exp);
    test("LExs", 5, exp);
    test("WJjY", 6, exp);
    test("sDGx", 7, exp);
  }

  private static void test(String s, int n, String expected) {
    String r = BSB64.decodeToString(s, n);
    assertEquals("b64=\"" + s + "\" n=" + n, expected, r);
  }

  private static void test(String s, int n, byte[] expected) {
    byte[] r = BSB64.decode(s, n);
    assertEquals("b64=\"" + s + "\" n=" + n, expected, r);
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
