import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.io.UnsupportedEncodingException;
import java.util.Base64;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class App {

    public static void main(String[] args) throws Exception {
        String cadena="compresionseguridad";
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        Encriptar(cadena,key);
        Desencriptar(key);

    }

    public static String Encriptar(String cad, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
    String resultado="";
    byte [] datosEnc;
    byte [] Encriptados;
    byte[] keybytes;
    keybytes = key.getEncoded();
    Cipher ObjetoCifrado =  Cipher.getInstance("AES/CBC/PKCS5Padding");
    SecretKeySpec keySpec = new SecretKeySpec(keybytes, "AES");

    datosEnc = cad.getBytes("UTF-8");
    ObjetoCifrado.init(Cipher.ENCRYPT_MODE, keySpec);
    Encriptados = ObjetoCifrado.doFinal(datosEnc);
    resultado = Base64.getEncoder().encodeToString(Encriptados);
    
    System.out.println(resultado);
    System.out.println(key.getAlgorithm());

    return resultado;
}

    public static String Desencriptar(SecretKey key){
        String resultado= "";

        
        return resultado;
    }

}

