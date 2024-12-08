import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

public class B {
    public static record Location(int x, int y, char c) {}
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
                    ArrayList<Location> antiNodes = createAntiNodes(antennae.get(i), antennae.get(j));
                    for (Location loc : antiNodes) {
                        antiNodeList.add(loc);
                    }
                }
            }
        }

        System.out.println(antiNodeList.size());
    }

    public static ArrayList<Location> createAntiNodes(Location a, Location b) {
        ArrayList<Location> antiNodeList = new ArrayList<>();
        int i = 0;
        while (inBounds(new Location(a.x - i * (b.x - a.x), a.y - i * (b.y - a.y), '#'))) {
            antiNodeList.add(new Location(a.x - i * (b.x - a.x), a.y - i * (b.y - a.y), '#'));
            i++;
        }

        return antiNodeList;
    }

    public static boolean inBounds(Location location) {
        if (location.x >= width || location.x < 0 || location.y >= height || location.y < 0) return false;
        return true;
    }
    
}