
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinit
 */
public class AESKeyGenerator {
    private SecretKey aesKey;
    private final KeyGenerator generator;
    public AESKeyGenerator(int keyLength ) throws NoSuchAlgorithmException{
        SecureRandom secureRandom = new SecureRandom();
        this.generator = KeyGenerator.getInstance("AES");
        this.generator.init(keyLength,secureRandom);
    }
    public SecretKey getKey(){
        this.aesKey = this.generator.generateKey();
        return this.aesKey;
    }
}
