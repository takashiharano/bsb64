. ".\bsb64.ps1"

$s = Get-Encoded-String "abc" 1
Write-Host $s

$s = Get-Encoded-String "abc" 2
Write-Host $s

$s = Get-Encoded-String "abc" 3
Write-Host $s

$s = Get-Encoded-String "abc" 4
Write-Host $s

$s = Get-Encoded-String "abc" 5
Write-Host $s

$s = Get-Encoded-String "abc" 6
Write-Host $s

$s = Get-Encoded-String "abc" 7
Write-Host $s

$s = Get-Encoded-String "abc" 0
Write-Host $s

$s = Get-Encoded-String "abc" 8
Write-Host $s

Write-Host "-------------------------------"
$s = Get-Decoded-String "wsTG" 1
Write-Host $s

$s = Get-Decoded-String "hYmN" 2
Write-Host $s

$s = Get-Decoded-String "CxMb" 3
Write-Host $s

$s = Get-Decoded-String "FiY2" 4
Write-Host $s

$s = Get-Decoded-String "LExs" 5
Write-Host $s

$s = Get-Decoded-String "WJjY" 6
Write-Host $s

$s = Get-Decoded-String "sDGx" 7
Write-Host $s

$s = Get-Decoded-String "np2c" 0
Write-Host $s

$s = Get-Decoded-String "np2c" 8
Write-Host $s

Write-Host "-------------------------------"
$s = Get-Encoded-String "‚ ‚¢‚¤‚¦‚¨" 1
Write-Host $s

Write-Host "-------------------------------"
[byte[]]$b = Get-Content "C:\test\img.jpg" -Encoding Byte
$s = Get-Encoded-String $b 1
Write-Host $s

$b = Get-Decoded-Bytes $s 1
Set-Content "C:\tmp\img.jpg" -Value $b -Encoding Byte
