#------------------------------------------------------------------------------
# BSB64 (Bit Shifted Base64)
# Copyright 2019 Takashi Harano
# Released under the MIT License
# https://libutil.com/bsb64/
#------------------------------------------------------------------------------
import base64

DEFAULT_ENCODING = 'utf-8'

#------------------------------------------------------------------------------
def encode(src, n, encoding=DEFAULT_ENCODING):
    if isinstance(src, str):
        src = src.encode(encoding)

    n = n % 8
    buf = []
    for i in range(len(src)):
        val = src[i]
        if n == 0:
            v = ~val & 255
        else:
            v = bit_rotate_left(val, n)
        buf.append(v)

    b = bytearray(buf)
    encoded = base64.b64encode(b)
    s = encoded.decode('ascii')
    return s

#------------------------------------------------------------------------------
def decode(src, n):
    b64decoded = base64.b64decode(src)

    n = n % 8
    buf = []
    for i in range(len(b64decoded)):
        val = b64decoded[i]
        if n == 0:
            v = ~val & 255
        else:
            v = bit_rotate_right(val, n)
        buf.append(v)

    return bytes(buf)

def decode_to_string(src, n, encoding=DEFAULT_ENCODING):
    b = decode(src, n)
    s = b.decode(encoding)
    return s

#------------------------------------------------------------------------------
def bit_rotate_left(v, n):
    return ((v << n) | (v >> (8 - n))) & 255

def bit_rotate_right(v, n):
    return ((v >> n) | (v << (8 - n))) & 255

if __name__ == '__main__':
    print(__file__)
