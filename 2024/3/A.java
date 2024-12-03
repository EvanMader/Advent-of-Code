import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();

        ArrayList<String> lines = new ArrayList<>();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        
        Pattern pattern = Pattern.compile("mul\\(\\d{1,3},\\d{1,3}\\)");
        ArrayList<String> matches = new ArrayList<>();
        for (String currentLine : lines) {
            Matcher matcher = pattern.matcher(currentLine);
            while (matcher.find()) {
                matches.add(matcher.group());
            }
        }

        int total = 0;
        
        Pattern numbers = Pattern.compile("\\d{1,3}");
        for (String match : matches) {
            System.out.println(match);
            Matcher matcher = numbers.matcher(match);
            matcher.find();
            int first = Integer.parseInt(matcher.group(0)); 
            matcher.find();
            int second = Integer.parseInt(matcher.group(0));
            total += first * second;
        }

        System.out.println(total);
    }
}