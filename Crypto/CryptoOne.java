import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CryptoOne {
    public static String getHash(String word, int x) throws NoSuchAlgorithmException {
        String oldWord = word;
        String newWord = null;
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        for (int i = 0; i < x; i++) {
            if (newWord != null) {
                digest.update(newWord.getBytes());
            } else {
                digest.update(oldWord.getBytes());
            }
            newWord = getHex(digest.digest());
        }
        System.out.println("Answer: " + newWord + "\n");
        return newWord;
    }

    public static String getHex(byte[] bytes) {
        StringBuffer sb = new StringBuffer();
        for (byte b : bytes) {
            sb.append(Integer.toString((b & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println("INIT\n");
        try {
            getHash("hello", 1);
            getHash("bitcoin", 2);
        } catch (NoSuchAlgorithmException ne) {
            ne.printStackTrace();
        }
    }
}
