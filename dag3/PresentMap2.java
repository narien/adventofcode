import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.InputStream;
import java.io.File;
import java.util.Map;
import java.util.HashMap;
import java.io.IOException;
import java.io.Reader;

public class PresentMap2 {
    Map<Integer, Map<Integer, Integer>> locations;
    
    public PresentMap2 (File file) throws Exception{
        locations = new HashMap<Integer, Map<Integer, Integer>>();
        handleFile(file);
    }
    
	public static void main(String args[]) throws Exception {
        for (String filename : args) {
            File file = new File(filename);
            PresentMap2 pm = new PresentMap2(file);
            
            pm.displayInfo();
        }
    }
    
    private void handleFile(File file) throws IOException {
        try (InputStream in = new FileInputStream(file);
             Reader reader = new InputStreamReader(in);
             // buffer for efficiency
             Reader buffer = new BufferedReader(reader)) {
            handleCharacters(buffer);
        }
    }
    
    private void handleCharacters(Reader reader)throws IOException {
        int r;
        boolean controller = true; //santa true, robot false
        int santaX = 0;
        int santaY = 0;
        int robotX = 0;
        int robotY = 0;
        int x = 0;
        int y = 0;
        
        Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
        temp.put(y, 2);
        locations.put(x, temp);

        while ((r = reader.read()) != -1) {
            if(controller){
                x = santaX;
                y = santaY;
            } else {
                x = robotX;
                y = robotY;
            }
            
            
            
            char ch = (char) r;
            switch (ch) {
                case '<':
                    x--;
                    break;
                case '>':
                    x++;
                    break;
                case 'v':
                    y--;
                    break;
                case '^':
                    y++;
                    break;
                default:
                    
                    break;
            }
            addLocation(x, y);
            if(controller){
                santaX = x;
                santaY = y;
                controller = false;
            } else {
                robotX = x;
                robotY = y;
                controller = true;
            }
        }
    }
    
    public void displayInfo(){
        int i = 0;
        for(int x: locations.keySet()){
            i += locations.get(x).size();
        }
        System.out.println("amount of houses that received presents: " + i);
    }
        
    private void addLocation( int x, int y){
        if (locations.containsKey(x)){
            if(locations.get(x).containsKey(y)){
                locations.get(x).put(y, locations.get(x).get(y) + 1);
            } else {
                locations.get(x).put(y, 1);
            }
        } else {
            Map<Integer, Integer> temp = new HashMap<Integer, Integer>();
            temp.put(y, 1);
            locations.put(x, temp);
        }
    }
}






































