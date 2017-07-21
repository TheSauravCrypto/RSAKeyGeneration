package com.thesauravcrypto.RsaKeyGeneration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.security.KeyPair;
import java.security.PrivateKey;
import java.security.PublicKey;

import com.thesauravcrypto.decryption.RsaKeyDecryption;
import com.thesauravcrypto.encryption.RsaEncryption;
import com.thesauravcrypto.keygeneration.RsaKeyGeneration;
import com.thesauravcrypto.util.RsaKeyUtils;

/**
 * Hello world!
 *
 */
public class App 
{
	public static void main( String[] args )
	{
		System.out.println( "RSA Key Generation" );
		try {
			RsaKeyGeneration rsakeygen=new RsaKeyGeneration();
			// Check if the pair of keys are present else generate those.
			//if (!RsaKeyUtils.areKeysPresent()) {
				// Method generates a pair of keys using the RSA algorithm and stores it
				// in their respective files
				System.out.println("RSA Key generation Started.........");
				
				KeyPair key	=rsakeygen.generateKey(RsaKeyUtils.ALGORITHM,RsaKeyUtils.RSA_KEY_LENGTH_1024);
				
				System.out.println("RSA Public key ::"+RsaKeyUtils.bytesToHex(key.getPublic().getEncoded()));
				System.out.println("RSA Private key ::"+RsaKeyUtils.bytesToHex(key.getPrivate().getEncoded()));


				File privateKeyFile = new File(RsaKeyUtils.PRIVATE_KEY_FILE);
				File publicKeyFile = new File(RsaKeyUtils.PUBLIC_KEY_FILE);

				// Create files to store public and private key
				if (privateKeyFile.getParentFile() != null) {
					privateKeyFile.getParentFile().mkdirs();
				}
				privateKeyFile.createNewFile();

				if (publicKeyFile.getParentFile() != null) {
					publicKeyFile.getParentFile().mkdirs();
				}
				publicKeyFile.createNewFile();

				// Saving the Public key in a file
				ObjectOutputStream publicKeyOS = new ObjectOutputStream(
						new FileOutputStream(publicKeyFile));
				publicKeyOS.writeObject(key.getPublic());
				publicKeyOS.close();

				// Saving the Private key in a file
				ObjectOutputStream privateKeyOS = new ObjectOutputStream(
						new FileOutputStream(privateKeyFile));
				privateKeyOS.writeObject(key.getPrivate());
				privateKeyOS.close();


			//}

			final String originalText = "The saurav crypto ";
			ObjectInputStream inputStream = null;

			// Encrypt the string using the public key
			inputStream = new ObjectInputStream(new FileInputStream(RsaKeyUtils.PUBLIC_KEY_FILE));
			final PublicKey publicKey = (PublicKey) inputStream.readObject();
			RsaEncryption rsaEncryption=new RsaEncryption();
			final byte[] cipherText = rsaEncryption.encrypt(originalText, publicKey);

			// Decrypt the cipher text using the private key.
			inputStream = new ObjectInputStream(new FileInputStream(RsaKeyUtils.PRIVATE_KEY_FILE));
			final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
			RsaKeyDecryption rsaDecryption=new RsaKeyDecryption();

			final String plainText = rsaDecryption.decrypt(cipherText, privateKey);

			// Printing the Original, Encrypted and Decrypted Text
			System.out.println("Original Encryption data: " + originalText);
			System.out.println("Encrypted data ::"+RsaKeyUtils.bytesToHex(cipherText));
			System.out.println("Decrypted: " + plainText);

		} catch (Exception e) {
			e.printStackTrace();

		}
	}
}
