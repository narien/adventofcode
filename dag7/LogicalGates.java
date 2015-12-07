import java.io.FileReader;
import java.io.BufferedReader;
import java.util.*;


public class LogicalGates{
    private Map<String, Instruction> instructions;
    
    public LogicalGates(){
        instructions = new HashMap<String, Instruction>();
    }
    
    public void add (String line){
        String[] s = line.split(" ");
        if(line.contains("AND")){
            instructions.put(s[4], new And(s[0], s[2]));
            
        } else if (line.contains("OR")) {
            instructions.put(s[4], new Or(s[0], s[2]));
            
        } else if (line.contains("NOT")){
            instructions.put(s[3], new Not(s[1]));

            
        } else if (line.contains("LSHIFT")){
            instructions.put(s[4], new LShift(s[0], s[2]));

            
        } else if (line.contains("RSHIFT")){
            instructions.put(s[4], new RShift(s[0], s[2]));

            
        } else { //normal input
            instructions.put(s[2], new Input(s[0]));
        }
    }

    public int getWire(String s){
        return instructions.get(s).run();
    }
    
	public static void main(String args[]) throws Exception{
        
        LogicalGates lg = new LogicalGates();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line = br.readLine();
        
        while (line != null){
            lg.add(line);
            line = br.readLine();
        }
        System.out.println("File read, processing");
        System.out.println(lg.getWire(args[1]));
    }
    
    
    
    
    private class Instruction{
        String inputA;
        
        public Instruction(String inputA){
            this.inputA = inputA;
        }
        
        public int run(){
            return 0;
        }
    }
                            
    private class Input extends Instruction{
        public Input(String input){
            super(input);
        }
        
        @Override
        public int run(){
            return Integer.parseInt(inputA);
        }
    }
    
    private class And extends Instruction {
        String inputB;
        
        public And(String a, String b){
            super(a);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            int a;
            int b;
            if(inputA.matches("\\d+")){
                a = Integer.parseInt(inputA);
            } else {
                a = instructions.get(inputA).run();
            }
            if(inputB.matches("\\d+")){
                b = Integer.parseInt(inputB);
            }else {
                b = instructions.get(inputB).run();
            }
            return (int)(a & b);
        }
    }

    private class LShift extends Instruction {
        String inputB;
        
        public LShift(String a, String b){
            super(a);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            int a;
            int b;
            if(inputA.matches("\\d+")){
                a = Integer.parseInt(inputA);
            } else {
                a = instructions.get(inputA).run();
            }
            if(inputB.matches("\\d+")){
                b = Integer.parseInt(inputB);
            }else {
                b = instructions.get(inputB).run();
            }
            return (int)(a << b);
        }
    }
    
    private class Not extends Instruction {
        
        public Not(String a){
            super(a);
        }
        
        @Override
        public int run(){
            int a;
            if(inputA.matches("\\d+")){
                a = Integer.parseInt(inputA);
            } else {
                a = instructions.get(inputA).run();
            }
            return (int)(~a);
        }
    }
    
    private class Or extends Instruction {
        String inputB;
        
        public Or(String a, String b){
            super(a);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            int a;
            int b;
            if(inputA.matches("\\d+")){
                a = Integer.parseInt(inputA);
            } else {
                a = instructions.get(inputA).run();
            }
            if(inputB.matches("\\d+")){
                b = Integer.parseInt(inputB);
            }else {
                b = instructions.get(inputB).run();
            }
            return (int)(a | b);
        }
    }

    private class RShift extends Instruction {
        String inputB;
        
        public RShift(String a, String b){
            super(a);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            int a;
            int b;
            if(inputA.matches("\\d+")){
                a = Integer.parseInt(inputA);
            } else {
                a = instructions.get(inputA).run();
            }
            if(inputB.matches("\\d+")){
                b = Integer.parseInt(inputB);
            }else {
                b = instructions.get(inputB).run();
            }
            return (int)(a >> b);
        }
    }

}



























