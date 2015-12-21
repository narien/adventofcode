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
            instructions.put(s[4], new And(s[0], s[2], s[4]));
            
        } else if (line.contains("OR")) {
            instructions.put(s[4], new Or(s[0], s[2], s[4]));
            
        } else if (line.contains("NOT")){
            instructions.put(s[3], new Not(s[1], s[3]));

            
        } else if (line.contains("LSHIFT")){
            instructions.put(s[4], new LShift(s[0], s[2], s[4]));

            
        } else if (line.contains("RSHIFT")){
            instructions.put(s[4], new RShift(s[0], s[2], s[4]));

            
        } else { //normal input
            instructions.put(s[2], new Input(s[0], s[2]));
        }
    }

    public int getWire(String s){
        instructions.put("b", new Input("956", "b")); //To solve part two
        return instructions.get(s).run();
    }
    
	public static void main(String args[]) throws Exception{ // java LogicalGates INPUTFILE TARGET
        
        LogicalGates lg = new LogicalGates();
        BufferedReader br = new BufferedReader(new FileReader(args[0]));
        String line = br.readLine();
        
        while (line != null){
            lg.add(line);
            line = br.readLine();
        }
        System.out.println(lg.getWire(args[1]));
    }
    
    
    
    
    private class Instruction{
        protected String iAm;
        protected String inputA;
        
        public Instruction(String inputA, String iAm){
            this.inputA = inputA;
            this.iAm = iAm;
        }
        
        public int run(){
            return 0;
        }
        
        protected void insert(int i){
            instructions.put(iAm, new Input(Integer.toString(i), iAm));
        }
        
        public int calculate(String s){
            if(s.matches("\\d+")){
                return Integer.parseInt(s);
            } else {
                int i = instructions.get(s).run();
                return i;
            }
        }
    }
                            
    private class Input extends Instruction{
        public Input(String input, String iAm){
            super(input, iAm);
        }
        
        @Override
        public int run(){
            System.out.println("INPUT " + inputA + " to " + iAm);
            int i = calculate(inputA);
            insert(i);
            return i;
        }
    }
    
    private class And extends Instruction {
        private String inputB;
        
        public And(String a, String b, String iAm){
            super(a, iAm);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            System.out.println(inputA + " AND " + inputB + " to " + iAm);
            int i = calculate(inputA) & calculate(inputB);
            insert(i);
            return i;
        }
    }

    private class LShift extends Instruction {
        private String inputB;
        
        public LShift(String a, String b, String iAm){
            super(a, iAm);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            System.out.println(inputA + " LSHIFT " + inputB + " to " + iAm);
            int i = (calculate(inputA) << calculate(inputB)) & 0xFFFF;
            insert(i);
            return i;
        }
    }
    
    private class Not extends Instruction {
        
        public Not(String a, String iAm){
            super(a, iAm);
        }
        
        @Override
        public int run(){
            System.out.println("NOT " + inputA + " to " + iAm);
            int i = (~calculate(inputA)) & 0xFFFF;
            insert(i);
            return i;
        }
    }
    
    private class Or extends Instruction {
        private String inputB;
        
        public Or(String a, String b, String iAm){
            super(a, iAm);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            System.out.println(inputA + " OR " + inputB + " to " + iAm);
            int i = calculate(inputA) | calculate(inputB);
            insert(i);
            return i;
        }
    }

    private class RShift extends Instruction {
        private String inputB;
        
        public RShift(String a, String b, String iAm){
            super(a, iAm);
            this.inputB = b;
        }
        
        @Override
        public int run(){
            System.out.println(inputA + " RSHIFT " + inputB + " to " + iAm);
            int i = calculate(inputA) >> calculate(inputB);
            insert(i);
            return i;
        }
    }

}



























