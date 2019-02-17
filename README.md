BSB64
=====================

BSB64 is a specification and implementation for the simple encryption.  
The encrypted data is seemingly Base64 encoded characters, but it is impossible to decode in Base64.  
The detail of the mechanism is available at https://bsb64.com/

## Usage
Java:
```Java
BSB64.encodeString("abc", 1);
BSB64.decodeString("wsTG", 1);
```

JavaScript:
```JavaSctipt
BSB64.encodeString('abc', 1);
BSB64.decodeString('wsTG', 1);
```

Python:
```Python
bsb64.encode_str('abc', 1)
bsb64.decode_str('wsTG', 1)
```

Visual Basic:
```Visual Basic
BSB64.encodeBSB64Str("abc", 1)
BSB64.decodeBSB64Str("wsTG", 1)
```

## Notice
BSB64 is not intended to be used where secrecy is of any concern.
