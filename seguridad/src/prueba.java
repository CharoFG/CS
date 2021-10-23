import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;


public class prueba{
	public static void main(String[] args) throws Exception {

		KeyGenerator keyGen = KeyGenerator.getInstance("AES");
		keyGen.init(128);
		SecretKey key = keyGen.generateKey();
        String llave = "ayer al mediodia me hice unos macarrones de puta madre";
		File inputFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/filesToEncrypt/pdf.pdf");
		File inputFile1 = new File("C:/Users/fran_/desktop/CS/seguridad/files/filesToEncrypt/pdf.pdf.enc");
		File outputFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/txt/encrypted.txt");
		File outputFile2 = new File("C:/Users/fran_/desktop/CS/seguridad/files/txt/unencrypted2.txt");

		byte[] iv = new byte[16];
		new SecureRandom().nextBytes(iv);
		IvParameterSpec ivdef = new IvParameterSpec(iv);

		//encryptFile("AES/CBC/PKCS5Padding", key, ivdef, inputFile, outputFile);
		
        String[] sep = key.toString().split("@");
        System.out.println(sep[1]);
        String keyString = sep[1];
        encrypt(inputFile, keyString);
        decrypt(inputFile1, keyString);
		//decryptFile("AES/CBC/PKCS5Padding", key, ivdef, outputFile, outputFile2);
		//decrypt(inputFile1, "AES/CBC/PKCS5Padding", key, ivdef);

	}

	/*public static void encrypt(File inputFile, String algorithm, SecretKey key, IvParameterSpec iv) throws IOException, NoSuchPaddingException,
	NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException
    {
		String keyString = key.toString(); 
		byte[] keyByte = key.getEncoded();
		SecretKeySpec keySpec = new SecretKeySpec(keyByte, algorithm);
		File destinationFile;
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.ENCRYPT_MODE, key, iv);
        if(!inputFile.isDirectory())
        {
			try{
                //keyHash=getHashInBytes(key);
                
                destinationFile=new File(inputFile.getAbsolutePath().concat(".enc"));
                if(destinationFile.exists())
                {
                    destinationFile.delete();
                    destinationFile=new File(inputFile.getAbsolutePath().concat(".enc"));
                }
                
                BufferedInputStream fileReader=new BufferedInputStream(new FileInputStream(inputFile.getAbsolutePath()));
                FileOutputStream fileWriter=new FileOutputStream (destinationFile, true);
                
                //writing key hash to file
                fileWriter.write(keySpec.getEncoded(), 0, 128); 
                
                //encrypting content & writing
                byte[] buffer = new byte[262144];
				int bufferSize=buffer.length;
                int keySpecSize=keySpec.toString().length();
                while(fileReader.available()>0)
                {
                    int bytesCopied=fileReader.read(buffer);

                    for(int i=0,keyCounter=0; i<bufferSize; i++, keyCounter%=keySpecSize )
                    {

                        buffer[i]+=keySpec.toString().toCharArray()[keyCounter];
                    }

					byte[] output = cipher.update(buffer, 0, bytesCopied);

                    fileWriter.write(output, 0, bytesCopied);
                }
                fileReader.close();
                fileWriter.close();
			}  
			catch (SecurityException e)
            {
				new Exception(e.getMessage());
            }
            catch (FileNotFoundException e)
            {
				new Exception(e.getMessage());
			}
            catch (IOException e)
            {
				new Exception(e.getMessage());
            }
            catch (Exception e)
            {
				new Exception(e.getMessage());
            }
            
        }
    }*/
	/*public static void decrypt(File inputFile1, String algorithm, SecretKey key, IvParameterSpec iv) throws IOException, NoSuchPaddingException,
	NoSuchAlgorithmException, InvalidAlgorithmParameterException, InvalidKeyException,
	BadPaddingException, IllegalBlockSizeException
    {
		String keyString = key.toString(); 
		byte[] keyByte = key.getEncoded();
		File destinationFile;
		Cipher cipher = Cipher.getInstance(algorithm);
		cipher.init(Cipher.DECRYPT_MODE, key, iv);
		if(!inputFile1.isDirectory())
        {
            try
            {
                //keyHash=getHashInString(key);
                
                    destinationFile=new File(inputFile1.getAbsolutePath().substring(0, inputFile1.getAbsolutePath().length()-4));
                
                    BufferedInputStream fileReader=new BufferedInputStream(new FileInputStream(inputFile1.getAbsolutePath()));
                    FileOutputStream fileWriter=new FileOutputStream (destinationFile);
                
                     //decrypting content & writing
                    byte[] buffer = new byte[262144];
                    int bufferSize=buffer.length;
                    int keySize=keyString.length();
                    
                    for(int i=0; i<128; i++)
                    {
                        if(fileReader.available()>0)
                        {
                            fileReader.read();
                        }
                    }
                    while(fileReader.available()>0)
                    {
                        int bytesCopied=fileReader.read(buffer);
                        for(int i=0,keyCounter=0; i<bufferSize; i++, keyCounter%=keySize )
                        {

                            buffer[i]-=keyString.toCharArray()[keyCounter];
                        }

                        fileWriter.write(buffer, 0, bytesCopied);
                        System.out.println("des file length= "+destinationFile.length());
                    
                    }
                    fileReader.close();
                    fileWriter.close();

                }
                
            
            catch (SecurityException e)
            {
				new Exception(e.getMessage());
            }
            catch (FileNotFoundException e)
            {
                new Exception(e.getMessage());
            }
            catch (IOException e)
            {
                new Exception(e.getMessage());
            }            
            catch (Exception e)
            {
				new Exception(e.getMessage());
            }            
        }
    }*/

