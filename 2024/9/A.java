import java.util.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.stream.*;

public class A {
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> disk = Files.lines(Paths.get("example.txt")).flatMap(line -> line.chars().mapToObj(c -> (char) c)).map(c -> Character.getNumericValue(c)).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Integer> compact = new ArrayList<>();

        int counter = 0;
        for (int i = 0; i < disk.size(); i++) {
            for (int j = 0; j < disk.get(i); j++) {
                compact.add(counter);
            }
            i++;
            if (i < disk.size()) {
                for (int j = 0; j < disk.get(i); j++) {
                    compact.add(-1);
                }
            }
            counter++;
        }

        for (int i = 0; i < compact.size(); i++) {
            if (!compact.get(i).equals(-1)) continue;

            for (int j = compact.size() - 1; j > i; j--) {
                if (compact.get(j).equals(-1)) continue;
                compact.set(i, compact.get(j));
                compact.set(j, -1);
                break;
            }
        }

        BigInteger total = BigInteger.valueOf(0);

        for (int i = 0; i < compact.size(); i++) {
            if (compact.get(i).equals(-1)) break;
            total = total.add(BigInteger.valueOf(compact.get(i) * i));
        }

        System.out.println(total);
    }
}
