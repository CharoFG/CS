package copsas;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.*;
import java.io.FileWriter;

public class encripta {
    public static void main(String args) throws Exception {

        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        File inputFile = new File(args);

        String[] sep = key.toString().split("@");
        String keyString = sep[1];
        encrypt(inputFile, keyString);
        inputFile.delete();
    }

    private static byte[] getHashInBytes(String key) throws NoSuchAlgorithmException {
        byte[] keyHash;
        final MessageDigest md = MessageDigest.getInstance("SHA-512");
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
