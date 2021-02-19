
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.SecureRandom;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinit
 */


public class RSAKeyGenerator {
    private KeyPairGenerator keyGen;
    private KeyPair pair;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    
    public RSAKeyGenerator(int keylength) throws NoSuchAlgorithmException, NoSuchProviderException {
        SecureRandom secureRandom = new SecureRandom();
        this.keyGen = KeyPairGenerator.getInstance("RSA");
        this.keyGen.initialize(keylength,secureRandom);
    }

    public void generateKeys() {
        this.pair = this.keyGen.generateKeyPair();
        this.privateKey = pair.getPrivate();
        this.publicKey = pair.getPublic();
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }
}
