/*!
 * BSB64
 * Copyright 2018 Takashi Harano
 * Released under the MIT License
 * https://libutil.com/bsb64/
 */
var bsb64 = {
  /**
   * Encodes a byte array or string to a BSB64 string.
   *
   * Strings are converted to UTF-8 before BSB64 encoding.
   * If n is 0, the bits are inverted instead of rotated.
   *
   * @param {number[]|string} src Source byte array or string.
   * @param {number} n Number of bits to rotate (0-7).
   * @returns {string} BSB64 encoded string.
   */
  encode: function(src, n) {
    if (typeof src == 'string') src = bsb64.utf8.toByteArray(src);
    var fn = bsb64.bit8.rotateLeft;
    if (n % 8 == 0) {
      fn = bsb64.bit8.invert;
    }
    var buf = [];
    for (var i = 0; i < src.length; i++) {
      buf.push(fn(src[i], n));
    }
    var str = bsb64.base64.encode(buf);
    return str;
  },

  /**
   * Decodes a BSB64 string to a byte array.
   *
   * If n is 0, the bits are inverted instead of rotated.
   *
   * @param {string} src BSB64 encoded string.
   * @param {number} n Number of bits used for encoding (0-7).
   * @returns {number[]} Decoded byte array.
   */
  decode: function(src, n) {
    var fn = bsb64.bit8.rotateRight;
    if (n % 8 == 0) {
      fn = bsb64.bit8.invert;
    }
    var buf = bsb64.base64.decode(src);
    var arr = [];
    for (var i = 0; i < buf.length; i++) {
      arr.push(fn(buf[i], n));
    }
    return arr;
  },

  /**
   * Decodes a BSB64 string to a string.
   *
   * The decoded data is interpreted as UTF-8.
   * If n is 0, the bits are inverted instead of rotated.
   *
   * @param {string} src BSB64 encoded string.
   * @param {number} n Number of bits used for encoding (0-7).
   * @returns {string} Decoded string.
   */
  decodeToString: function(src, n) {
    var arr = bsb64.decode(src, n);
    return bsb64.utf8.fromByteArray(arr);
  },

  bit8: {
    rotateLeft: function(v, n) {
      n = n % 8;
      return ((v << n) | (v >> (8 - n))) & 255;
    },

    rotateRight: function(v, n) {
      n = n % 8;
      return ((v >> n) | (v << (8 - n))) & 255;
    },

    invert: function(v) {
      return (~v) & 255;
    }
  },

  base64: {
    encode: function(arr) {
      var len = arr.length;
      if (len == 0) return '';
      var tbl = {64: 61, 63: 47, 62: 43};
      for (var i = 0; i < 62; i++) {
        tbl[i] = (i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4));
      }
      var str = '';
      for (i = 0; i < len; i += 3) {
        str += String.fromCharCode(
          tbl[arr[i] >>> 2],
          tbl[(arr[i] & 3) << 4 | arr[i + 1] >>> 4],
          tbl[(i + 1) < len ? (arr[i + 1] & 15) << 2 | arr[i + 2] >>> 6 : 64],
          tbl[(i + 2) < len ? (arr[i + 2] & 63) : 64]
        );
      }
      return str;
    },

    decode: function(str) {
      var arr = [];
      if (str.length == 0) return arr;
      for (var i = 0; i < str.length; i++) {
        var c = str.charCodeAt(i);
        if (!(((c >= 0x30) && (c <= 0x39)) || ((c >= 0x41) && (c <= 0x5A)) || ((c >= 0x61) && (c <= 0x7A)) || (c == 0x2B) || (c == 0x2F) || (c == 0x3D))) {
          throw new Error('invalid b64 char: 0x' + c.toString(16).toUpperCase() + ' at ' + i);
        }
      }
      var tbl = {61: 64, 47: 63, 43: 62};
      for (i = 0; i < 62; i++) {
        tbl[i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4)] = i;
      }
      var buf = [];
      for (i = 0; i < str.length; i += 4) {
        for (var j = 0; j < 4; j++) {
          buf[j] = tbl[str.charCodeAt(i + j) || 0];
        }
        arr.push(
          buf[0] << 2 | (buf[1] & 63) >>> 4,
          (buf[1] & 15) << 4 | (buf[2] & 63) >>> 2,
          (buf[2] & 3) << 6 | buf[3] & 63
        );
      }
      if (buf[3] == 64) {
        arr.pop();
        if (buf[2] == 64) {
          arr.pop();
        }
      }
      return arr;
    }
  },

  utf8: {
    toByteArray: function(s) {
      var a = [];
      if (!s) return a;
      var chs = s.match(/[\uD800-\uDBFF][\uDC00-\uDFFF]|[\s\S]/g) || [];
      for (var i = 0; i < chs.length; i++) {
        var ch = chs[i];
        var c = ch.charCodeAt(0);
        if (c <= 0x7F) {
          a.push(c);
        } else {
          var e = encodeURIComponent(ch);
          var w = e.split('%');
          for (var j = 1; j < w.length; j++) {
            a.push(('0x' + w[j]) | 0);
          }
        }
      }
      return a;
    },

    fromByteArray: function(b) {
      var e = '';
      for (var i = 0; i < b.length; i++) {
        e += '%' + bsb64.toHex(b[i]);
      }
      return decodeURIComponent(e);
    }
  },

  toHex: function(v) {
    var hex = parseInt(v).toString(16).toUpperCase();
    if (hex.length < 2) {
      hex = '0' + hex;
    }
    return hex;
  }
};
