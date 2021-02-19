
import java.util.Base64;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author vinit
 */
public class KeyEncoderDecoder {
    private String keyStr;
    private byte[] keyBytes;
    
    public String keysToString(byte[] key){
        this.keyStr = Base64.getEncoder().encodeToString(key);
        return this.keyStr;
    }
    public byte[] stringToKeys(String str){
        this.keyBytes = Base64.getDecoder().decode(str);
        return this.keyBytes;
    }
}
