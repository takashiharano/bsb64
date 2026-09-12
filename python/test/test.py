import os
import sys

sys.path.append(os.path.join(os.path.dirname(__file__), '..'))

import bsb64

def test_encode():
    s = '\n'
    s += bsb64.encode('abc', 0) + '\n'
    s += bsb64.encode('abc', 1) + '\n'
    s += bsb64.encode('abc', 2) + '\n'
    s += bsb64.encode('abc', 3) + '\n'
    s += bsb64.encode('abc', 4) + '\n'
    s += bsb64.encode('abc', 5) + '\n'
    s += bsb64.encode('abc', 6) + '\n'
    s += bsb64.encode('abc', 7) + '\n'
    s += bsb64.encode('abc', 8) + '\n'
    return s

def test_decode_to_string():
    s = '\n'
    s += bsb64.decode_to_string('np2c', 0) + '\n'
    s += bsb64.decode_to_string('wsTG', 1) + '\n'
    s += bsb64.decode_to_string('hYmN', 2) + '\n'
    s += bsb64.decode_to_string('CxMb', 3) + '\n'
    s += bsb64.decode_to_string('FiY2', 4) + '\n'
    s += bsb64.decode_to_string('LExs', 5) + '\n'
    s += bsb64.decode_to_string('WJjY', 6) + '\n'
    s += bsb64.decode_to_string('sDGx', 7) + '\n'
    s += bsb64.decode_to_string('np2c', 8) + '\n'
    return s

def test_encode_fr():
    s = '\n'
    s += bsb64.encode('Français', 0) + '\n'
    s += bsb64.encode('Français', 1) + '\n'
    s += bsb64.encode('Français', 2) + '\n'
    s += bsb64.encode('Français', 3) + '\n'
    s += bsb64.encode('Français', 4) + '\n'
    s += bsb64.encode('Français', 5) + '\n'
    s += bsb64.encode('Français', 6) + '\n'
    s += bsb64.encode('Français', 7) + '\n'
    return s

def test_decode_to_string_fr():
    s = '\n'
    s += bsb64.decode_to_string('uY2ekTxYnpaM', 0) + '\n'
    s += bsb64.decode_to_string('jOTC3IdPwtLm', 1) + '\n'
    s += bsb64.decode_to_string('GcmFuQ+ehaXN', 2) + '\n'
    s += bsb64.decode_to_string('MpMLcx49C0ub', 3) + '\n'
    s += bsb64.decode_to_string('ZCcW5jx6FpY3', 4) + '\n'
    s += bsb64.decode_to_string('yE4szXj0LC1u', 5) + '\n'
    s += bsb64.decode_to_string('kZxYm/DpWFrc', 6) + '\n'
    s += bsb64.decode_to_string('IzmwN+HTsLS5', 7) + '\n'
    return s

def test_encode_ja():
    s = '\n'
    s += bsb64.encode('あいうえお', 0) + '\n'
    s += bsb64.encode('あいうえお', 1) + '\n'
    s += bsb64.encode('あいうえお', 2) + '\n'
    s += bsb64.encode('あいうえお', 3) + '\n'
    s += bsb64.encode('あいうえお', 4) + '\n'
    s += bsb64.encode('あいうえお', 5) + '\n'
    s += bsb64.encode('あいうえお', 6) + '\n'
    s += bsb64.encode('あいうえお', 7) + '\n'
    return s

def test_decode_to_string_ja():
    s = '\n'
    s += bsb64.decode_to_string('HH59HH57HH55HH53HH51', 0) + '\n'
    s += bsb64.decode_to_string('xwMFxwMJxwMNxwMRxwMV', 1) + '\n'
    s += bsb64.decode_to_string('jwYKjwYSjwYajwYijwYq', 2) + '\n'
    s += bsb64.decode_to_string('HwwUHwwkHww0HwxEHwxU', 3) + '\n'
    s += bsb64.decode_to_string('PhgoPhhIPhhoPhiIPhio', 4) + '\n'
    s += bsb64.decode_to_string('fDBQfDCQfDDQfDARfDBR', 5) + '\n'
    s += bsb64.decode_to_string('+GCg+GAh+GCh+GAi+GCi', 6) + '\n'
    s += bsb64.decode_to_string('8cBB8cBC8cBD8cBE8cBF', 7) + '\n'
    return s

def test_encode_zh():
    s = '\n'
    s += bsb64.encode('华语', 0) + '\n'
    s += bsb64.encode('华语', 1) + '\n'
    s += bsb64.encode('华语', 2) + '\n'
    s += bsb64.encode('华语', 3) + '\n'
    s += bsb64.encode('华语', 4) + '\n'
    s += bsb64.encode('华语', 5) + '\n'
    s += bsb64.encode('华语', 6) + '\n'
    s += bsb64.encode('华语', 7) + '\n'
    return s

def test_decode_to_string_zh():
    s = '\n'
    s += bsb64.decode_to_string('GnJxF1BS', 0) + '\n'
    s += bsb64.decode_to_string('yxsd0V9b', 1) + '\n'
    s += bsb64.decode_to_string('lzY6o762', 2) + '\n'
    s += bsb64.decode_to_string('L2x0R31t', 3) + '\n'
    s += bsb64.decode_to_string('Xtjojvra', 4) + '\n'
    s += bsb64.decode_to_string('vLHRHfW1', 5) + '\n'
    s += bsb64.decode_to_string('eWOjOutr', 6) + '\n'
    s += bsb64.decode_to_string('8sZHdNfW', 7) + '\n'
    return s

def test():
    ret = ''
    ret += 'test_encode() = ' + test_encode() + '\n'
    ret += '\n'
    ret += 'test_decode_to_string() = ' + test_decode_to_string() + '\n'
    ret += '\n'

    ret += 'test_encode_fr() = ' + test_encode_fr() + '\n'
    ret += '\n'
    ret += 'test_decode_to_string_fr() = ' + test_decode_to_string_fr() + '\n'
    ret += '\n'

    ret += 'test_encode_ja() = ' + test_encode_ja() + '\n'
    ret += '\n'
    ret += 'test_decode_to_string_ja() = ' + test_decode_to_string_ja() + '\n'
    ret += '\n'

    ret += 'test_encode_zh() = ' + test_encode_zh() + '\n'
    ret += '\n'
    ret += 'test_decode_to_string_zh() = ' + test_decode_to_string_zh() + '\n'
    ret += '\n'
    return ret

def main():
    ret = test()
    print(ret)

main()
