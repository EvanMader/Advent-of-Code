import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.io.File;

public class A {
    public static void main(String args[]) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
            String line = br.readLine();

            ArrayList<Integer> first = new ArrayList<>();
            ArrayList<Integer> second = new ArrayList<>();

            int total = 0;
            while (line != null) {
                String[] newLine = line.split("\\s+");

                first.add(Integer.parseInt(newLine[0]));
                second.add(Integer.parseInt(newLine[1]));

                line = br.readLine();
            }
            first.sort((a, b) -> a - b);
            second.sort((a, b) -> a - b);
            for (int i = 0; i < first.size(); i++) {
                total += Math.abs(first.get(i) - second.get(i));
            }
            System.out.println(total);
            br.close();
    }
}