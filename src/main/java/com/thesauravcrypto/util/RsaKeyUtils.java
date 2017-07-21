package com.thesauravcrypto.util;

import java.io.File;

public class RsaKeyUtils {

	/**
	 * int to hold length of the key generation algorithm.
	 */
	public static final int RSA_KEY_LENGTH_1024 = 1024;
	public static final int RSA_KEY_LENGTH_2048 = 2048;
	public static final int RSA_KEY_LENGTH_4096 = 4096;
	/**
	 * String to hold name of the encryption algorithm.
	 */
	public static final String ALGORITHM = "RSA";

	/**
	 * String to hold the name of the private key file.
	 */
	public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";

	/**
	 * String to hold name of the public key file.
	 */
	public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";


	/**
	 * The method checks if the pair of public and private key has been generated.
	 * 
	 * @return flag indicating if the pair of keys were generated.
	 */
	public static boolean areKeysPresent() {

		File privateKey = new File(RsaKeyUtils.PRIVATE_KEY_FILE);
		File publicKey = new File(RsaKeyUtils.PUBLIC_KEY_FILE);

		if (privateKey.exists() && publicKey.exists()) {
			return true;
		}
		return false;
	}

	private final static char[] hexArray = "0123456789ABCDEF".toCharArray();
	public static String bytesToHex(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for ( int j = 0; j < bytes.length; j++ ) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

}
