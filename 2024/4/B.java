import java.util.ArrayList;
import java.nio.file.*;

public class B {
    public static void main(String[] args) throws Exception {
        ArrayList<String> horrizontalList = new ArrayList<>(Files.readAllLines(Paths.get("input.txt")));
        int matches = 0;

        for (int i = 0; i < horrizontalList.size() - 2; i++) {
            for (int j = 0; j < horrizontalList.get(i).length() - 2; j++) {
                String check = "";
                check += String.valueOf(horrizontalList.get(j).charAt(i));
                check += String.valueOf(horrizontalList.get(j+2).charAt(i));
                check += String.valueOf(horrizontalList.get(j).charAt(i+2));
                check += String.valueOf(horrizontalList.get(j+2).charAt(i+2));
                check += String.valueOf(horrizontalList.get(j+1).charAt(i+1));  
                switch (check) {
                    case "MMSSA":
                    case "SSMMA":
                    case "MSMSA":
                    case "SMSMA":
                        matches++;
                    default:
                }
            }
        }

        System.out.println(matches);
    }
}
