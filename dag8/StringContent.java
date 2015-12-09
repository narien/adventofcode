import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;
import org.apache.commons.lang3.StringEscapeUtils;

public class StringContent{

	public static void main(String args[]) throws Exception{ // compile code: javac -cp '.:commons-lang3-3.4.jar' StringContent.javamysource.java
        
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        ArrayList<String> list = new ArrayList<String>();
        int codeLength = 0;
        int totalLength = 0;
        int part2Length = 0;
        
        String line = br.readLine();
        while (line != null){
            totalLength += line.length();
            codeLength += line.length();
            part2Length += StringEscapeUtils.escapeJava(line).length()+2;

            int offset = 0;
            
            while (offset < line.length()) {
                int curChar = line.codePointAt(offset);
                offset += Character.charCount(curChar);
                if (curChar == 34) { // if quotation
                    codeLength--;
                } else if (curChar == 92) {  // if slash
                    codeLength--;
                    curChar = line.codePointAt(offset);
                    if (curChar == 120) { // if hex
                        codeLength -= 2;
                        offset += Character.charCount(curChar);
                    } else {
                        offset += Character.charCount(curChar);
                    }
                }
            }
            line = br.readLine();
        }
        System.out.println("Total length is: " + totalLength);
        System.out.println("Code length is: " + codeLength);

        System.out.println("difference is: " + (totalLength - codeLength));
        System.out.println("difference in part 2 is: " + ( part2Length - totalLength));

    }
}


























