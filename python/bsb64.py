#------------------------------------------------------------------------------
# BSB64 (Bit Shifted Base64)
# Copyright 2019 Takashi Harano
# Released under the MIT license
# https://bsb64.com/
#------------------------------------------------------------------------------
import base64

ENCODING = 'utf-8'

#------------------------------------------------------------------------------
# Encodes a string to BSB64 encoded string
def encode_string(src, n, encoding=ENCODING):
    b = src.encode(encoding)
    return encode_to_string(b, n, encoding)

# Encodes bytes to BSB64 encoded string
def encode_to_string(src, n, encoding=ENCODING):
    encoded = encode(src, n)
    s = encoded.decode(encoding)
    return s

# Encodes bytes into a new bytes using the BSB64 encoding scheme
def encode(src, n):
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
    return encoded

#------------------------------------------------------------------------------
# Decodes BSB64 encoded string to a string
def decode_string(src, n, encoding=ENCODING):
    decoded = decode(src, n)
    s = decoded.decode(encoding)
    return s

# Decodes a BSB64 encoded String into a newly-allocated bytearray
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

    b = bytearray(buf)
    return b

#------------------------------------------------------------------------------
def bit_rotate_left(v, n):
    return ((v << n) | (v >> (8 - n))) & 255

def bit_rotate_right(v, n):
    return ((v >> n) | (v << (8 - n))) & 255

if __name__ == '__main__':
    print(__file__)
