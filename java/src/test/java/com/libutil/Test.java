package com.libutil;

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
    Log.d("encode = " + BSB64.encode("abc", 0));
    Log.d("encode = " + BSB64.encode("abc", 1));
    Log.d("encode = " + BSB64.encode("abc", 2));
    Log.d("encode = " + BSB64.encode("abc", 3));
    Log.d("encode = " + BSB64.encode("abc", 4));
    Log.d("encode = " + BSB64.encode("abc", 5));
    Log.d("encode = " + BSB64.encode("abc", 6));
    Log.d("encode = " + BSB64.encode("abc", 7));
  }

  private static void test1Fr() {
    Log.d("encode = " + BSB64.encode("Français", 0));
    Log.d("encode = " + BSB64.encode("Français", 1));
    Log.d("encode = " + BSB64.encode("Français", 2));
    Log.d("encode = " + BSB64.encode("Français", 3));
    Log.d("encode = " + BSB64.encode("Français", 4));
    Log.d("encode = " + BSB64.encode("Français", 5));
    Log.d("encode = " + BSB64.encode("Français", 6));
    Log.d("encode = " + BSB64.encode("Français", 7));
  }

  private static void test1Ja() {
    Log.d("encode = " + BSB64.encode("あいうえお", 0));
    Log.d("encode = " + BSB64.encode("あいうえお", 1));
    Log.d("encode = " + BSB64.encode("あいうえお", 2));
    Log.d("encode = " + BSB64.encode("あいうえお", 3));
    Log.d("encode = " + BSB64.encode("あいうえお", 4));
    Log.d("encode = " + BSB64.encode("あいうえお", 5));
    Log.d("encode = " + BSB64.encode("あいうえお", 6));
    Log.d("encode = " + BSB64.encode("あいうえお", 7));
  }

  private static void test1Zh() {
    Log.d("encode = " + BSB64.encode("华语", 0));
    Log.d("encode = " + BSB64.encode("华语", 1));
    Log.d("encode = " + BSB64.encode("华语", 2));
    Log.d("encode = " + BSB64.encode("华语", 3));
    Log.d("encode = " + BSB64.encode("华语", 4));
    Log.d("encode = " + BSB64.encode("华语", 5));
    Log.d("encode = " + BSB64.encode("华语", 6));
    Log.d("encode = " + BSB64.encode("华语", 7));
  }

  private static void test2() {
    Log.d("decode = " + BSB64.decodeToString("np2c", 0));
    Log.d("decode = " + BSB64.decodeToString("wsTG", 1));
    Log.d("decode = " + BSB64.decodeToString("hYmN", 2));
    Log.d("decode = " + BSB64.decodeToString("CxMb", 3));
    Log.d("decode = " + BSB64.decodeToString("FiY2", 4));
    Log.d("decode = " + BSB64.decodeToString("LExs", 5));
    Log.d("decode = " + BSB64.decodeToString("WJjY", 6));
    Log.d("decode = " + BSB64.decodeToString("sDGx", 7));
  }

  private static void test2Fr() {
    Log.d("decode = " + BSB64.decodeToString("uY2ekTxYnpaM", 0));
    Log.d("decode = " + BSB64.decodeToString("jOTC3IdPwtLm", 1));
    Log.d("decode = " + BSB64.decodeToString("GcmFuQ+ehaXN", 2));
    Log.d("decode = " + BSB64.decodeToString("MpMLcx49C0ub", 3));
    Log.d("decode = " + BSB64.decodeToString("ZCcW5jx6FpY3", 4));
    Log.d("decode = " + BSB64.decodeToString("yE4szXj0LC1u", 5));
    Log.d("decode = " + BSB64.decodeToString("kZxYm/DpWFrc", 6));
    Log.d("decode = " + BSB64.decodeToString("IzmwN+HTsLS5", 7));
  }

  private static void test2Ja() {
    Log.d("decode = " + BSB64.decodeToString("HH59HH57HH55HH53HH51", 0));
    Log.d("decode = " + BSB64.decodeToString("xwMFxwMJxwMNxwMRxwMV", 1));
    Log.d("decode = " + BSB64.decodeToString("jwYKjwYSjwYajwYijwYq", 2));
    Log.d("decode = " + BSB64.decodeToString("HwwUHwwkHww0HwxEHwxU", 3));
    Log.d("decode = " + BSB64.decodeToString("PhgoPhhIPhhoPhiIPhio", 4));
    Log.d("decode = " + BSB64.decodeToString("fDBQfDCQfDDQfDARfDBR", 5));
    Log.d("decode = " + BSB64.decodeToString("+GCg+GAh+GCh+GAi+GCi", 6));
    Log.d("decode = " + BSB64.decodeToString("8cBB8cBC8cBD8cBE8cBF", 7));
  }

  private static void test2Zh() {
    Log.d("decode = " + BSB64.decodeToString("GnJxF1BS", 0));
    Log.d("decode = " + BSB64.decodeToString("yxsd0V9b", 1));
    Log.d("decode = " + BSB64.decodeToString("lzY6o762", 2));
    Log.d("decode = " + BSB64.decodeToString("L2x0R31t", 3));
    Log.d("decode = " + BSB64.decodeToString("Xtjojvra", 4));
    Log.d("decode = " + BSB64.decodeToString("vLHRHfW1", 5));
    Log.d("decode = " + BSB64.decodeToString("eWOjOutr", 6));
    Log.d("decode = " + BSB64.decodeToString("8sZHdNfW", 7));
  }

  private static void test3() {
    ArrayList<Byte> arr = new ArrayList<>();
    arr.add(Byte.valueOf((byte) 97));
    arr.add(Byte.valueOf((byte) 98));
    arr.add(Byte.valueOf((byte) 99));

    byte[] bsb64 = BSB64.transform(toByteArray(arr), 1);
    Log.d(bsb64);

    bsb64 = BSB64.transform(toByteArray(arr), 0);
    Log.d(bsb64);
  }

  private static void test4() {
    byte[] decoded = BSB64.decode("np2c", 0);
    Log.d("decode =");
    Log.d(decoded);

    decoded = BSB64.decode("wsTG", 1);
    Log.d("decode =");
    Log.d(decoded);
  }

  public static void test5() {
    ArrayList<Byte> arr = new ArrayList<>();
    arr.add(Byte.valueOf((byte) (227)));
    arr.add(Byte.valueOf((byte) (129)));
    arr.add(Byte.valueOf((byte) (130)));
    arr.add(Byte.valueOf((byte) (227)));
    arr.add(Byte.valueOf((byte) (129)));
    arr.add(Byte.valueOf((byte) (132)));
    arr.add(Byte.valueOf((byte) (227)));
    arr.add(Byte.valueOf((byte) (129)));
    arr.add(Byte.valueOf((byte) (134)));

    Log.d(BSB64.transform(toByteArray(arr), 1));
  }

  private static void timeTest() {
    long start = Log.timeStart();
    BSB64.encode("abc", 1);
    Log.d("encode time=");
    Log.timeEnd(start);

    start = Log.timeStart();
    BSB64.decodeToString("wsTG", 1);
    Log.d("decodeToString time=");
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
