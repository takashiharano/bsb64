Option Explicit

Public Sub EncodeTest()
    Debug.Print "Encode Test"

    Call DoTest("abc", 0, "np2c")
    Call DoTest("abc", 1, "wsTG")
    Call DoTest("abc", 2, "hYmN")
    Call DoTest("abc", 3, "CxMb")
    Call DoTest("abc", 4, "FiY2")
    Call DoTest("abc", 5, "LExs")
    Call DoTest("abc", 6, "WJjY")
    Call DoTest("abc", 7, "sDGx")

    Call DoTest("あいう", 0, "HH59HH57HH55")
    Call DoTest("あいう", 1, "xwMFxwMJxwMN")
    Call DoTest("あいう", 2, "jwYKjwYSjwYa")
    Call DoTest("あいう", 3, "HwwUHwwkHww0")
    Call DoTest("あいう", 4, "PhgoPhhIPhho")
    Call DoTest("あいう", 5, "fDBQfDCQfDDQ")
    Call DoTest("あいう", 6, "+GCg+GAh+GCh")
    Call DoTest("あいう", 7, "8cBB8cBC8cBD")

    Debug.Print ""
End Sub

Private Sub DoTest(src As String, n As Integer, exp As String)
    Dim r As String
    r = BSB64.EncodeString(src, n)

    Dim res As String
    Dim status As String

    status = "FAIL"
    If r = exp Then
        status = "PASS"
    End If

    res = "[" & status & "] """ & src & """ -> """ & r & """"
    Debug.Print res
End Sub
