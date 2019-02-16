package com.bsb64;

import java.util.ArrayList;

public class B64 {

  public static String encode(ArrayList<Byte> arr) {
    int len = arr.size();
    if (len == 0) {
      return "";
    }
    char[] tbl = new char[65];
    tbl[64] = 61;
    tbl[63] = 47;
    tbl[62] = 43;
    for (int i = 0; i < 62; i++) {
      tbl[i] = (char) (i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4));
    }
    String str = "";
    for (int i = 0; i < len; i += 3) {
      int b0 = 0;
      int b1 = 0;
      int b2 = 0;
      b0 = arr.get(i) & 255;
      if ((i + 1) < len) {
        b1 = arr.get(i + 1) & 255;
      }
      if ((i + 2) < len) {
        b2 = arr.get(i + 2) & 255;
      }
      int[] codePoints = { (int) (tbl[b0 >>> 2]), (int) tbl[(b0 & 3) << 4 | b1 >>> 4],
          (int) tbl[(i + 1) < len ? (b1 & 15) << 2 | b2 >>> 6 : 64], (int) tbl[(i + 2) < len ? (b2 & 63) : 64] };
      str += new String(codePoints, 0, codePoints.length);
    }
    return str;
  }

  public static ArrayList<Byte> decode(String str) throws RuntimeException {
    ArrayList<Byte> arr = new ArrayList<>();
    if (str.length() == 0) {
      return arr;
    }
    for (int i = 0; i < str.length(); i++) {
      char c = str.charAt(i);
      if (!(((c >= 0x30) && (c <= 0x39)) || ((c >= 0x41) && (c <= 0x5A)) || ((c >= 0x61) && (c <= 0x7A)) || (c == 0x2B)
          || (c == 0x2F) || (c == 0x3D))) {
        throw new RuntimeException("Invalid char: " + c + " at " + i);
      }
    }
    char[] tbl = new char[256];
    tbl[61] = 64;
    tbl[47] = 63;
    tbl[43] = 62;
    for (char i = 0; i < 62; i++) {
      tbl[i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4)] = i;
    }
    byte[] buf = new byte[4];
    for (int i = 0; i < str.length(); i += 4) {
      for (int j = 0; j < 4; j++) {
        buf[j] = (byte) tbl[str.charAt(i + j)];
      }
      arr.add((byte) (buf[0] << 2 | (buf[1] & 63) >>> 4));
      arr.add((byte) ((buf[1] & 15) << 4 | (buf[2] & 63) >>> 2));
      arr.add((byte) ((buf[2] & 3) << 6 | buf[3] & 63));
    }
    if (buf[3] == 64) {
      arr.remove(arr.size() - 1);
      if (buf[2] == 64) {
        arr.remove(arr.size() - 1);
      }
    }
    return arr;
  }
}
