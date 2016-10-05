<?php

decrypt("0d84f8840d77bac1ac87f62bd02e365e","57238004e784498bbc2f8bf984565090");

function encrypt($plaintext, $key) {
    $plaintext = pkcs5_pad($plaintext, 16);
    return bin2hex(mcrypt_encrypt(MCRYPT_RIJNDAEL_128, hex2bin($key), $plaintext, MCRYPT_MODE_ECB));
}

function decrypt($encrypted, $key) {
    $decrypted = mcrypt_decrypt(MCRYPT_RIJNDAEL_128, pack('H*',$key), pack('H*', $encrypted), MCRYPT_MODE_ECB);
    $padSize = ord(substr($decrypted, -1));
    return substr($decrypted, 0, $padSize*-1);
}

function pkcs5_pad ($text, $blocksize)
{
    $pad = $blocksize - (strlen($text) % $blocksize);
    return $text . str_repeat(chr($pad), $pad);
}

function hex2bin($hex_string) {
    $pos = 0;
	$result = '';
	while ($pos < strlen($hex_string)) {
	  if (strpos(HEX2BIN_WS, $hex_string{$pos}) !== FALSE) {
	    $pos++;
	  } else {
	    $code = hexdec(substr($hex_string, $pos, 2));
		$pos = $pos + 2;
	    $result .= chr($code); 
	  }
	}
	return $result;
}

?>