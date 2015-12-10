import java.lang.StringBuilder;

public class LookAndSay {
 
    public static void main(String[] args) {
        String s;
        if(args.length == 2) {
            s = args[0];
            for(int i = 0; i < Integer.parseInt(args[1]); i++){
                s = LookAndSay(s);
            }
            System.out.println("The string is: " +s + " and is " + s.length() + " long.");
        } else {
            System.out.println("Incorrect input");
        }
    }

    public static String LookAndSay(String s){
        int i = 0;
        int count = 1;
        StringBuilder output = new StringBuilder();

        while (i < s.length()){
            char c = s.charAt(i);

            while ( i < s.length()-1 && c == s.charAt(i+1)){
                count++;
                i++;
            } 
            output.append(count);
            count = 1;
            output.append(c);
            i++;
        }
        return output.toString();
    }
}