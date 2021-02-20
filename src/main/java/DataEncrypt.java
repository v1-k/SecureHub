
import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinit
 */
public class DataEncrypt {
    private Cipher cipher;
    private PrivateKey privateKey;
    private PublicKey publicKey;
    
    
    public DataEncrypt() throws NoSuchAlgorithmException, NoSuchPaddingException{
        this.cipher = Cipher.getInstance("RSA");
    }
    private void setPrivate(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException{
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(key);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.privateKey = kf.generatePrivate(spec);
    }    
    private void setPublic(byte[] key) throws NoSuchAlgorithmException, InvalidKeySpecException{
        X509EncodedKeySpec spec = new X509EncodedKeySpec(key);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        this.publicKey = kf.generatePublic(spec);
    }
    
    private String encryptText(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException{
        this.cipher.init(Cipher.ENCRYPT_MODE, this.privateKey);
        return Base64.getEncoder().encodeToString(cipher.doFinal(str.getBytes()));
        //return Base64.encodeBase64String(cipher.doFinal(str.getBytes("UTF-8")));
    }
    private String decryptText(String str) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        this.cipher.init(Cipher.DECRYPT_MODE, this.publicKey);
        return new String(cipher.doFinal(Base64.getDecoder().decode(str)));
        //return new String(cipher.doFinal(Base64.decodeBase64(str)), "UTF-8");
    }
    public static void main(String[] args) throws NoSuchPaddingException, InvalidKeySpecException, InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
                RSAKeyGenerator RKG;
        try {
            RKG = new RSAKeyGenerator(1024);
            RKG.generateKeys();
            byte[] prvtkey,pblckey;
            String publickey, privatekey;
            KeyEncoderDecoder KED;
            KED = new KeyEncoderDecoder();
            pblckey = RKG.getPublicKey().getEncoded();
            prvtkey = RKG.getPrivateKey().getEncoded();
            publickey = KED.keysToString(pblckey);
            privatekey = KED.keysToString(prvtkey);
            
            System.out.println("KeyPair/publicKey--"+publickey);
            System.out.println("KeyPair/privateKey--"+privatekey);
            
            String text = "This is test";
            DataEncrypt de = new DataEncrypt();
            de.setPrivate(KED.stringToKeys(privatekey));
            de.setPublic(KED.stringToKeys(publickey));
            String encText,decText;
            encText =de.encryptText(text);
            decText =de.decryptText(encText);
            System.out.println("Encrypted Text --"+encText);
            System.out.println("decrypted Text --"+decText);
        } catch (NoSuchAlgorithmException | NoSuchProviderException e) {
            System.err.println(e.getMessage());
        }
    }
    
}
