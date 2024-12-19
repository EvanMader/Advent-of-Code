import java.util.*;
import java.util.regex.*;
import java.nio.Buffer;
import java.io.*;

public class A {
    public static String[] towels = null;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();
        towels = line.replaceAll(" ", "").split(",");
        br.readLine();
        Arrays.sort(towels);

        int total = 0;
        while ((line = br.readLine()) != null) {
            if (findPattern(line)) {
                total++;
            }
        }

        System.out.println(total);
    }

    public static boolean findPattern(String line) {
        if (line.length() == 0) return true;

        for (String towel : towels) {
            if (towel.length() > line.length()) continue;
            if (!(towel.equals(line.substring(0, towel.length())))) continue;

            if(findPattern(line.substring(towel.length()))) return true;
        }

        return false;
    }
}