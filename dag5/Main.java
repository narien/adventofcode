import java.io.FileReader;
import java.io.BufferedReader;


public class Main{
	public static void main(String args[]) throws Exception{
        int niceStrings1 = 0;
        int niceStrings2 = 0;
        
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String s = br.readLine();
//        String s = "aaa";
        while(s != null){
            if(countVowels(s) > 2 && !combo(s) && doublettes(s)) niceStrings1++;
            if(pair(s) && repeat(s)) niceStrings2++;
            s = br.readLine();
        }
        
        System.out.println("Amount of nice strings in problem one: " + niceStrings1);
        System.out.println("Amount of nice strings in problem two: " + niceStrings2);

	}
    
    public static int countVowels(String s){
        return s.replaceAll("[^aeiou]","").length();
    }
    /** returns true if combinations are found**/
    public static boolean combo(String s){
        return s.matches(".*?(ab|cd|pq|xy).*?");
    }
    
    public static boolean doublettes(String s){
        return s.matches(".*?(.)\\1+.*?");
    }
    
    public static boolean pair(String s){
        return s.matches(".*?(..).*?\\1.*?");
    }
    
    public static boolean repeat(String s){
        return s.matches(".*?(.){1}.\\1.*?");
    }
}