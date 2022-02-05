. ".\bsb64.ps1"

$s = Get-Bsb64EncodedString "abc" 1
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 2
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 3
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 4
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 5
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 6
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 7
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 0
Write-Host $s

$s = Get-Bsb64EncodedString "abc" 8
Write-Host $s

Write-Host "-------------------------------"
$s = Get-Bsb64DecodedString "wsTG" 1
Write-Host $s

$s = Get-Bsb64DecodedString "hYmN" 2
Write-Host $s

$s = Get-Bsb64DecodedString "CxMb" 3
Write-Host $s

$s = Get-Bsb64DecodedString "FiY2" 4
Write-Host $s

$s = Get-Bsb64DecodedString "LExs" 5
Write-Host $s

$s = Get-Bsb64DecodedString "WJjY" 6
Write-Host $s

$s = Get-Bsb64DecodedString "sDGx" 7
Write-Host $s

$s = Get-Bsb64DecodedString "np2c" 0
Write-Host $s

$s = Get-Bsb64DecodedString "np2c" 8
Write-Host $s

Write-Host "-------------------------------"
$s = Get-Bsb64EncodedString "‚ ‚¢‚¤‚¦‚¨" 1
Write-Host $s

Write-Host "-------------------------------"
[byte[]]$b = Get-Content "C:\test\img.jpg" -Encoding Byte
$s = Get-Bsb64EncodedString $b 1
Write-Host $s

$b = Get-Bsb64DecodedBytes $s 1
Set-Content "C:\tmp\img.jpg" -Value $b -Encoding Byte
