import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.*;

public class B {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();

        ArrayList<String> lines = new ArrayList<>();
        while (line != null) {
            lines.add(line);
            line = br.readLine();
        }
        
        boolean exce = true;
        Pattern pattern = Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))|do\\(\\)|don't\\(\\)");

        int total = 0;
        for (String currentLine : lines) {
            Matcher matcher = pattern.matcher(currentLine);
            while (matcher.find()) {
                if (matcher.group().equals("don't()")) {
                    exce = false;
                    continue;
                }
                if (matcher.group().equals("do()")) {
                    exce = true;
                    continue;
                }

                Pattern numbers = Pattern.compile("\\d{1,3}");
                Matcher newMatcher = numbers.matcher(matcher.group());
                newMatcher.find();
                int first = Integer.parseInt(newMatcher.group(0)); 
                newMatcher.find();
                int second = Integer.parseInt(newMatcher.group(0));
                if (exce) total += first * second;
            }
        }

        

        System.out.println(total);
    }
}
