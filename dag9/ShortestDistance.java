import java.io.*;
import java.util.*;
import java.util.regex.*;
import java.lang.Math;

public class ShortestDistance {
    
    private static HashMap<String, HashMap<String, Integer>> relations;
    private static Set<String> cities;
    
    public static void initialize(String fileName) {
        relations = new HashMap<String, HashMap <String, Integer>>();
        cities = new HashSet<String>();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File(fileName)));
            Pattern p = Pattern.compile("([A-Za-z]*) to ([A-Za-z]*) = ([0-9]*)");
            String line = reader.readLine();
            while(line != null) {
                Matcher m = p.matcher(line);
                if(m.find()){
                    if(relations.containsKey(m.group(1))) {
                        relations.get(m.group(1)).put(m.group(2), Integer.parseInt(m.group(3)));
                    } else {
                        HashMap<String, Integer> nestedMap = new HashMap<String, Integer>();
                        nestedMap.put(m.group(2), Integer.parseInt(m.group(3)));
                        relations.put(m.group(1), nestedMap);
                    }
                    
                    if(relations.containsKey(m.group(2))) {
                        relations.get(m.group(2)).put(m.group(1), Integer.parseInt(m.group(3)));
                    } else {
                        HashMap<String, Integer> nestedMap = new HashMap<String, Integer>();
                        nestedMap.put(m.group(1), Integer.parseInt(m.group(3)));
                        relations.put(m.group(2), nestedMap);
                    }

                    cities.add(m.group(1));
                    cities.add(m.group(2));
                }
                line = reader.readLine();
            }
        } catch(FileNotFoundException ex) {
            System.err.println(fileName + " couldn't be found.");
            System.exit(1);
        } catch(IOException ex) {
            System.err.println("Error while reading from " + fileName);
            System.exit(1);
        }
    }
    
    public static int findShortestDistance(){
        int shortestDistance = Integer.MAX_VALUE;
        for (String firstTown: cities){
            HashSet <String> visited = new HashSet <String>();
            visited.add(firstTown);
            shortestDistance = Math.min(shortestDistance, nextTown(firstTown, visited));
        }
        
        return shortestDistance;
    }
    
    
    public static int nextTown(String curTown, HashSet<String> prevVisited){
        if(cities.size() == prevVisited.size()) return 0;
        int shortestDistance = Integer.MAX_VALUE;
        for(String nextTown : cities){
            HashSet<String> visited = new HashSet<String>();
            visited.addAll(prevVisited);
            int distance = Integer.MAX_VALUE;
            if (!visited.contains(nextTown)){
                visited.add(nextTown);
                distance = relations.get(curTown).get(nextTown) + nextTown(nextTown, visited);
            }
            shortestDistance = Math.min(shortestDistance, distance);
        }
        return shortestDistance;
    }
    
    public static void main(String[] args) {
        if(args.length != 1) {
            System.err.println("Must specify (only) one input file.");
            System.exit(1);
        }
        initialize(args[0]);
        System.out.println("Result: " + findShortestDistance());
    }
}