import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();

        ArrayList<ArrayList<Integer>> levels = new ArrayList<>();
        while (line != null) {
            levels.add(new ArrayList<>());
            String[] parsedLine = line.split("\\s+");
            for (int i = 0; i < parsedLine.length; i++) {
                levels.getLast().add(Integer.parseInt(parsedLine[i]));
            }
            line = br.readLine();
        }

        int safeLevels = 0;
        
        for (ArrayList<Integer> level : levels) {
            ArrayList<Integer> sortedLevel = new ArrayList<>();
            for (Integer num : level) {
                sortedLevel.add(num);
            }

            boolean ascending = false; 
            boolean sorted = false;

            sortedLevel.sort((a, b) -> b - a);
            if (level.equals(sortedLevel))  {
                sorted = true;
            }

            sortedLevel.sort((a, b) -> a - b);
            if (level.equals(sortedLevel)) {
                ascending = true;
                sorted = true;
            }
            
            if (!sorted) continue;

            if (ascending) {
                for (int i = 0; i < level.size() - 1; i++) {
                    int difference = level.get(i + 1) - level.get(i);
                    if (difference >= 4 || difference <= 0) {
                        safeLevels--;
                        break;
                    }
                }
                safeLevels++;
                continue;
            }

            if (sorted) {
                for (int i = 0; i < level.size() - 1; i++) {
                    int difference = level.get(i) - level.get(i + 1);
                    if (difference >= 4 || difference <= 0) {
                        safeLevels--;
                        break;
                    }
                }
                safeLevels++;
                continue;
            }
        }

        System.out.println(safeLevels);
    }
}