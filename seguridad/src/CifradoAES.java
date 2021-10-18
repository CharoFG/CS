import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import java.io.*;
import java.util.Random;

public class CifradoAES {
    
    /*VARIABLES*/
    public static final String sIv = "AES/CBC/PKS5Padding";
    private SecretKeySpec sKeySpec;
    private Cipher objetoCifrado;
    IvParameterSpec ivspec;

    /*CONSTRUCTOR*/
    public CifradoAES() throws Exception {
        //Creamos un generador de llaves de tipo AES
        KeyGenerator generadorLlaves = KeyGenerator.getInstance("AES");
        //Le especificamos que queremos tamanyo de 128 bytes
        generadorLlaves.init(128);
        //Se genera un objeto de cifrado AES
        objetoCifrado = Cipher.getInstance(sIv);
        //Generamos una llave secreta
        SecretKey llaveSecreta = generadorLlaves.generateKey();
        //Pasamos la llave a bytes
        byte[] llaveBytes = llaveSecreta.getEncoded();
        //Creamos la llave secreta especificando que usamos el algoritmo AES
        sKeySpec = new SecretKeySpec(llaveBytes, "AES");

        //Con esto generamos un torrente de bytes random que luego utilizaremos
        byte[] iv = new byte[128/8];
        Random srandom = new Random();
        srandom.nextBytes(iv);
        ivspec = new IvParameterSpec(iv);
    }


    /*Metodo que encripta un archivo que le pasamos por parametro
    Devuelve el mensaje encriptado en String y el nombre del archivo*/
    public String[] encriptar (File archivo) throws Exception {
        String[] mensajeEncriptado = new String[2];
        mensajeEncriptado[1] = archivo.getName();

        return mensajeEncriptado;
    }
}
