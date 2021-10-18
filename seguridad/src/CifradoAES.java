import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import java.io.*;

public class CifradoAES {
    
    /*VARIABLES*/
    private SecretKeySpec sKeySpec;
    private Cipher objetoCifrado;
    private IvParameterSpec iv;
    public static final String sIv = "AES/CBC/PKS5Padding";


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
    }
}
