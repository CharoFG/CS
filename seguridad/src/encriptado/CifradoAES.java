package encriptado;
import javax.crypto.*;
import javax.crypto.spec.*;
import java.util.Base64;
import java.io.*;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.util.Random;

public class CifradoAES {
    
    /*VARIABLES*/
    public static final String sIv = "AES/CBC/PKCS5Padding";
    private SecretKeySpec sKeySpec;
    private Cipher objetoCifrado;
    IvParameterSpec ivspec;

    /*CONSTRUCTOR*/
    public CifradoAES() throws Exception {
        //En este metodo constructor, primer creamos un objeto generador de llaves de tipos AES
        //y lo inicializamos con un tamanyo de 128 bytes. Hecho esto generamos un objeto de tipo cifrado
        //(Cipher) de tipo AES. 
        KeyGenerator generadorLlaves = KeyGenerator.getInstance("AES");
        generadorLlaves.init(128);
        objetoCifrado = Cipher.getInstance(sIv);

        //Ahora generamos una llave, la convertimos en un array de bytes y creamos un 
        //sKeySpec con esa llave
        SecretKey llaveSecreta = generadorLlaves.generateKey();
        byte[] llaveBytes = llaveSecreta.getEncoded();

        //Creamos la llave secreta especificando que usamos el algoritmo AES
        //sKeySpec sirve para construir una secretKey desde un array de bytes ,sin tener que usar un 
        //provider-based SecretKeyFactory
        //tambien sirve para especificar que el algoritmo que queremos utilizar es el AES
        sKeySpec = new SecretKeySpec(llaveBytes, "AES");
        
        //Con esto generamos un torrente de bytes random que luego utilizaremos
        byte[] iv = new byte[128/8];
        Random srandom = new Random();
        srandom.nextBytes(iv);
        
        ivspec = new IvParameterSpec(iv);

    }


    /*Metodo que encripta un archivo cuyo path le pasamos por parametro
    Devuelve el mensaje encriptado en String y el nombre del archivo*/
    public String[] encriptar (String path) throws Exception {
        File archivo = new File(path);
        String[] mensajeEncriptado = new String[2];
        mensajeEncriptado[1] = archivo.getName();

        //Intentamos inizializar el sistema para que encripte, utilizando la llave que hemos creado antes
        objetoCifrado.init(Cipher.ENCRYPT_MODE, sKeySpec, ivspec);
        //Encriptamoos todo el mensaje
        byte[] mensajeBytes = new byte[(int) archivo.length()];
        //Creamos un objeto de input stream para leer el fichero y metemos lo que leamos al array de bytes
        //finalmentecerramos el fichero
        FileInputStream ficheroInput = new FileInputStream(archivo);
        ficheroInput.read(mensajeBytes);
        ficheroInput.close();

        byte[] mensajeBytesEncriptado = objetoCifrado.doFinal(mensajeBytes);

        mensajeEncriptado[0] = new String(Base64.getEncoder().encode(mensajeBytesEncriptado));

        crearArchivoEncriptado(mensajeEncriptado[0], path);
        return mensajeEncriptado;
    }

    //Crea un archivo encriptado, para ello recoge un string con el mensaje encriptado, lo pasa a bytes y lo escribe en otro archivo
    public void crearArchivoEncriptado(String mensajeEncriptado, String path) throws IOException{
        String pathNuevo = path.concat(".enc");
        FileOutputStream outputStream = new FileOutputStream(pathNuevo);
        byte[] strToBytes = mensajeEncriptado.getBytes();
        outputStream.write(strToBytes);

        outputStream.close();
    }

    public void desencriptarArchivo(String s) throws InvalidKeyException, InvalidAlgorithmParameterException, IllegalBlockSizeException, BadPaddingException, IOException{
        objetoCifrado.init(Cipher.DECRYPT_MODE, sKeySpec, ivspec);

        byte[] desencriptado = objetoCifrado.doFinal(Base64.getDecoder().decode(s));

        FileOutputStream outputStream = new FileOutputStream("C:/Users/Charo/Desktop/Ricky2.jpg");
        outputStream.write(desencriptado);

        outputStream.close();
        
    }
    public String getPrivateKey(){
        byte[] llavePrivada = sKeySpec.getEncoded();
        return Base64.getEncoder().encodeToString(llavePrivada);
    }
}
