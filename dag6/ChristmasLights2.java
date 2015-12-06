import java.io.FileReader;
import java.io.BufferedReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class ChristmasLights2{
    private int[][] grid;
    private int lightsOn;
    
    public ChristmasLights2(){
        grid = new int[1000][1000];
        lightsOn = 0;
        
    }
	public static void main(String args[]) throws Exception{
        
        ChristmasLights2 lights = new ChristmasLights2();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String s = br.readLine();
        //String s = "toggle 0,0 through 0,0";
        Pattern task = Pattern.compile("[^0-9]*");
        Pattern numbers = Pattern.compile("\\d+");
        Matcher operation;
        Matcher digits;
        
        operation = task.matcher(s);
        digits = numbers.matcher(s);
        
        

        while(s != null){
            operation = task.matcher(s);
            digits = numbers.matcher(s);
            
            int[] coordinates = new int[4]; //x1, y1, x2, y2
            for (int i = 0; i <= 3; i++) {
                digits.find();
                coordinates[i] = Integer.parseInt(digits.group());
            }
            
            operation.find();
            switch (operation.group()) {
                case "toggle ": lights.controller(coordinates[0], coordinates[2], coordinates[1], coordinates[3], 1);
                    break;
                case "turn on ": lights.controller(coordinates[0], coordinates[2], coordinates[1], coordinates[3], 2);
                    break;
                case "turn off ": lights.controller(coordinates[0], coordinates[2], coordinates[1], coordinates[3], 3);
                    break;
                default:
                    break;
            }
            s = br.readLine();
        }
        
        System.out.println("Amount of lights on: " + lights.amountOfLights());
	}
    
    public int amountOfLights(){
        return lightsOn;
    }

    public void controller(int x1, int x2, int y1, int y2, int control){
        while (y1 <= y2){
            for (int x = x1; x <= x2; x++){

                if(control == 1) {
                    turnOn(x, y1, 2);
                } else if (control == 2){
                    turnOn(x, y1, 1);
                } else {
                    turnOff(x, y1);
                }
            }
            y1++;
        }
    }
    
    private void turnOn(int x, int y, int amount){
        lightsOn += amount;
        grid[x][y] += amount;
    }
    
    private void turnOff(int x, int y){
        if(grid[x][y] > 0){
            lightsOn--;
            grid[x][y] = grid[x][y] -1;
        }
    }
}