	/*public static void encryptFile(String algorithm, SecretKey key, IvParameterSpec iv,
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
	}*/
    private static byte[] getHashInBytes(String key) throws NoSuchAlgorithmException
    {
        byte[] keyHash;
        final MessageDigest md = MessageDigest.getInstance("SHA-512");
                keyHash = md.digest(key.getBytes());
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< keyHash.length ;i++)
                {
                    sb.append(Integer.toString((keyHash[i] & 0xff) + 0x100, 16).substring(1));
                }
                String hashOfPassword = sb.toString();
                System.out.println("hashOfPassword length= "+hashOfPassword.length());
                System.out.println("hashOfPassword = " +hashOfPassword);
                return hashOfPassword.getBytes();
                
    }
	public static void encrypt(File file, String key)
    {
        byte[] keyHash;
        if(!file.isDirectory())
        {
            try
            {
                keyHash=getHashInBytes(key);
                
                File destinationFile=new File(file.getAbsolutePath().concat(".enc"));
                if(destinationFile.exists())
                {
                    destinationFile.delete();
                    destinationFile=new File(file.getAbsolutePath().concat(".enc"));
                }
                
                BufferedInputStream fileReader=new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                FileOutputStream fileWriter=new FileOutputStream (destinationFile, true);
                
                //writing key hash to file

                fileWriter.write(keyHash, 0, 128); 
                //encrypting content & writing
                byte[] buffer = new byte[262144];
                int bufferSize=buffer.length;
                int keySize=key.length();
                while(fileReader.available()>0)
                {
                    int bytesCopied=fileReader.read(buffer);
                    for(int i=0,keyCounter=0; i<bufferSize; i++, keyCounter%=keySize )
                    {

                        buffer[i]+=key.toCharArray()[keyCounter];
                    }
                    
                    fileWriter.write(buffer, 0, bytesCopied);                    
                    System.out.println("des file length= "+destinationFile.length());
                }
                fileReader.close();
                fileWriter.close();
                
            } 
            catch (SecurityException e)
            {
                new Exception("File Security Error!!!", e);
            }
            catch (FileNotFoundException e)
            {
                new Exception("File Not Found!!!", e);
            }
            catch (IOException e)
            {
                new Exception("Can Not Read or Write file!!!", e);
            }
            catch (Exception e)
            {
                new Exception("Unexpected System Error!", e);
            }
            
        }
    }

    private static String getHashInString(String key) throws NoSuchAlgorithmException
    {
        byte[] keyHash;
        final MessageDigest md = MessageDigest.getInstance("SHA-512");
                keyHash = md.digest(key.getBytes());
                StringBuilder sb = new StringBuilder();
                for(int i=0; i< keyHash.length ;i++)
                {
                    sb.append(Integer.toString((keyHash[i] & 0xff) + 0x100, 16).substring(1));
                }
                String hashOfPassword = sb.toString();
                System.out.println("hashOfPassword length= "+hashOfPassword.length());
                System.out.println("hashOfPassword = " +hashOfPassword);
                return hashOfPassword;
                
    }

    public static void decrypt(File file, String key)
    {
        if(!file.isDirectory())
        {
            try
            {
                
                    File destinationFile=new File(file.getAbsolutePath().substring(0, file.getAbsolutePath().length()-4));
                
                    BufferedInputStream fileReader=new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                    FileOutputStream fileWriter=new FileOutputStream (destinationFile);
                
                     //decrypting content & writing
                    byte[] buffer = new byte[262144];
                    int bufferSize=buffer.length;
                    int keySize=key.length();
                    for(int i=0; i<128; i++)
                    {
                        if(fileReader.available()>0)
                        {
                            fileReader.read();
                        }
                    }
                    while(fileReader.available()>0)
                    {
                        int bytesCopied=fileReader.read(buffer);
                        for(int i=0,keyCounter=0; i<bufferSize; i++, keyCounter%=keySize )
                        {

                            buffer[i]-=key.toCharArray()[keyCounter];
                        }

                        fileWriter.write(buffer, 0, bytesCopied);
                        System.out.println("des file length= "+destinationFile.length());
                    
                    }
                    fileReader.close();
                    fileWriter.close();

                
            }
            catch (SecurityException e)
            {
                new Exception("File Security Error!!!", e);
            }
            catch (FileNotFoundException e)
            {
                new Exception("File Not Found!!!", e);
            }
            catch (IOException e)
            {
                new Exception("Can Not Read or Write file!!!", e);
            }            
            catch (Exception e)
            {
                new Exception("Unexpected System Error!", e);
            }            
        }
    }

}