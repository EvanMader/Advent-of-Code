import java.util.ArrayList;
import java.io.*;
import java.io.File;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        ArrayList<char[]> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.isEmpty()) break;
            lines.add(line.toCharArray());
        }
        char[][] grid = lines.toArray(new char[lines.size()][]);

        ARobot robot = null;
        for (int i = 0; i < grid[0].length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[j][i] == '@') robot = new ARobot(i, j, grid);
                if (robot != null) break;
            }
            if (robot != null) break;
        }

        ArrayList<Character> movements = new ArrayList<>();
        int charValue;
        while ((charValue = br.read()) != -1) {
            char c = (char) charValue;
            movements.add(c);
        }

        for (int i = 0; i < movements.size(); i++) {
            robot.move(movements.get(i));
        }

        robot.printGrid();
        System.out.println(robot.GPS());
    }
}