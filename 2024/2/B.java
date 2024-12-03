import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;

public class B {
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
            boolean good = false;
            boolean ascending = false; 

            ArrayList<Integer> removedLevel = new ArrayList<>();
            for (int j = -1; j < level.size(); j++) {
                for (int i = 0; i < level.size(); i++) {
                    if (i == j) continue;
                    removedLevel.add(level.get(i));
                }

                if (good(removedLevel)) {
                    safeLevels ++;
                    break;
                }
                removedLevel.clear();
            }
        }

        System.out.println(safeLevels);
    }

    public static boolean good(ArrayList<Integer> arr) {
        ArrayList<Integer> sortedLevel = (ArrayList) arr.clone();
        boolean sorted = false;

        sortedLevel.sort((a, b) -> b - a);
        if (arr.equals(sortedLevel))  {
            sorted = true;
        }

        sortedLevel.sort((a, b) -> a - b);
        if (arr.equals(sortedLevel)) {
            sorted = true;
        }
        
        for (int i = 0; i < sortedLevel.size() - 1; i++) {
            if (sortedLevel.get(i).equals(sortedLevel.get(i + 1))) {
                sorted = false;
            }
        }

        if (sorted == false) return false;

        for (int i = 0; i < arr.size() - 1; i++) {
            if (Math.abs(arr.get(i) - arr.get(i + 1)) > 3) return false;
        }

        return true;
    }
}