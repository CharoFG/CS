import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class prueba {
    public static void main(String[] args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        File inputFile = new File("C:/Users/fran_/desktop/CS/seguridad/files/filesToEncrypt/texto.txt");
        File inputFile1 = new File("C:/Users/fran_/desktop/CS/seguridad/files/filesToEncrypt/texto.txt.enc");

        byte[] iv = new byte[16];
        new SecureRandom().nextBytes(iv);
        IvParameterSpec ivdef = new IvParameterSpec(iv);
        System.out.println(key);

        Cipher ObjetoCifrado =  Cipher.getInstance("AES/CBC/PKCS5Padding");
        ObjetoCifrado.init(Cipher.ENCRYPT_MODE, key);
        System.out.println(ObjetoCifrado.doFinal());
        

        String[] sep = key.toString().split("@");
        System.out.println(sep[1]);
        String keyString = sep[1];
        encrypt(inputFile, keyString);
        decrypt(inputFile1, keyString);
    }

    private static byte[] getHashInBytes(String key) throws NoSuchAlgorithmException {
        byte[] keyHash;

        final MessageDigest md = MessageDigest.getInstance("AES");
        keyHash = md.digest(key.getBytes());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < keyHash.length; i++) {
            sb.append(Integer.toString((keyHash[i] & 0xff) + 0x100, 16).substring(1));
        }
        String hashOfPassword = sb.toString();
        return hashOfPassword.getBytes();
    }

    public static void encrypt(File file, String key) {
        byte[] keyHash;
        if (!file.isDirectory()) {
            try {
                keyHash = getHashInBytes(key);

                FileWriter keyFile = new FileWriter(file.getParent().concat("/key.txt"));
                keyFile.write(key);
                keyFile.close();

                File destinationFile = new File(file.getAbsolutePath().concat(".enc"));
                if (destinationFile.exists()) {
                    destinationFile.delete();
                    destinationFile = new File(file.getAbsolutePath().concat(".enc"));
                }

                BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                FileOutputStream fileWriter = new FileOutputStream(destinationFile, true);

                // Escribimos el archivo con la clave generada aleatoriamente

                fileWriter.write(keyHash, 0, 128);

                // encriptamos el contenido y lo escribimos en el "destinationFile"
                byte[] buffer = new byte[262144];
                int bufferSize = buffer.length;
                int keySize = key.length();
                while (fileReader.available() > 0) {
                    int bytesCopied = fileReader.read(buffer);
                    for (int i = 0, keyCounter = 0; i < bufferSize; i++, keyCounter %= keySize) {

                        buffer[i] += key.toCharArray()[keyCounter];
                    }

                    fileWriter.write(buffer, 0, bytesCopied);
                    System.out.println("des file length= " + destinationFile.length());
                }
                fileReader.close();
                fileWriter.close();

            } catch (SecurityException e) {
                new Exception("Ha ocurrido un error con la seguridad del archivo. Inténtelo de nuevo.", e);
            } catch (FileNotFoundException e) {
                new Exception("No se ha podido encontrar el archivo seleccionado.", e);
            } catch (IOException e) {
                new Exception("No se ha podido leer o escribir ese archivo. Inténtelo de nuevo.", e);
            } catch (Exception e) {
                new Exception("Se ha producido un error en el sistema. Inténtelo de nuevo", e);
            }

        }
    }

    public static void decrypt(File file, String key) {
        if (!file.isDirectory()) {
            try {

                File destinationFile = new File(
                file.getAbsolutePath().substring(0, file.getAbsolutePath().length() - 4));

                BufferedInputStream fileReader = new BufferedInputStream(new FileInputStream(file.getAbsolutePath()));
                FileOutputStream fileWriter = new FileOutputStream(destinationFile);

                // desencriptamos el contenido y lo escribimos en el "destinationFile"
                byte[] buffer = new byte[262144];
                int bufferSize = buffer.length;
                int keySize = key.length();
                for (int i = 0; i < 128; i++) {
                    if (fileReader.available() > 0) {
                        fileReader.read();
                    }
                }
                while (fileReader.available() > 0) {
                    int bytesCopied = fileReader.read(buffer);
                    for (int i = 0, keyCounter = 0; i < bufferSize; i++, keyCounter %= keySize) {

                        buffer[i] -= key.toCharArray()[keyCounter];
                    }

                    fileWriter.write(buffer, 0, bytesCopied);
                    System.out.println("des file length= " + destinationFile.length());

                }
                fileReader.close();
                fileWriter.close();

            } catch (SecurityException e) {
                new Exception("Ha ocurrido un error con la seguridad del archivo. Inténtelo de nuevo.", e);
            } catch (FileNotFoundException e) {
                new Exception("No se ha podido encontrar el archivo seleccionado.", e);
            } catch (IOException e) {
                new Exception("No se ha podido leer o escribir ese archivo. Inténtelo de nuevo.", e);
            } catch (Exception e) {
                new Exception("Se ha producido un error en el sistema. Inténtelo de nuevo", e);
            }
        }
    }

}