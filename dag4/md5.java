import java.security.MessageDigest;
import java.math.BigInteger;

public class md5 {
    
	public static void main(String args[]) throws Exception{
        //String plainText = "ckczppom";
        String plainText = "iwrupvqb";

        String hashText = "48fbdf1af6eb206e65ef98bf8a78ad85"; //just a random hashcode
        int i = 1;
//        while(!(hashText.length() <= 27)){ //part one
        while(!(hashText.length() <= 26)){ //part two

            hashText = md5Hash(plainText + Integer.toString(i));
            i++;
        }
        while(hashText.length() < 32 ){
            hashText = "0"+hashText;
        }
        i--;
        System.out.println("This is the correct hash: " + hashText);
        System.out.println("For plaintext: " + plainText + Integer.toString(i));

    }
    
    
    private static String md5Hash(String plainText) throws Exception{
        MessageDigest m = MessageDigest.getInstance("MD5");
        m.reset();
        m.update(plainText.getBytes());
        byte[] digest = m.digest();
        BigInteger bigInt = new BigInteger(1,digest);
        String hashText = bigInt.toString(16);
        // Now we need to zero pad it if you actually want the full 32 chars.
/**        while(hashText.length() < 32 ){
            hashText = "0"+hashText;
        }**/
        return hashText;
    }
    
    private static boolean hashOK(String hashText){
        System.out.println(hashText);
        if(hashText.substring(0, 4) == ("00000"))    return true;
        return false;
    }
}



































