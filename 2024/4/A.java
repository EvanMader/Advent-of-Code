import java.nio.file.*;
import java.util.*;
import java.util.regex.*;

public class A {
    public static void main(String[] args) throws Exception {
        ArrayList<String> horrizontalList = new ArrayList<>(Files.readAllLines(Paths.get("input.txt")));

        ArrayList<String> verticalList = new ArrayList<>(Collections.nCopies(horrizontalList.get(0).length(), ""));
        for (String line : horrizontalList) {
            for (int i = 0; i < line.length(); i++) {
                verticalList.set(i, verticalList.get(i) + line.charAt(i));
            }
        }

        ArrayList<String> supplyList = new ArrayList<>(Collections.nCopies(verticalList.size() + horrizontalList.size() - 1, ""));
        int counter = 0;
        for (String line : verticalList) {
            for (int i = 0; i < line.length(); i++) {
                supplyList.set(i+counter, supplyList.get(i+counter) + line.charAt((i)));
            }
            counter++;
        }

        ArrayList<String> demandList = new ArrayList<>(Collections.nCopies(verticalList.size() + horrizontalList.size() - 1, ""));
        counter = verticalList.size() - 1;
        for (String line : verticalList) {
            for (int i = 0; i < line.length(); i++) {
                demandList.set(i+counter, demandList.get(i+counter) + line.charAt(i));
            }
            counter--;
        }

        Pattern pattern = Pattern.compile("(?=(XMAS|SAMX))");
        int matches = 0;

        for (String line : horrizontalList) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                matches++;
            }
        }
        for (String line : verticalList) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                matches++;
            }
        }
        for (String line : supplyList) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                matches++;
            }
        }
        for (String line : demandList) {
            Matcher matcher = pattern.matcher(line);
            while(matcher.find()) {
                matches++;
            }
        }
        System.out.println(matches);
    }
}