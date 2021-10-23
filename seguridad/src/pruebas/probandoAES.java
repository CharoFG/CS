package pruebas;
import encriptado.*;
import java.io.File;

public class probandoAES {
    public static void main(String[] args) throws Exception {
        CifradoAES objetoAES1;
        objetoAES1 = new CifradoAES();
        String path = "C:/Users/Charo/Desktop/Ricky.jpg";
        String pathDesencriptar = "C:/Users/Charo/Desktop/Ricky.jpg.enc";

        File ficheroleido = new File(path);
        String[] prueba = objetoAES1.encriptar(path); //El mensaje esta en [0] - Probamos encriptar

        objetoAES1.desencriptarArchivo(prueba[0]);

        System.out.println("GENERANDO CLAVE AES1");
        System.out.println(objetoAES1.getPrivateKey());
    }
}
