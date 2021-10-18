import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;

public class prueba{
	public static void main(String[] args) throws Exception {
		String cadena="compresionseguridad";
		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey key = keyGen.generateKey();
		File keyFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/key.txt");
		File inputFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/pdf/unencrypted.pdf");
		File outputFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/pdf/encrypted.pdf");
		File outputFile2 = new File("C:/Users/fran_/desktop/CS/seguridad/files/pdf/unencrypted2.pdf");

		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivdef = new IvParameterSpec(iv);
		encryptFile("AES/CBC/PKCS5Padding", key, ivdef, inputFile, outputFile);
		decryptFile("AES/CBC/PKCS5Padding", key, ivdef, outputFile, outputFile2);

	}

	public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
	File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
	NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {

	Cipher cipher = Cipher.getInstance(algorithm);
	cipher.init(Cipher.ENCRYPT_MODE, key, iv);
	FileInputStream inputStream = new FileInputStream(inputFile);
	FileOutputStream outputStream = new FileOutputStream(outputFile);
	byte[] buffer = new byte[64];
	int bytesRead;
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		byte[] output = cipher.update(buffer, 0, bytesRead);
		if (output != null) {
			outputStream.write(output);
		}
	}
	byte[] outputBytes = cipher.doFinal();
	if (outputBytes != null) {
		outputStream.write(outputBytes);
	}
	inputStream.close();
	outputStream.close();
	}
	public static void decryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
	File inputFile, File outputFile) throws IOException, NoSuchPaddingException,
	NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException {

	Cipher cipher = Cipher.getInstance(algorithm);
	cipher.init(Cipher.DECRYPT_MODE, key, iv);
	FileInputStream inputStream = new FileInputStream(inputFile);
	FileOutputStream outputStream = new FileOutputStream(outputFile);
	byte[] buffer = new byte[64];
	int bytesRead;
	while ((bytesRead = inputStream.read(buffer)) != -1) {
		byte[] output = cipher.update(buffer, 0, bytesRead);
		if (output != null) {
			outputStream.write(output);
		}
	}
	byte[] outputBytes = cipher.doFinal();
	if (outputBytes != null) {
		outputStream.write(outputBytes);
	}
	inputStream.close();
	outputStream.close();
	}
}
