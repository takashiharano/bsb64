#!python
#!/usr/bin/python3.4

import os
import sys
import datetime

sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

import bsb64

def test_encode_str():
  s = '\n'
  s += bsb64.encode_str('abc', 0) + '\n'
  s += bsb64.encode_str('abc', 1) + '\n'
  s += bsb64.encode_str('abc', 2) + '\n'
  s += bsb64.encode_str('abc', 3) + '\n'
  s += bsb64.encode_str('abc', 4) + '\n'
  s += bsb64.encode_str('abc', 5) + '\n'
  s += bsb64.encode_str('abc', 6) + '\n'
  s += bsb64.encode_str('abc', 7) + '\n'
  return s

def test_decode_str():
  s = '\n'
  s += bsb64.decode_str('np2c', 0) + '\n'
  s += bsb64.decode_str('wsTG', 1) + '\n'
  s += bsb64.decode_str('hYmN', 2) + '\n'
  s += bsb64.decode_str('CxMb', 3) + '\n'
  s += bsb64.decode_str('FiY2', 4) + '\n'
  s += bsb64.decode_str('LExs', 5) + '\n'
  s += bsb64.decode_str('WJjY', 6) + '\n'
  s += bsb64.decode_str('sDGx', 7) + '\n'
  return s

def test_encode_str_fr():
  s = '\n'
  s += bsb64.encode_str('Français', 0) + '\n'
  s += bsb64.encode_str('Français', 1) + '\n'
  s += bsb64.encode_str('Français', 2) + '\n'
  s += bsb64.encode_str('Français', 3) + '\n'
  s += bsb64.encode_str('Français', 4) + '\n'
  s += bsb64.encode_str('Français', 5) + '\n'
  s += bsb64.encode_str('Français', 6) + '\n'
  s += bsb64.encode_str('Français', 7) + '\n'
  return s

def test_decode_str_fr():
  s = '\n'
  s += bsb64.decode_str('uY2ekTxYnpaM', 0) + '\n'
  s += bsb64.decode_str('jOTC3IdPwtLm', 1) + '\n'
  s += bsb64.decode_str('GcmFuQ+ehaXN', 2) + '\n'
  s += bsb64.decode_str('MpMLcx49C0ub', 3) + '\n'
  s += bsb64.decode_str('ZCcW5jx6FpY3', 4) + '\n'
  s += bsb64.decode_str('yE4szXj0LC1u', 5) + '\n'
  s += bsb64.decode_str('kZxYm/DpWFrc', 6) + '\n'
  s += bsb64.decode_str('IzmwN+HTsLS5', 7) + '\n'
  return s

def test_encode_str_ja():
  s = '\n'
  s += bsb64.encode_str('あいうえお', 0) + '\n'
  s += bsb64.encode_str('あいうえお', 1) + '\n'
  s += bsb64.encode_str('あいうえお', 2) + '\n'
  s += bsb64.encode_str('あいうえお', 3) + '\n'
  s += bsb64.encode_str('あいうえお', 4) + '\n'
  s += bsb64.encode_str('あいうえお', 5) + '\n'
  s += bsb64.encode_str('あいうえお', 6) + '\n'
  s += bsb64.encode_str('あいうえお', 7) + '\n'
  return s

def test_decode_str_ja():
  s = '\n'
  s += bsb64.decode_str('HH59HH57HH55HH53HH51', 0) + '\n'
  s += bsb64.decode_str('xwMFxwMJxwMNxwMRxwMV', 1) + '\n'
  s += bsb64.decode_str('jwYKjwYSjwYajwYijwYq', 2) + '\n'
  s += bsb64.decode_str('HwwUHwwkHww0HwxEHwxU', 3) + '\n'
  s += bsb64.decode_str('PhgoPhhIPhhoPhiIPhio', 4) + '\n'
  s += bsb64.decode_str('fDBQfDCQfDDQfDARfDBR', 5) + '\n'
  s += bsb64.decode_str('+GCg+GAh+GCh+GAi+GCi', 6) + '\n'
  s += bsb64.decode_str('8cBB8cBC8cBD8cBE8cBF', 7) + '\n'
  return s

def test_encode_str_zh():
  s = '\n'
  s += bsb64.encode_str('华语', 0) + '\n'
  s += bsb64.encode_str('华语', 1) + '\n'
  s += bsb64.encode_str('华语', 2) + '\n'
  s += bsb64.encode_str('华语', 3) + '\n'
  s += bsb64.encode_str('华语', 4) + '\n'
  s += bsb64.encode_str('华语', 5) + '\n'
  s += bsb64.encode_str('华语', 6) + '\n'
  s += bsb64.encode_str('华语', 7) + '\n'
  return s

def test_decode_str_zh():
  s = '\n'
  s += bsb64.decode_str('GnJxF1BS', 0) + '\n'
  s += bsb64.decode_str('yxsd0V9b', 1) + '\n'
  s += bsb64.decode_str('lzY6o762', 2) + '\n'
  s += bsb64.decode_str('L2x0R31t', 3) + '\n'
  s += bsb64.decode_str('Xtjojvra', 4) + '\n'
  s += bsb64.decode_str('vLHRHfW1', 5) + '\n'
  s += bsb64.decode_str('eWOjOutr', 6) + '\n'
  s += bsb64.decode_str('8sZHdNfW', 7) + '\n'
  return s

def test():
  ret = ''
  ret += 'test_encode_str() = ' + test_encode_str() + '\n'
  ret += '\n'
  ret += 'test_decode_str() = ' + test_decode_str() + '\n'
  ret += '\n'

  ret += 'test_encode_str_fr() = ' + test_encode_str_fr() + '\n'
  ret += '\n'
  ret += 'test_decode_str_fr() = ' + test_decode_str_fr() + '\n'
  ret += '\n'

  ret += 'test_encode_str_ja() = ' + test_encode_str_ja() + '\n'
  ret += '\n'
  ret += 'test_decode_str_ja() = ' + test_decode_str_ja() + '\n'
  ret += '\n'

  ret += 'test_encode_str_zh() = ' + test_encode_str_zh() + '\n'
  ret += '\n'
  ret += 'test_decode_str_zh() = ' + test_decode_str_zh() + '\n'
  ret += '\n'
  return ret

def main():
  ret = test()
  sys.stdout.reconfigure(encoding='utf-8')
  print('Content-Type: text/plain; charset=UTF-8')
  print() 
  print(ret)

main()
