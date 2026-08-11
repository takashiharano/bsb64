/**
 * The MIT License
 *
 * Copyright 2019 Takashi Harano
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
package com.libutil;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

/**
 * Provides methods for encoding and decoding data using the BSB64 encoding
 * scheme.
 *
 * @see <a href="https://libutil.com/bsb64/">BSB64</a>
 */
public class BSB64 {
  /**
   * The default charset used to convert between strings and byte arrays.
   */
  public static final String DEFAULT_CHARSET = "UTF-8";

  /**
   * Encodes the specified byte array using the BSB64 encoding scheme.
   *
   * @param src
   *          the byte array to encode
   * @param n
   *          the shift value from 0 to 7
   * @return the BSB64 encoded string
   */
  public static String encode(byte[] src, int n) {
    byte[] buf = transform(src, n);
    String encoded = Base64.getEncoder().encodeToString(buf);
    return encoded;
  }

  /**
   * Encodes the specified string using the BSB64 encoding scheme and the default
   * charset.
   *
   * @param src
   *          the string to encode
   * @param n
   *          the shift value from 0 to 7
   * @return the BSB64 encoded string
   */
  public static String encode(String src, int n) {
    return encode(src, n, DEFAULT_CHARSET);
  }

  /**
   * Encodes the specified string using the BSB64 encoding scheme and the
   * specified charset.
   *
   * @param src
   *          the string to encode
   * @param n
   *          the shift value from 0 to 7
   * @param charsetName
   *          the charset used to convert the string to bytes
   * @return the BSB64 encoded string
   */
  public static String encode(String src, int n, String charsetName) {
    String encoded;
    try {
      byte[] srcBytes = src.getBytes(charsetName);
      encoded = BSB64.encode(srcBytes, n);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    return encoded;
  }

  /**
   * Decodes the specified BSB64 encoded string into a newly allocated byte array.
   *
   * @param src
   *          the BSB64 encoded string to decode
   * @param n
   *          the shift value from 0 to 7
   * @return a newly allocated byte array containing the decoded bytes
   */
  public static byte[] decode(String src, int n) {
    byte[] buf = Base64.getDecoder().decode(src);
    return reverseTransform(buf, n);
  }

  /**
   * Decodes the specified BSB64 encoded string into the original string using the
   * default charset.
   *
   * @param src
   *          the BSB64 encoded string to decode
   * @param n
   *          the shift value from 0 to 7
   * @return the decoded string
   */
  public static String decodeToString(String src, int n) {
    return decodeToString(src, n, DEFAULT_CHARSET);
  }

  /**
   * Decodes the specified BSB64 encoded string into the original string using the
   * specified charset.
   *
   * @param src
   *          the BSB64 encoded string to decode
   * @param n
   *          the shift value from 0 to 7
   * @param charsetName
   *          the charset used to convert the decoded bytes to a string
   * @return the decoded string
   */
  public static String decodeToString(String src, int n, String charsetName) {
    String str;
    try {
      byte[] decoded = BSB64.decode(src, n);
      str = new String(decoded, charsetName);
    } catch (UnsupportedEncodingException e) {
      throw new RuntimeException(e);
    }
    return str;
  }

  /**
   * Applies the forward bit transformation used by the BSB64 encoding scheme to
   * the specified byte array.
   *
   * <p>
   * Each byte is rotated to the left by {@code n} bit positions. If {@code n} is
   * 0, all bits in the byte are inverted instead.
   * </p>
   *
   * @param src
   *          the byte array to transform
   * @param n
   *          the shift value from 0 to 7
   * @return a newly allocated byte array containing the transformed bytes
   */
  public static byte[] transform(byte[] src, int n) {
    n = n % 8;
    byte[] buf = new byte[src.length];
    for (int i = 0; i < src.length; i++) {
      byte b;
      if (n == 0) {
        b = invert(src[i]);
      } else {
        b = rotateLeft(src[i], n);
      }
      buf[i] = b;
    }
    return buf;
  }

  /**
   * Applies the reverse bit transformation used by the BSB64 encoding scheme to
   * the specified byte array.
   *
   * <p>
   * Each byte is rotated to the right by {@code n} bit positions. If {@code n} is
   * 0, all bits in the byte are inverted instead.
   * </p>
   *
   * @param src
   *          the byte array to reverse-transform
   * @param n
   *          the shift value from 0 to 7
   * @return a newly allocated byte array containing the reverse-transformed bytes
   */
  public static byte[] reverseTransform(byte[] src, int n) {
    n = n % 8;
    byte[] buf = new byte[src.length];

    for (int i = 0; i < src.length; i++) {
      byte b;
      if (n == 0) {
        b = invert(src[i]);
      } else {
        b = rotateRight(src[i], n);
      }
      buf[i] = b;
    }

    return buf;
  }

  private static byte rotateLeft(byte v, int n) {
    int i = ((int) v) & 255;
    byte b = (byte) (((i << n) | (i >>> (8 - n))) & 255);
    return b;
  }

  private static byte rotateRight(byte v, int n) {
    int i = ((int) v) & 255;
    byte b = (byte) (((i >>> n) | (i << ((8 - n)))) & 255);
    return b;
  }

  private static byte invert(byte v) {
    byte b = (byte) ((~v) & 255);
    return b;
  }

}
