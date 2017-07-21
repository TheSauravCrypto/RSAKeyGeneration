package com.thesauravcrypto.keygeneration;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;

import javax.crypto.Cipher;

import com.thesauravcrypto.util.RsaKeyUtils;

/**
 * @author theSauravCrypto
 * 
 */
public class RsaKeyGeneration {


	/**
	 * Generate key which contains a pair of private and public key using 1024/2048/4096
	 * bytes. Store the set of keys in Prvate.key and Public.key files.
	 * 
	 * @throws NoSuchAlgorithmException
	 * @throws IOException
	 * @throws FileNotFoundException
	 */
	public static KeyPair generateKey(String keyAlgo,int keyGenLength) {
		KeyPair keyPair=null;
		try {
			final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(keyAlgo);
			keyGen.initialize(keyGenLength);
			keyPair = keyGen.generateKeyPair();
			return keyPair;

		} catch (Exception e) {
			e.printStackTrace();
			return keyPair;

		}

	}






	/*  *//**
	 * Test the EncryptionUtil
	 *//*
  public static void main(String[] args) {

    try {

      // Check if the pair of keys are present else generate those.
      if (!areKeysPresent()) {
        // Method generates a pair of keys using the RSA algorithm and stores it
        // in their respective files
        generateKey();
      }

      final String originalText = "Text to be encrypted ";
      ObjectInputStream inputStream = null;

      // Encrypt the string using the public key
      inputStream = new ObjectInputStream(new FileInputStream(RsaKeyUtils.PUBLIC_KEY_FILE));
      final PublicKey publicKey = (PublicKey) inputStream.readObject();
      final byte[] cipherText = encrypt(originalText, publicKey);

      // Decrypt the cipher text using the private key.
      inputStream = new ObjectInputStream(new FileInputStream(RsaKeyUtils.PRIVATE_KEY_FILE));
      final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
      final String plainText = decrypt(cipherText, privateKey);

      // Printing the Original, Encrypted and Decrypted Text
      System.out.println("Original: " + originalText);
      System.out.println("Encrypted: " +cipherText.toString());
      System.out.println("Decrypted: " + plainText);

    } catch (Exception e) {
      e.printStackTrace();
    }
  }*/
}