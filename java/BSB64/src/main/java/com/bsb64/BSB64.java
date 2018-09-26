package com.bsb64;

import java.util.Base64;

public class BSB64 {
  public static String encode(byte[] arr, int n) {
    byte[] buf = new byte[arr.length];
    for (int i = 0; i < arr.length; i++) {
      byte b;
      if (n % 8 == 0) {
        b = invert(arr[i]);
      } else {
        b = rotateLeft(arr[i], n);
      }
      buf[i] = b;
    }
    String str = Base64.getEncoder().encodeToString(buf);
    return str;
  }

  public static byte[] decode(String bsb64, int n) throws RuntimeException {
    byte[] buf = Base64.getDecoder().decode(bsb64);
    byte[] arr = new byte[buf.length];
    for (int i = 0; i < buf.length; i++) {
      byte b;
      if (n % 8 == 0) {
        b = invert(buf[i]);
      } else {
        b = rotateRight(buf[i], n);
      }
      arr[i] = b;
    }
    return arr;
  }

  private static byte rotateLeft(byte v, int n) {
    n = n % 8;
    int i = ((int) v) & 255;
    byte b = (byte) (((i << n) | (i >>> (8 - n))) & 255);
    return b;
  }

  private static byte rotateRight(byte v, int n) {
    n = n % 8;
    int i = ((int) v) & 255;
    byte b = (byte) (((i >>> n) | (i << ((8 - n)))) & 255);
    return b;
  }

  private static byte invert(byte v) {
    byte b = (byte) ((~v) & 255);
    return b;
  }
}
