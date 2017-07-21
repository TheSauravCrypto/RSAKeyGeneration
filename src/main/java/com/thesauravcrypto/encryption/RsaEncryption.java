package com.thesauravcrypto.encryption;

import java.security.PublicKey;

import javax.crypto.Cipher;

import com.thesauravcrypto.util.RsaKeyUtils;

public class RsaEncryption {


	/**
	 * Encrypt the plain text using public key.
	 * 
	 * @param text
	 *          : original plain text
	 * @param key
	 *          :The public key
	 * @return Encrypted text
	 * @throws java.lang.Exception
	 */
	public  byte[] encrypt(String text, PublicKey key) {
		byte[] cipherText = null;
		try {
			// get an RSA cipher object and print the provider
			final Cipher cipher = Cipher.getInstance(RsaKeyUtils.ALGORITHM);
			// encrypt the plain text using the public key
			cipher.init(Cipher.ENCRYPT_MODE, key);
			cipherText = cipher.doFinal(text.getBytes());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cipherText;
	}

}
