import java.util.ArrayList;
import java.util.regex.*;
import java.io.*;
import java.io.File;

public class A {
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

        int q1 = 0;
        int q2 = 0;
        int q3 = 0;
        int q4 = 0;
        for (Robot robot : robots) {
            robot.move(100);
            switch (robot.quad()) {
                case 1:
                    q1++;
                    break;
                case 2:
                    q2++;
                    break;
                case 3:
                    q3++;
                    break;
                case 4:
                    q4++;
                    break;
                default:
                    break;
            }
        }

        System.out.println(q1 + " " + q2 + " " + q3 + " " + q4);
        System.out.println(q1*q2*q3*q4);


    }
}