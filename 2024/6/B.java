import java.util.ArrayList;
import java.nio.file.*;
import java.security.cert.CollectionCertStoreParameters;
import java.util.stream.Collectors;

public class B {
    public static record Spot(int x, int y) {}
    public static record Location(int x, int y, Direction d) {}

    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT 
    }
    public static Direction direction = Direction.UP;
    public static void main(String args[]) throws Exception {
        ArrayList<ArrayList<Character>> board = Files.lines(Paths.get("input.txt")).map(line -> line.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Location> locations = new ArrayList<>();

        found:
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == '^') {
                    locations.add(new Location(j, i, direction));
                    break found;
                }
            }
        }

        // It's all awful don't @ me

        while (true) {
            Location newLocation = move(locations.getLast(), board);
            if (newLocation == null) break;
            locations.add(newLocation);
        }

        ArrayList<Spot> spots = new ArrayList<>();
        for (int i = 1; i < locations.size(); i++) {
            ArrayList<ArrayList<Character>> newBoard = board.stream().map(innerList -> new ArrayList<>(innerList)).collect(Collectors.toCollection(ArrayList::new));
            newBoard.get(locations.get(i).y).set(locations.get(i).x, '#');

            ArrayList<Location> newPath = new ArrayList<>();
            newPath.add(locations.getFirst());
            while (true) {
                direction = newPath.getLast().d;
                Location newLocation = move(newPath.getLast(), newBoard);
                if (newLocation == null) break;
                if (newPath.contains(newLocation)) {
                    if (!spots.contains(new Spot(locations.get(i).x, locations.get(i).y))) spots.add(new Spot(locations.get(i).x, locations.get(i).y));
                    break;
                }
                newPath.add(newLocation);
            }
        }

        System.out.println(spots.size());

    }

    public static Location move(Location location, ArrayList<ArrayList<Character>> board) {
        Location nextLocation = next(location);
        int x = nextLocation.x;
        int y = nextLocation.y;

        try{
            if (board.get(y).get(x) == '#') {
                direction = nextDirection();
                return move(location, board);
            }
            return nextLocation;
        } catch (Exception e) {
            return null;
        }
    }

    public static Location next(Location location) {
        int x = location.x;
        int y = location.y;
        switch (direction) {
            case UP: return new Location(x, -- y, direction);
            case RIGHT: return new Location(++ x, y, direction);
            case DOWN: return new Location(x, ++ y, direction);
            case LEFT: return new Location(-- x, y, direction);
            default: 
                return null;
        }
    }

    public static Direction nextDirection () {
        return B.Direction.values()[(direction.ordinal() + 1) % B.Direction.values().length];
    }
}