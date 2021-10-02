BSB64
=====================

BSB64 is a specification and implementation for the simple encryption.  
The encrypted data is seemingly Base64 encoded characters, but it is impossible to decode in Base64.  
The detail of the mechanism is available at https://bsb64.com/

## Usage
Java:
```Java
String encoded = BSB64.encodeString("abc", 1);
String decoded = BSB64.decodeString("wsTG", 1);
```

JavaScript:
```JavaSctipt
var encoded = BSB64.encodeString('abc', 1);
var decoded = BSB64.decodeString('wsTG', 1);
```

Python:
```Python
encoded = bsb64.encode_str('abc', 1)
decoded = bsb64.decode_str('wsTG', 1)
```

Visual Basic:
```Visual Basic
Dim encoded As String
Dim decoded As String
encoded = BSB64.EncodeString("abc", 1)
decoded = BSB64.DecodeString("wsTG", 1)
```

## Notice
BSB64 is not intended to be used where secrecy is of any concern.
