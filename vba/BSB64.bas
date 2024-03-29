' BSB64 (Bit Shifted Base64)
' Copyright 2018 Takashi Harano
' Released under the MIT license
' https://bsb64.com/

''
' Plain text to BSB64 encoded string
'
Public Function EncodeString(str As String, n As Integer) As String
    Dim arr() As Byte
    Dim ret As String
    arr = StringToUtf8Bytes(str)
    ret = Encode(arr, n)
    EncodeString = ret
End Function

Public Function Encode(arr() As Byte, n As Integer) As String
    Dim i As Integer
    Dim arrLen As Integer
    Dim buf() As Byte
    arrLen = UBound(arr)
    ReDim buf(arrLen)
    For i = 0 To arrLen
        If n Mod 8 = 0 Then
            buf(i) = InvertBit(arr(i))
        Else
            buf(i) = RotateBitLeft(arr(i), n)
        End If
    Next
    Encode = EncodeBase64(buf)
End Function

''
' BSB64 encoded string to Plain text
'
Public Function DecodeString(str As String, n As Integer) As String
    Dim arr() As Byte
    Dim ret As String
    arr = Decode(str, n)
    ret = Utf8BytesToString(arr)
    DecodeString = ret
End Function

Public Function Decode(src As String, n As Integer) As Byte()
    Dim i As Integer
    Dim bufLen As Integer
    Dim buf() As Byte
    Dim arr() As Byte
    buf = DecodeBase64(src)
    bufLen = UBound(buf)
    ReDim arr(bufLen)
    For i = 0 To bufLen
        If n Mod 8 = 0 Then
            arr(i) = InvertBit(buf(i))
        Else
            arr(i) = RotateBitRight(buf(i), n)
        End If
    Next
    Decode = arr
End Function

''
' Plain text to Base64 encoded string
'
Public Function EncodeBase64String(str As String) As String
    Dim arr() As Byte
    Dim ret As String
    arr = StringToUtf8Bytes(str)
    ret = EncodeBase64(arr)
    EncodeBase64String = ret
End Function

''
' Base64 encoded string to Plain text
'
Public Function DecodeBase64String(str As String) As String
    Dim arr() As Byte
    Dim ret As String
    arr = DecodeBase64(str)
    ret = Utf8BytesToString(arr)
    DecodeBase64String = ret
End Function

Public Function EncodeBase64(ByRef arr() As Byte) As String
    Dim arrLen As Integer
    Dim str As String
    Dim b0 As Integer
    Dim b1 As Integer
    Dim b2 As Integer
    Dim tbl(64) As Byte
    Dim i As Integer
    Dim codePoints(3) As Integer
    Dim idx As Integer

    tbl(64) = 61
    tbl(63) = 47
    tbl(62) = 43
    For i = 0 To 61
        If i < 26 Then
            tbl(i) = i + 65
        ElseIf i < 52 Then
            tbl(i) = i + 71
        Else
            tbl(i) = i - 4
       End If
    Next i

    str = ""
    arrLen = UBound(arr)
    For i = 0 To arrLen Step 3
        b0 = 0
        b1 = 0
        b2 = 0
        If i > arrLen Then
          Exit For
        End If
        b0 = arr(i) And 255
        If ((i + 1) <= arrLen) Then
            b1 = arr(i + 1) And 255
        End If
        If ((i + 2) <= arrLen) Then
            b2 = arr(i + 2) And 255
        End If

        codePoints(0) = tbl(b0 \ 2 ^ 2)
        codePoints(1) = tbl((b0 And 3) * 2 ^ 4 Or b1 \ 2 ^ 4)

        If ((i + 1) <= arrLen) Then
            idx = ((b1 And 15) * 2 ^ 2) Or (b2 \ 2 ^ 6)
        Else
            idx = 64
        End If
        codePoints(2) = tbl(idx)

        If ((i + 2) <= arrLen) Then
            idx = (b2 And 63)
        Else
            idx = 64
        End If
        codePoints(3) = tbl(idx)

        str = str & ChrW(codePoints(0)) & ChrW(codePoints(1)) & ChrW(codePoints(2)) & ChrW(codePoints(3))
    Next

    EncodeBase64 = str
End Function

