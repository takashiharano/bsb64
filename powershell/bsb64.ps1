# BSB64 (Bit Shifted Base64)
# https://bsb64.com/
#
# The MIT License
#
# Copyright (c) 2022 Takashi Harano
#
# Permission is hereby granted, free of charge, to any person obtaining a copy
# of this software and associated documentation files (the "Software"), to deal
# in the Software without restriction, including without limitation the rights
# to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
# copies of the Software, and to permit persons to whom the Software is
# furnished to do so, subject to the following conditions:
#
# The above copyright notice and this permission notice shall be included in
# all copies or substantial portions of the Software.
#
# THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
# IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
# FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
# AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
# LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
# OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
# THE SOFTWARE.
#------------------------------------------------------------------------------
# Usage:
# . ".\bsb64.ps1"
#
# [Encode]
#  String:
#   $s = Get-Bsb64EncodedString "<STRING>" <0-7>
#
#  Byte[]:
#   [byte[]]$b = Get-Content "C:\test\file.bin" -Encoding Byte
#   $s = Get-Bsb64EncodedString $b <0-7>
#
# [Decode]
#  String:
#   $s = Get-Bsb64DecodedString "<BSB64_STRING>" <0-7>
#
#  Byte[]:
#   $b = Get-Bsb64DecodedBytes "<BSB64_STRING>" <0-7>
#   Set-Content "C:\tmp\file.bin" -Value $b -Encoding Byte
#------------------------------------------------------------------------------

#----------------------------------------------------------
# Byte array or plain text to BSB64 encoded string
#----------------------------------------------------------
function Get-Bsb64EncodedString {
    Param (
        $Src,
        [int]$N
    )

    if ($Src.GetType().Name -eq "String") {
        $b = [System.Text.Encoding]::UTF8.GetBytes($Src)
    } else {
        $b = $Src
    }

    $buf = Get-Bsb64EncodedBytes $b $N
    $encoded = [System.Convert]::ToBase64String($buf)

    return $encoded
}

function Get-Bsb64EncodedBytes {
    Param (
        [byte[]] $Src,
        [int] $N
    )

    $buf = New-Object byte[] $Src.Length

    for ($i=0; $i -lt $Src.Length; $i++) {
        if ($N % 8 -eq 0) {
            $b = Get-InvertedBitPattern $Src[$i]
        } else {
            $b = Get-LeftRotatedBitPattern $Src[$i] $N
        }
        $buf[$i] = $b
    }

    return $buf
}

#----------------------------------------------------------
# BSB64 encoded string to Byte array
#----------------------------------------------------------
function Get-Bsb64DecodedBytes {
    Param (
        $Src,
        [int]$N
    )

    $buf = [System.Convert]::FromBase64String($Src)
    $arr = New-Object byte[] $buf.Length

    for ($i = 0; $i -lt $buf.Length; $i++) {
        if ($N % 8 -eq 0) {
            $b = Get-InvertedBitPattern $buf[$i]
        } else {
            $b = Get-RightRotatedBitPattern $buf[$i] $N
        }
        $arr[$i] = $b
    }

    return $arr
}

#----------------------------------------------------------
# BSB64 encoded string to Plain text
#----------------------------------------------------------
function Get-Bsb64DecodedString {
    Param (
        $Src,
        [int]$N
    )

    $buf = Get-Bsb64DecodedBytes $Src $N
    $str = [System.Text.Encoding]::UTF8.GetString($buf)
    return $str
}

function Get-LeftRotatedBitPattern {
    Param (
        [byte]$V,
        [int]$N
    )

    $N = $N % 8
    $i = [int]$V -band 255
    $b = [byte]((($i -shl $N) -bor ($i -shr (8 - $N))) -band 255)
    return $b
}

function Get-RightRotatedBitPattern {
    Param (
        [byte]$V,
        [int]$N
    )

    $N = $N % 8
    $i = ([int]$V -band 255)
    $b = [byte]((($i -shr $N) -bor ($i -shl ((8 - $N)))) -band 255)
    return $b
}

function Get-InvertedBitPattern {
    Param (
        [byte]$V
    )

    $b = [byte]((-bnot $V) -band 255)
    return $b
}
