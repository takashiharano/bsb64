/**
 * The MIT License
 *
 * Copyright (c) 2019 Takashi Harano
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.bsb64;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * This class implements an encoder and a decoder of the BSB64 encoding scheme.
 * https://bsb64.com/
 */
public class BSB64 {
  /**
   * The default charset to be used to encode/decode a string.
   */
  public static final String DEFAULT_CHARSET = "UTF-8";

  /**
   * Encodes all bytes from the specified byte array into a newly-allocated byte
   * array using the BSB64 encoding scheme.
   *
   * @param src
   *          The byte array to encode
   * @param n
   *          The number of shifting
   * @return A newly-allocated byte array containing the resulting encoded bytes.
   */
  public static byte[] encode(byte[] src, int n) {
    byte[] buf = new byte[src.length];
    for (int i = 0; i < src.length; i++) {
      byte b;
      if (n % 8 == 0) {
        b = invert(src[i]);
      } else {
        b = rotateLeft(src[i], n);
      }
      buf[i] = b;
    }
    return buf;
  }

  /**
   * Encodes the specified byte array into a String using the BSB64 encoding
   * scheme.
   *
   * @param src
   *          The byte array to encode
   * @param n
   *          The number of shifting
   * @return A String containing the resulting BSB64 encoded characters
   */
  public static String encodeToString(byte[] src, int n) {
    byte[] buf = encode(src, n);
    String encoded = Base64.getEncoder().encodeToString(buf);
    return encoded;
  }

  /**
   * Encodes the specified string into a String using the BSB64 encoding scheme.
   *
   * @param src
   *          The string to encode
   * @param n
   *          The number of shifting
   * @return An encoded string
   */
  public static String encodeString(String src, int n) {
    return encodeString(src, n, DEFAULT_CHARSET);
  }

  /**
   * Encodes the specified string into a String using the BSB64 encoding scheme.
   * 
   * @param src
   *          The string to encode
   * @param n
   *          The number of shifting
   * @param charsetName
   *          The charset to be used to decode the bytes
   * @return An encoded string
   */
  public static String encodeString(String src, int n, String charsetName) {
    String endoded = null;
    try {
      byte[] srcBytes = src.getBytes(charsetName);
      endoded = BSB64.encodeToString(srcBytes, n);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    return endoded;
  }

  /**
   * Decodes a BSB64 encoded String into a newly-allocated byte array using the
   * BSB64 encoding scheme.
   *
   * @param src
   *          The string to decode
   * @param n
   *          The number of shifting
   * @return A newly-allocated byte array containing the decoded bytes.
   * @throws RuntimeException
   *           If failed to decode
   */
  public static byte[] decode(String src, int n) throws RuntimeException {
    byte[] buf = Base64.getDecoder().decode(src);
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

  /**
   * Decodes a BSB64 encoded String into an original string using the BSB64
   * encoding scheme.
   *
   * @param src
   *          The string to decode
   * @param n
   *          The number of shifting
   * @return A decoded string
   */
  public static String decodeString(String src, int n) {
    return decodeString(src, n, DEFAULT_CHARSET);
  }

  /**
   * Decodes a BSB64 encoded String into an original string using the BSB64
   * encoding scheme.
   *
   * @param src
   *          The string to decode
   * @param n
   *          The number of shifting
   * @param charsetName
   *          The charset to be used to decode
   * @return A decoded string
   */
  public static String decodeString(String src, int n, String charsetName) {
    String str = null;
    try {
      byte[] decoded = BSB64.decode(src, n);
      str = new String(decoded, charsetName);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    return str;
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
