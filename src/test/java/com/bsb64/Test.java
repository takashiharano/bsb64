package com.bsb64;

import java.util.ArrayList;

public class Test {
  public static void main(String args[]) {
    test1();
    test1Fr();
    test1Ja();
    test1Zh();
    test2();
    test2Fr();
    test2Ja();
    test2Zh();
    test3();
    test4();
    test5();
    timeTest();
  }

  private static void test1() {
    Log.d("encode = " + BSB64.encodeString("abc", 0));
    Log.d("encode = " + BSB64.encodeString("abc", 1));
    Log.d("encode = " + BSB64.encodeString("abc", 2));
    Log.d("encode = " + BSB64.encodeString("abc", 3));
    Log.d("encode = " + BSB64.encodeString("abc", 4));
    Log.d("encode = " + BSB64.encodeString("abc", 5));
    Log.d("encode = " + BSB64.encodeString("abc", 6));
    Log.d("encode = " + BSB64.encodeString("abc", 7));
  }

  private static void test1Fr() {
    Log.d("encode = " + BSB64.encodeString("Français", 0));
    Log.d("encode = " + BSB64.encodeString("Français", 1));
    Log.d("encode = " + BSB64.encodeString("Français", 2));
    Log.d("encode = " + BSB64.encodeString("Français", 3));
    Log.d("encode = " + BSB64.encodeString("Français", 4));
    Log.d("encode = " + BSB64.encodeString("Français", 5));
    Log.d("encode = " + BSB64.encodeString("Français", 6));
    Log.d("encode = " + BSB64.encodeString("Français", 7));
  }

  private static void test1Ja() {
    Log.d("encode = " + BSB64.encodeString("あいうえお", 0));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 1));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 2));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 3));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 4));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 5));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 6));
    Log.d("encode = " + BSB64.encodeString("あいうえお", 7));
  }

  private static void test1Zh() {
    Log.d("encode = " + BSB64.encodeString("华语", 0));
    Log.d("encode = " + BSB64.encodeString("华语", 1));
    Log.d("encode = " + BSB64.encodeString("华语", 2));
    Log.d("encode = " + BSB64.encodeString("华语", 3));
    Log.d("encode = " + BSB64.encodeString("华语", 4));
    Log.d("encode = " + BSB64.encodeString("华语", 5));
    Log.d("encode = " + BSB64.encodeString("华语", 6));
    Log.d("encode = " + BSB64.encodeString("华语", 7));
  }

  private static void test2() {
    Log.d("decode = " + BSB64.decodeString("np2c", 0));
    Log.d("decode = " + BSB64.decodeString("wsTG", 1));
    Log.d("decode = " + BSB64.decodeString("hYmN", 2));
    Log.d("decode = " + BSB64.decodeString("CxMb", 3));
    Log.d("decode = " + BSB64.decodeString("FiY2", 4));
    Log.d("decode = " + BSB64.decodeString("LExs", 5));
    Log.d("decode = " + BSB64.decodeString("WJjY", 6));
    Log.d("decode = " + BSB64.decodeString("sDGx", 7));
  }

  private static void test2Fr() {
    Log.d("decode = " + BSB64.decodeString("uY2ekTxYnpaM", 0));
    Log.d("decode = " + BSB64.decodeString("jOTC3IdPwtLm", 1));
    Log.d("decode = " + BSB64.decodeString("GcmFuQ+ehaXN", 2));
    Log.d("decode = " + BSB64.decodeString("MpMLcx49C0ub", 3));
    Log.d("decode = " + BSB64.decodeString("ZCcW5jx6FpY3", 4));
    Log.d("decode = " + BSB64.decodeString("yE4szXj0LC1u", 5));
    Log.d("decode = " + BSB64.decodeString("kZxYm/DpWFrc", 6));
    Log.d("decode = " + BSB64.decodeString("IzmwN+HTsLS5", 7));
  }

  private static void test2Ja() {
    Log.d("decode = " + BSB64.decodeString("HH59HH57HH55HH53HH51", 0));
    Log.d("decode = " + BSB64.decodeString("xwMFxwMJxwMNxwMRxwMV", 1));
    Log.d("decode = " + BSB64.decodeString("jwYKjwYSjwYajwYijwYq", 2));
    Log.d("decode = " + BSB64.decodeString("HwwUHwwkHww0HwxEHwxU", 3));
    Log.d("decode = " + BSB64.decodeString("PhgoPhhIPhhoPhiIPhio", 4));
    Log.d("decode = " + BSB64.decodeString("fDBQfDCQfDDQfDARfDBR", 5));
    Log.d("decode = " + BSB64.decodeString("+GCg+GAh+GCh+GAi+GCi", 6));
    Log.d("decode = " + BSB64.decodeString("8cBB8cBC8cBD8cBE8cBF", 7));
  }

  private static void test2Zh() {
    Log.d("decode = " + BSB64.decodeString("GnJxF1BS", 0));
    Log.d("decode = " + BSB64.decodeString("yxsd0V9b", 1));
    Log.d("decode = " + BSB64.decodeString("lzY6o762", 2));
    Log.d("decode = " + BSB64.decodeString("L2x0R31t", 3));
    Log.d("decode = " + BSB64.decodeString("Xtjojvra", 4));
    Log.d("decode = " + BSB64.decodeString("vLHRHfW1", 5));
    Log.d("decode = " + BSB64.decodeString("eWOjOutr", 6));
    Log.d("decode = " + BSB64.decodeString("8sZHdNfW", 7));
  }

  private static void test3() {
    ArrayList<Byte> arr = new ArrayList<>();
    arr.add(new Byte((byte) 97));
    arr.add(new Byte((byte) 98));
    arr.add(new Byte((byte) 99));

    byte[] bsb64 = BSB64.encode(toByteArray(arr), 1);
    Log.d(bsb64);

    bsb64 = BSB64.encode(toByteArray(arr), 0);
    Log.d(bsb64);
  }

  private static void test4() {
    byte[] decoded = BSB64.decode("np2c", 0);
    Log.d("decode = " + decoded);
    Log.d(decoded);

    decoded = BSB64.decode("wsTG", 1);
    Log.d("decode = " + decoded);
    Log.d(decoded);
  }

  public static void test5() {
    ArrayList<Byte> arr = new ArrayList<>();
    arr.add(new Byte((byte) (227)));
    arr.add(new Byte((byte) (129)));
    arr.add(new Byte((byte) (130)));
    arr.add(new Byte((byte) (227)));
    arr.add(new Byte((byte) (129)));
    arr.add(new Byte((byte) (132)));
    arr.add(new Byte((byte) (227)));
    arr.add(new Byte((byte) (129)));
    arr.add(new Byte((byte) (134)));

    Log.d(BSB64.encode(toByteArray(arr), 1));
  }

  private static void timeTest() {
    long start = Log.timeStart();
    BSB64.encodeString("abc", 1);
    Log.d("encodeString time=");
    Log.timeEnd(start);

    start = Log.timeStart();
    BSB64.decodeString("wsTG", 1);
    Log.d("decodeString time=");
    Log.timeEnd(start);
  }

  private static byte[] toByteArray(ArrayList<Byte> list) {
    byte[] arr = new byte[list.size()];
    for (int i = 0; i < arr.length; i++) {
      arr[i] = list.get(i);
    }
    return arr;
  }
}
