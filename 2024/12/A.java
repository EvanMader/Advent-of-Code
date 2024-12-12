import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashMap;

public class A {
    public static record Values(int p, int a) {}
    public static record Loc(int x, int y) {}
    public static HashMap<Character, ArrayList<Loc>> locs = new HashMap<>();
    public static HashMap<Character, ArrayList<Values>> vals = new HashMap<>();
    public static ArrayList<ArrayList<Character>> garden = new ArrayList<>();
    public static int width;
    public static int height;

    public static void main(String[] args) throws Exception {
        garden = Files.lines(Path.of("input.txt")).map(line -> line.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        width = garden.get(0).size();
        height = garden.size();

        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                search(garden.get(j).get(i), i, j);
            }
        }

        int total = 0;
        for (ArrayList<Values> val : vals.values()) {
            for (Values nums : val) {
                total += nums.p * nums.a;
            }
        }
        System.out.println(total);
    }

    public static void search(Character c, int x, int y) {
        if (locs.containsKey(c)) {
            if (locs.get(c).contains(new Loc(x, y))) return;
        } else {
            vals.put(c, new ArrayList<>());
            locs.put(c, new ArrayList<>());
        }
        Values val = searchR(c, new Values(0, 0), x, y);

        vals.get(c).add(val);
    }

    public static Values searchR(Character c, Values values, int x, int y) {
        if (x < 0 || x >= width || y < 0 || y >= height) return new Values(values.p + 1, values.a);
        if (garden.get(y).get(x) != c) return new Values(values.p + 1, values.a);
        if (locs.get(c).contains(new Loc(x, y))) return values;
        locs.get(c).add(new Loc(x, y));
        Values newValues = new Values(values.p, values.a + 1);

        newValues = searchR(garden.get(y).get(x), newValues, x+1, y);
        newValues = searchR(garden.get(y).get(x), newValues, x-1, y);
        newValues = searchR(garden.get(y).get(x), newValues, x, y+1);
        newValues = searchR(garden.get(y).get(x), newValues, x, y-1);

        return newValues;
    }
}