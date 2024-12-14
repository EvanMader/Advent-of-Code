import java.util.ArrayList;
import java.util.regex.*;
import java.io.*;
import java.io.File;

public class B {
    public static int[][] locations;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        ArrayList<Robot> robots = new ArrayList<>();
        Pattern pattern = Pattern.compile("-?\\d+");
        String line = br.readLine();
        while (line != null) {
            Matcher matcher = pattern.matcher(line);
            matcher.find();
            int x = Integer.parseInt(matcher.group());
            matcher.find();
            int y = Integer.parseInt(matcher.group());
            matcher.find();
            int vx = Integer.parseInt(matcher.group());
            matcher.find();
            int vy = Integer.parseInt(matcher.group());

            robots.add(new Robot(x, y, vx, vy));
            line = br.readLine();
        }

        int counter = 0;
        while (true) {
            locations = new int[103][101];
            for (Robot robot : robots) {
                robot.move();
                locations[robot.y][robot.x] ++;
            }
            counter ++;

            for (int i = 0; i < 103; i++) {
                for (int j = 0; j < 101; j++) {
                    if (check(i, j) && check(i, j+1) && check(i, j+2) && check(i, j+3) && check(i, j+4) && check(i-1, j+1) && check(i-1, j+2) && check(i-1, j+3) && check(i-2, j+2) && check(i+1, j+2)) {
                        System.out.println(counter);
                        System.exit(1);
                    }
                }
            }
            
            if (counter % 100 == 0) System.out.println(counter);
        }

    }

    public static boolean check(int x, int y) {
        if (x < 0 || x >= 101 || y < 0 || y >= 103) return false;
        return locations[y][x] > 0;
    }
}