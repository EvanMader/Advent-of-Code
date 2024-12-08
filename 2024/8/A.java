import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class A {

    public static record Location(int x, int y, char c) {}
    public static record AntiNodes(Location one, Location two) {}
    public static int width = 0;
    public static int height = 0;
    public static void main(String[] args) throws Exception {
        ArrayList<ArrayList<Character>> grid = Files.lines(Path.of("input.txt")).map(line -> new ArrayList<>(line.chars().mapToObj(c -> (char) c).toList())).collect(Collectors.toCollection(ArrayList::new));

        width = grid.get(0).size();
        height = grid.size();

        ArrayList<Location> antennae = new ArrayList<>();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                if (grid.get(i).get(j) != '.') {
                    antennae.add(new Location(i, j, grid.get(i).get(j).charValue()));
                }
            }
        }

        Set<Location> antiNodeList = new HashSet<>();
        for (int i = 0; i < antennae.size(); i++) {
            for (int j = 0; j < antennae.size(); j++) {
                if (i == j) continue;
                if (antennae.get(i).c == antennae.get(j).c) {
                    AntiNodes antiNodes = createAntiNodes(antennae.get(i), antennae.get(j));
                    if (inBounds(antiNodes.one)) antiNodeList.add(antiNodes.one);
                    if (inBounds(antiNodes.two)) antiNodeList.add(antiNodes.two);
                }
            }
        }

        System.out.println(antiNodeList.size());
    }

    public static AntiNodes createAntiNodes(Location a, Location b) {
        Location one = new Location(2*a.x - b.x, 2*a.y - b.y, '#');
        Location two = new Location(2*b.x - a.x, 2*b.y - a.y,'#');

        return new AntiNodes(one, two);
    }

    public static boolean inBounds(Location location) {
        if (location.x >= width || location.x < 0 || location.y >= height || location.y < 0) return false;
        return true;
    }
    
}