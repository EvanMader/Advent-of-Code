import java.util.ArrayList;
import java.nio.file.*;
import java.util.stream.Collectors;

public class A {
    public static record Location(int x, int y) {}

    public static enum Direction {
        UP,
        RIGHT,
        DOWN,
        LEFT 
    }
    public static Direction direction = Direction.UP;
    public static int distinct = 1;
    public static void main(String args[]) throws Exception {
        ArrayList<ArrayList<Character>> board = Files.lines(Paths.get("input.txt")).map(line -> line.chars().mapToObj(c -> (char) c).collect(Collectors.toCollection(ArrayList::new))).collect(Collectors.toCollection(ArrayList::new));
        ArrayList<Location> locations = new ArrayList<>();

        found:
        for (int i = 0; i < board.size(); i++) {
            for (int j = 0; j < board.get(i).size(); j++) {
                if (board.get(i).get(j) == '^') {
                    locations.add(new Location(j, i));
                    break found;
                }
            }
        }

        // Yes I know that everything I wrote before this is awful but if I do it the lame and boreing way it won't contribute to my learning
        // Like you see that 1 liner then a record then a break => label??? I know you don't because you can't read it... no one can.

        while (true) {
            Location newLocation = move(locations.getLast(), board);
            if (!locations.contains(newLocation)) distinct ++;
            locations.add(newLocation);
        }
    }

    public static Location move(Location location, ArrayList<ArrayList<Character>> board) {
        Location nextLocation = next(location);
        int x = nextLocation.x;
        int y = nextLocation.y;

        try{
            if (board.get(y).get(x) == '#') {
                direction = A.Direction.values()[(direction.ordinal() + 1) % A.Direction.values().length];
                return move(location, board);
            }
            return nextLocation;
        } catch (Exception e) {
            System.out.println(distinct);
            System.exit(0);
        }
        return null;
    }

    public static Location next(Location location) {
        int x = location.x;
        int y = location.y;
        switch (direction) {
            case UP: return new Location(x, -- y);
            case RIGHT: return new Location(++ x, y);
            case DOWN: return new Location(x, ++ y );
            case LEFT: return new Location(-- x, y);
            default: 
                System.out.println("Chalked");
                return null;
        }
    }

}