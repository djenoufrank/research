/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package g55301g48982.chatserver.common;

import java.nio.charset.StandardCharsets;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.crypto.Cipher;
/**
 *
 * @author User
 */
public class Security {
    
    public static byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr = SecureRandom.getInstance("SHA1PRNG");
        byte[] salt = new byte[16];
        sr.nextBytes(salt);
        return salt;
    }

    public static String getHashPasswordSalt(String passwordToHash, byte[] salt)
            throws NoSuchAlgorithmException {      
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(salt);
            byte[] bytes = md.digest(passwordToHash.getBytes());
            StringBuilder stringBuilderForSalt = new StringBuilder();
            for (byte aByte : bytes) {
                stringBuilderForSalt.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
   
        return stringBuilderForSalt.toString();
    }

    public static KeyPair getKeys() throws NoSuchAlgorithmException {
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
        keyPairGenerator.initialize(2048);
        return keyPairGenerator.generateKeyPair();
    }
    
     public static byte[] cipherMessage(String message, String publicKeyDestinataire)
            throws Exception {
     
        byte[] keyAsByte = Base64.getDecoder().decode(publicKeyDestinataire);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        PublicKey byteAsKey = factory.generatePublic(new X509EncodedKeySpec(keyAsByte));
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, byteAsKey);
        cipher.update(message.getBytes());
        return cipher.doFinal();
    }

   public static String decipherMessage(byte[] message, String privateKey)
            throws Exception {
                    byte[] keyAsByte = Base64.getDecoder().decode(privateKey);
                    KeyFactory factory = KeyFactory.getInstance("RSA");
    PrivateKey byteAsKey = factory.generatePrivate(new PKCS8EncodedKeySpec(keyAsByte));

 Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, byteAsKey);
    byte[] decryptedMessage = cipher.doFinal(message);
    String affiche=new String(decryptedMessage, StandardCharsets.UTF_8);
       return affiche;
    }

    public static List<String> decipherHistoryMessage(List<String> histories, String privateKey) {
         List<String> listHistories = new ArrayList<>();
         for(String message :histories){
             try {
                 listHistories.add(decipherMessage(Base64.getDecoder().decode(message),privateKey));
             } catch (Exception ex) {
                 Logger.getLogger(Security.class.getName()).log(Level.SEVERE, null, ex);
             }
        }
         return listHistories;
    }
}