Public Function DecodeBase64(ByRef str As String) As Byte()
    Dim arr() As Byte
    Dim i As Integer
    Dim j As Integer
    Dim c As Integer
    Dim idx As Integer
    Dim tbl(127) As Byte
    Dim buf(3) As Byte

    If Len(str) = 0 Then
        Exit Function
    End If

    For i = 1 To Len(str)
        c = Asc(Mid(str, i, 1))
        If Not (((c >= 48) And (c <= 57)) Or ((c >= 65) And (c <= 90)) Or ((c >= 97) And (c <= 122)) Or (c = 43) Or (c = 47) Or (c = 61)) Then
            MsgBox "Invalid char: " & c & " at " & i
        End If
    Next

    tbl(61) = 64
    tbl(47) = 63
    tbl(43) = 62
    For i = 0 To 61
        If i < 26 Then
            idx = i + 65
        ElseIf i < 52 Then
            idx = i + 71
        Else
            idx = i - 4
        End If
        tbl(idx) = i
    Next

    For i = 1 To Len(str) Step 4
        For j = 0 To 3
            If (i + j) > Len(str) Then
                Exit For
            End If
            buf(j) = tbl(Asc(Mid(str, i + j, 1)))
        Next
        Call ArrayPush(arr, ((buf(0) * 2 ^ 2) Or (buf(1) And 63) \ 2 ^ 4))
        Call ArrayPush(arr, (((buf(1) And 15) * 2 ^ 4) Or (buf(2) And 63) \ 2 ^ 2))
        Call ArrayPush(arr, (((buf(2) And 3) * 2 ^ 6) Or (buf(3) And 63)))
    Next

    If buf(3) = 64 Then
        Call ArrayPop(arr)
        If buf(2) = 64 Then
            Call ArrayPop(arr)
        End If
    End If

    DecodeBase64 = arr
End Function

Private Function InvertBit(v As Byte) As Byte
    InvertBit = (v Xor 255)
End Function

Private Function RotateBitLeft(v As Byte, n As Integer) As Byte
    n = n Mod 8
    Dim ret As Byte
    ret = ShiftBitLeft(v, n) Or ShiftBitRight(v, (8 - n))
    RotateBitLeft = ret
End Function

Private Function RotateBitRight(v As Byte, n As Integer) As Byte
    n = n Mod 8
    Dim ret As Byte
    ret = ShiftBitRight(v, n) Or ShiftBitLeft(v, (8 - n))
    RotateBitRight = ret
End Function

Private Function ShiftBitLeft(v As Byte, n As Integer) As Byte
    Dim ret As Byte
    If n = 0 Then
        ret = v
    Else
        Dim k As Byte
        k = CLng(2 ^ (8 - n - 1))
        Dim d As Byte
        d = v And (k - 1)
        Dim c As Byte
        c = d * CLng(2 ^ n)
        If v And k Then
            c = c Or &H80
        End If
        ret = c
    End If
    ShiftBitLeft = ret
End Function

Private Function ShiftBitRight(v As Byte, n As Integer) As Byte
    Dim ret As Byte
    If n = 0 Then
        ret = v
    Else
        Dim y As Byte
        y = v And &H7F
        Dim z As Byte
        If n = 8 - 1 Then
            z = 0
        Else
            z = y \ CLng(2 ^ n)
        End If
        If y <> v Then
            z = z Or CLng(2 ^ (8 - n - 1))
        End If
        ret = z
    End If
    ShiftBitRight = ret
End Function

Private Function ArrayPush(ByRef arr As Variant, val As Variant)
    On Error GoTo ArrInit
    ReDim Preserve arr(UBound(arr) + 1)
    arr(UBound(arr)) = val
    Exit Function
ArrInit:
    ReDim arr(0)
    arr(0) = val
End Function

Private Function ArrayPop(ByRef arr As Variant)
    ReDim Preserve arr(UBound(arr) - 1)
End Function

Private Function StringToUtf8Bytes(ByRef sData As String) As Byte()
    Dim arr() As Byte
    With CreateObject("ADODB.Stream")
        .Mode = 3 'adModeReadWrite
        .Open
        .Type = 2 'adTypeText
        .Charset = "UTF-8"
        .WriteText sData
        .Position = 0
        .Type = 1 'adTypeBinary
        .Position = 3 'Skip BOM
        arr = .Read
        .Close
    End With
    StringToUtf8Bytes = arr
End Function

Private Function Utf8BytesToString(ByRef arr() As Byte) As String
    Dim str As String
    With CreateObject("ADODB.Stream")
        .Mode = 3 'adModeReadWrite
        .Open
        .Type = 1 'adTypeBinary
        .Write arr
        .Position = 0
        .Type = 2 'adTypeText
        .Charset = "UTF-8"
        str = .ReadText
        .Close
    End With
    Utf8BytesToString = str
End Function
