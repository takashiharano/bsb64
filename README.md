BSB64
=====================

BSB64 is a simple reversible encoding method for obfuscation based on bit shifting and Base64 encoding.
It is intended for lightweight obfuscation in environments where data would otherwise have to be stored or transmitted as plain text.
The resulting data resembles Base64-encoded text, but it cannot be decoded correctly using a standard Base64 decoder alone.
Details of the algorithm are available at https://libutil.com/bsb64/.

## Usage
Java:
```Java
String encoded = BSB64.encode("abc", 1);
byte[] decoded = BSB64.decode("wsTG", 1);
String decoded = BSB64.decodeToString("wsTG", 1);
```

JavaScript:
```JavaScript
var encoded = bsb64.encode('abc', 1);
var decoded = bsb64.decodeToString('wsTG', 1);
```

Python:
```Python
encoded = bsb64.encode('abc', 1)
decoded = bsb64.decode_to_string('wsTG', 1)
```

PowerShell:
```powershell
$encoded = Get-Bsb64EncodedString "abc" 1
$decoded = Get-Bsb64DecodedString "wsTG" 1
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
