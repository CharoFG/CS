import javax.crypto.*;

public class App {
    public static void main(String[] args) throws Exception {
        KeyGenerator keyGen = KeyGenerator.getInstance("AES");
        keyGen.init(128);
        SecretKey key = keyGen.generateKey();
        System.out.println(keyGen);
        System.out.println(key);
        
    }
}
