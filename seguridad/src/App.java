import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

import javax.crypto.*;

public class App {

    public static void main(String[] args) throws Exception {
        String cadena="compresionseguridad";
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        Encriptar(cadena,key);
        Desencriptar();
    }

        public static String Encriptar(String cad, SecretKey key) throws NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String resultado="";
        byte [] datosEnc;
        byte [] Encriptados;
        Cipher ObjetoCifrado =  Cipher.getInstance("AES/CBC/PKCS5Padding");

        datosEnc = cad.getBytes("UTF-8");
        ObjetoCifrado.init(Cipher.ENCRYPT_MODE, key);
        Encriptados = ObjetoCifrado.doFinal(datosEnc);
        resultado = Base64.getEncoder().encodeToString(Encriptados);
        
        System.out.println(datosEnc);
        System.out.println(resultado);

        return resultado;
    }

    public static String Desencriptar(){
        String resultado= "";
        
        return resultado;
    }

}

