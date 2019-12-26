/*!
 * BSB64
 * Copyright 2018 Takashi Harano
 * Released under the MIT license
 * https://bsb64.com/
 */
BSB64 = {
  /**
   * Byte array to BSB64 encoded string
   */
  encode: function(arr, n) {
    var fn = BSB64.bit8.rotateLeft;
    if (n % 8 == 0) {
      fn = BSB64.bit8.invert;
    }
    var buf = [];
    for (var i = 0; i < arr.length; i++) {
      buf.push(fn(arr[i], n));
    }
    var str = BSB64.Base64.encode(buf);
    return str;
  },

  /**
   * BSB64 encoded string to Byte array
   */
  decode: function(bsb64, n) {
    var fn = BSB64.bit8.rotateRight;
    if (n % 8 == 0) {
      fn = BSB64.bit8.invert;
    }
    var buf = BSB64.Base64.decode(bsb64);
    var arr = [];
    for (var i = 0; i < buf.length; i++) {
      arr.push(fn(buf[i], n));
    }
    return arr;
  },

  /**
   * Plain text to BSB64 encoded string
   */
  encodeString: function(str, n) {
    var arr = BSB64.UTF8.toByte(str);
    return BSB64.encode(arr, n);
  },

  /**
   * BSB64 encoded string to Plain text
   */
  decodeString: function(str, n) {
    var arr = BSB64.decode(str, n);
    return BSB64.UTF8.fromByte(arr);
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

  Base64: {
    encode: function(arr) {
      var len = arr.length;
      if (len == 0) return '';
      var tbl = {64: 61, 63: 47, 62: 43};
      for (var i = 0; i < 62; i++) {
        tbl[i] = (i < 26 ? i + 65 : (i < 52 ? i + 71 : i - 4));
      }
      var str = '';
      var buf = [];
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
        if (!(((c >= 0x30) && (c <= 0x39)) ||
              ((c >= 0x41) && (c <= 0x5A)) || ((c >= 0x61) && (c <= 0x7A)) ||
               (c == 0x2B) || (c == 0x2F) || (c == 0x3D))) {
          throw new Error('Invalid char: ' + c);
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

  UTF8: {
    toByte: function(s) {
      var a = [];
      if (!s) return a;
      for (var i = 0; i < s.length; i++) {
        var c = s.charCodeAt(i);
        if (c <= 0x7F) {
          a.push(c);
        } else if (c <= 0x07FF) {
          a.push(((c >> 6) & 0x1F) | 0xC0);
          a.push((c & 0x3F) | 0x80);
        } else {
          a.push(((c >> 12) & 0x0F) | 0xE0);
          a.push(((c >> 6) & 0x3F) | 0x80);
          a.push((c & 0x3F) | 0x80);
        }
      }
      return a;
    },

    fromByte: function(a) {
      if (!a) return null;
      var s = '';
      var i, c;
      while (i = a.shift()) {
        if (i <= 0x7F) {
          s += String.fromCharCode(i);
        } else if (i <= 0xDF) {
          c = ((i & 0x1F) << 6);
          c += a.shift() & 0x3F;
          s += String.fromCharCode(c);
        } else if (i <= 0xE0) {
          c = ((a.shift() & 0x1F) << 6) | 0x800;
          c += a.shift() & 0x3F;
          s += String.fromCharCode(c);
        } else {
          c = ((i & 0x0F) << 12);
          c += (a.shift() & 0x3F) << 6;
          c += a.shift() & 0x3F;
          s += String.fromCharCode(c);
        }
      }
      return s;
    }
  }
};
