package copsas;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class desencripta {
    public static void main(String args, String llave) throws Exception {
        File inputFile1 = new File(args);
        decrypt(inputFile1, llave);

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
