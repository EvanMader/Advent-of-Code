import java.util.*;
import java.math.BigInteger;
import java.nio.file.*;
import java.util.stream.*;

public class B {
    public static record indexSizeNum(int i, int s, int n) {}
    public static void main(String[] args) throws Exception {
        ArrayList<Integer> disk = Files.lines(Paths.get("input.txt")).flatMap(line -> line.chars().mapToObj(c -> (char) c)).map(c -> Character.getNumericValue(c)).collect(Collectors.toCollection(ArrayList::new));
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

        int start = compact.size() - 1;
        while (true) {
            start = insert(compact, start);
            if (start == -1) break;
        }

        BigInteger total = BigInteger.valueOf(0);

        for (int i = 0; i < compact.size(); i++) {
            if (compact.get(i).equals(-1)) continue;
            total = total.add(BigInteger.valueOf(compact.get(i) * i));
        }

        System.out.println(total);
    }

    public static int insert(ArrayList<Integer> disk, int start) {
        indexSizeNum values = findSet(disk, start);
        if (values == null) return -1;

        int spaceIndex = findSpace(disk, values.s);
        if (spaceIndex == -1) return values.i - 1;
        if (spaceIndex > values.i) return values.i - 1;

        setSpace(disk, spaceIndex, values.s, values.n);
        setSpace(disk, values.i, values.s, -1);

        return values.i - 1;
    }

    public static int findSpace(ArrayList<Integer> disk, int space) {
        int size = 0;
        for (int i = 0; i < disk.size(); i++) {
            if (disk.get(i).equals(-1)) {
                size++;
            }
            if (size == space) return i - space + 1;
            if (!disk.get(i).equals(-1)) size = 0;
        }
        return -1;
    }

    public static indexSizeNum findSet(ArrayList<Integer> disk, int index) {
        int i;
        for (i = index; i >= 0; i--) {
            if (disk.get(i).equals(-1)) continue;
            else break;
        }

        if (i == -1) return null;

        int num = disk.get(i);
        int size = 0;
        for (; i >= 0; i--) {
            if (!disk.get(i).equals(num)) break;
            size++; 
        }

        return new indexSizeNum(i + 1, size, num);
    }

    public static void setSpace(ArrayList<Integer> disk, int index, int size, int num) {
        for (int i = index; i < index+size; i++) {
            disk.set(i, num);
        }
    }
}