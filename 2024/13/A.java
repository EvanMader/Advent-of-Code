import java.util.*;
import java.io.*;
import java.math.BigInteger;
import java.util.regex.*;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        ArrayList<Game> games = new ArrayList<>();

        Pattern pattern = Pattern.compile("\\d+");
        String line = br.readLine();
        while (line != null) {
            if (line.equals("")) {
                line = br.readLine();
                continue;
            }
            Matcher matcher = null;

            matcher = pattern.matcher(line);
            matcher.find();
            int ax = Integer.parseInt(matcher.group());
            matcher.find();
            int ay = Integer.parseInt(matcher.group());

            line = br.readLine();

            matcher = pattern.matcher(line);
            matcher.find();
            int bx = Integer.parseInt(matcher.group());
            matcher.find();
            int by = Integer.parseInt(matcher.group());

            line = br.readLine();

            matcher = pattern.matcher(line);
            matcher.find();
            Long gx = Long.parseLong(matcher.group());
            matcher.find();
            Long gy = Long.parseLong(matcher.group());

            games.add(new Game(gx, gy, ax, ay, bx, by));
            line = br.readLine();
        }

        BigInteger total = new BigInteger(String.valueOf("0"));
        for (Game game : games) {
            total = total.add(game.tokens());
        }

        System.out.println(total);

    }
}