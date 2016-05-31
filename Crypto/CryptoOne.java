import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/*
Given a variable integer X (e.g. 42) and a string word (e.g. "hello"),
where word is iteratively run through a SHA-256 hex digest computation X number of times,
what is the resulting hex digest?

So the answer for X = 1 and word = "hello" would be "2cf24dba5fb0a30e26e83b2ac5b9e29e1b161e5c1fa7425e73043362938b9824".

For X = 2 and word = "bitcoin", the answer would be "a23b7f87e4250b3a64b737f349c06422f752f419cbb25ae9169a6cf1e23f4462".

Please provide a method that can compute the general solution.
 */
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
