package pruebas;
import encriptado.*;
import java.io.File;

public class probandoAES {
    public static void main(String[] args) throws Exception {
        CifradoAES objetoAES1;
        objetoAES1 = new CifradoAES();
        String path = "C:/Users/Charo/Desktop/AEncriptar.txt";

        File ficheroleido = new File(path);
        objetoAES1.encriptar(ficheroleido);

        System.out.println("GENERANDO CLAVE AES1");
        System.out.println(objetoAES1.getPrivateKey());
    }
}
