import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

import bsb64

def test_encode_string():
    s = '\n'
    s += bsb64.encode_string('abc', 0) + '\n'
    s += bsb64.encode_string('abc', 1) + '\n'
    s += bsb64.encode_string('abc', 2) + '\n'
    s += bsb64.encode_string('abc', 3) + '\n'
    s += bsb64.encode_string('abc', 4) + '\n'
    s += bsb64.encode_string('abc', 5) + '\n'
    s += bsb64.encode_string('abc', 6) + '\n'
    s += bsb64.encode_string('abc', 7) + '\n'
    s += bsb64.encode_string('abc', 8) + '\n'
    return s

def test_decode_string():
    s = '\n'
    s += bsb64.decode_string('np2c', 0) + '\n'
    s += bsb64.decode_string('wsTG', 1) + '\n'
    s += bsb64.decode_string('hYmN', 2) + '\n'
    s += bsb64.decode_string('CxMb', 3) + '\n'
    s += bsb64.decode_string('FiY2', 4) + '\n'
    s += bsb64.decode_string('LExs', 5) + '\n'
    s += bsb64.decode_string('WJjY', 6) + '\n'
    s += bsb64.decode_string('sDGx', 7) + '\n'
    s += bsb64.decode_string('np2c', 8) + '\n'
    return s

def test_encode_string_fr():
    s = '\n'
    s += bsb64.encode_string('Français', 0) + '\n'
    s += bsb64.encode_string('Français', 1) + '\n'
    s += bsb64.encode_string('Français', 2) + '\n'
    s += bsb64.encode_string('Français', 3) + '\n'
    s += bsb64.encode_string('Français', 4) + '\n'
    s += bsb64.encode_string('Français', 5) + '\n'
    s += bsb64.encode_string('Français', 6) + '\n'
    s += bsb64.encode_string('Français', 7) + '\n'
    return s

def test_decode_string_fr():
    s = '\n'
    s += bsb64.decode_string('uY2ekTxYnpaM', 0) + '\n'
    s += bsb64.decode_string('jOTC3IdPwtLm', 1) + '\n'
    s += bsb64.decode_string('GcmFuQ+ehaXN', 2) + '\n'
    s += bsb64.decode_string('MpMLcx49C0ub', 3) + '\n'
    s += bsb64.decode_string('ZCcW5jx6FpY3', 4) + '\n'
    s += bsb64.decode_string('yE4szXj0LC1u', 5) + '\n'
    s += bsb64.decode_string('kZxYm/DpWFrc', 6) + '\n'
    s += bsb64.decode_string('IzmwN+HTsLS5', 7) + '\n'
    return s

def test_encode_string_ja():
    s = '\n'
    s += bsb64.encode_string('あいうえお', 0) + '\n'
    s += bsb64.encode_string('あいうえお', 1) + '\n'
    s += bsb64.encode_string('あいうえお', 2) + '\n'
    s += bsb64.encode_string('あいうえお', 3) + '\n'
    s += bsb64.encode_string('あいうえお', 4) + '\n'
    s += bsb64.encode_string('あいうえお', 5) + '\n'
    s += bsb64.encode_string('あいうえお', 6) + '\n'
    s += bsb64.encode_string('あいうえお', 7) + '\n'
    return s

def test_decode_string_ja():
    s = '\n'
    s += bsb64.decode_string('HH59HH57HH55HH53HH51', 0) + '\n'
    s += bsb64.decode_string('xwMFxwMJxwMNxwMRxwMV', 1) + '\n'
    s += bsb64.decode_string('jwYKjwYSjwYajwYijwYq', 2) + '\n'
    s += bsb64.decode_string('HwwUHwwkHww0HwxEHwxU', 3) + '\n'
    s += bsb64.decode_string('PhgoPhhIPhhoPhiIPhio', 4) + '\n'
    s += bsb64.decode_string('fDBQfDCQfDDQfDARfDBR', 5) + '\n'
    s += bsb64.decode_string('+GCg+GAh+GCh+GAi+GCi', 6) + '\n'
    s += bsb64.decode_string('8cBB8cBC8cBD8cBE8cBF', 7) + '\n'
    return s

def test_encode_string_zh():
    s = '\n'
    s += bsb64.encode_string('华语', 0) + '\n'
    s += bsb64.encode_string('华语', 1) + '\n'
    s += bsb64.encode_string('华语', 2) + '\n'
    s += bsb64.encode_string('华语', 3) + '\n'
    s += bsb64.encode_string('华语', 4) + '\n'
    s += bsb64.encode_string('华语', 5) + '\n'
    s += bsb64.encode_string('华语', 6) + '\n'
    s += bsb64.encode_string('华语', 7) + '\n'
    return s

def test_decode_string_zh():
    s = '\n'
    s += bsb64.decode_string('GnJxF1BS', 0) + '\n'
    s += bsb64.decode_string('yxsd0V9b', 1) + '\n'
    s += bsb64.decode_string('lzY6o762', 2) + '\n'
    s += bsb64.decode_string('L2x0R31t', 3) + '\n'
    s += bsb64.decode_string('Xtjojvra', 4) + '\n'
    s += bsb64.decode_string('vLHRHfW1', 5) + '\n'
    s += bsb64.decode_string('eWOjOutr', 6) + '\n'
    s += bsb64.decode_string('8sZHdNfW', 7) + '\n'
    return s

def test():
    ret = ''
    ret += 'test_encode_string() = ' + test_encode_string() + '\n'
    ret += '\n'
    ret += 'test_decode_string() = ' + test_decode_string() + '\n'
    ret += '\n'

    ret += 'test_encode_string_fr() = ' + test_encode_string_fr() + '\n'
    ret += '\n'
    ret += 'test_decode_string_fr() = ' + test_decode_string_fr() + '\n'
    ret += '\n'

    ret += 'test_encode_string_ja() = ' + test_encode_string_ja() + '\n'
    ret += '\n'
    ret += 'test_decode_string_ja() = ' + test_decode_string_ja() + '\n'
    ret += '\n'

    ret += 'test_encode_string_zh() = ' + test_encode_string_zh() + '\n'
    ret += '\n'
    ret += 'test_decode_string_zh() = ' + test_decode_string_zh() + '\n'
    ret += '\n'
    return ret

def main():
    ret = test()
    print(ret)

main()
