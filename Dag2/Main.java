import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Arrays;

public class Main{
    
	public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        int paper = 0;
        int ribbon = 0;
        String s = br.readLine();
    
        while (s != null){
            string[] measures = s.split("x"); // length, width, height
            paper += addPackage(measures);
            ribbon += addRibbon(measures);
            s = br.readLine();
        }
        System.out.println("Total amount of square feet wrapping paper needed is:" + paper);
        System.out.println("Total amount of feet ribbon:" + ribbon);

    }
    
    private static int addPackage(String[] measures){
        int l = Integer.parseInt(measures[0]);
        int w = Integer.parseInt(measures[1]);
        int h = Integer.parseInt(measures[2]);
        int lw = l*w;
        int wh = w*h;
        int hl = h*l;
        int result = 2*lw + 2*wh + 2*hl;
        
        int min = lw;
        if (wh < min) min = wh;
        if (hl < min) min = hl;

        result += min;
        
        int[] myIntArray = {1,2,3};
        return result;
    }
    
    private static int addRibbon(String[] measures){
        int ribbon = 0;
        int[] myIntArray = {Integer.parseInt(measures[0]),Integer.parseInt(measures[1]),Integer.parseInt(measures[2])};
        Arrays.sort(myIntArray);
        ribbon = 2*myIntArray[0] + 2*myIntArray[1] + myIntArray[0]*myIntArray[1]*myIntArray[2];
        
        return ribbon;
    }
}