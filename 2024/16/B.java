import java.util.ArrayList;
import java.io.*;
import java.io.File;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        ArrayList<char[]> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) break;
            lines.add(line.toCharArray());
        }
        char[][] grid = lines.toArray(new char[lines.size()][]);

        BDeer deer = null;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == 'S') deer = new BDeer(i, j, grid);
                if (deer != null) break;
            }
            if (deer != null) break;
        }

        deer.cost();
        System.out.println(deer.printGrid() + 1);
    }
}