package Javarama_local;

import java.security.MessageDigest;

/**
 *  SHA.java
 * @author Isaac Hutchinson
 * @version 0.0.1
 */
public class SHA {

    public SHA() {
    }


    /**
     * The SHAHash method is a SHA-256 hashing method used to hash a given text to an unreadable format.
     *
     * @param toHash A string to hash
     * @return SHA-256 hash string
     * @throws Exception No SHA-256 hashing algorithm
     * Reference: https://www.mkyong.com/java/java-sha-hashing-example/
     */

    public String SHAHash(String toHash) throws Exception {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        md.update(toHash.getBytes());
        byte byteData[] = md.digest();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < byteData.length; i++) {
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
