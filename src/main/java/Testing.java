
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinit
 */
public class Testing {
    public static void main(String[] args) {
        RSAKeyGenerator RKG;
        try {
            RKG = new RSAKeyGenerator(1024);
            RKG.generateKeys();
            String publickey, privatekey;
            KeyEncoderDecoder KED;
            KED = new KeyEncoderDecoder();
            publickey = KED.keysToString(RKG.getPublicKey().getEncoded());
            privatekey = KED.keysToString(RKG.getPrivateKey().getEncoded());
            
            System.out.println("KeyPair/publicKey--"+publickey);
            System.out.println("KeyPair/privateKey--"+privatekey);
            
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        }
    }
}
