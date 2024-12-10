import java.util.*;
import java.io.*;
import java.nio.file.*;
import java.util.stream.*;

public class B {
    public static record Location(int x, int y, int n) {}
    public static int width = 0;
    public static int height = 0;
    public static ArrayList<ArrayList<Integer>> list = new ArrayList<>();
    public static void main (String[] args) throws Exception {
        list = Files.lines(Path.of("input.txt")).map(line -> line.chars().mapToObj(c -> c - '0').collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        width = list.get(0).size();
        height = list.size();

        int total = 0;
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (list.get(i).get(j).equals(0))  total += move(new Location(j, i, list.get(i).get(j)));
            }
        }

        System.out.println(total);
    }

    public static int move(Location loc) {
        if (loc.x >= width || loc.x < 0 || loc.y < 0 || loc.y >= height) return 0;
        int total = 0;
        if (loc.n == 9) return 1;
        if (loc.y + 1 < height && list.get(loc.y + 1).get(loc.x) == loc.n + 1) total += move(new Location(loc.x, loc.y + 1, list.get(loc.y+1).get(loc.x)));
        if (loc.y - 1 >= 0 && list.get(loc.y - 1).get(loc.x) == loc.n + 1) total += move(new Location(loc.x, loc.y - 1, list.get(loc.y-1).get(loc.x)));
        if (loc.x + 1 < width && list.get(loc.y).get(loc.x + 1) == loc.n + 1) total += move(new Location(loc.x + 1, loc.y, list.get(loc.y).get(loc.x + 1)));
        if (loc.x - 1 >= 0 && list.get(loc.y).get(loc.x - 1) == loc.n + 1) total += move(new Location(loc.x - 1, loc.y, list.get(loc.y).get(loc.x - 1)));
        return total;
    }
}