import java.util.*;
import java.util.regex.*;
import java.io.*;

public class B {
    public static record Location(int x, int y) {}
    public static record State(Location loc, int cost) {}
    public static PriorityQueue<State> states = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.cost, s2.cost));
    public static Map<Location, Location> parent = new HashMap<>();
    public static char[][] grid = new char[71][71];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        ArrayList<Location> locs = new ArrayList<>();
        Pattern pattern = Pattern.compile("\\d+");
        String line;
        while ((line = br.readLine()) != null) {
            Matcher matcher = pattern.matcher(line);
            matcher.find(); 
            int x = Integer.parseInt(matcher.group());
            matcher.find(); 
            int y = Integer.parseInt(matcher.group());
            locs.add(new Location(x, y));
        }
        br.close();

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = '.';
            }
        }

        int index = dijkstra(locs);
        System.out.println(index);
        System.out.println(locs.get(index));
    }

    public static int dijkstra(ArrayList<Location> locs) {

        State current = null;
        int bytes = 0;
        boolean running = true;
        while (running) {
            states.add(new State(new Location(0, 0), 0));
            while (true) {
                current = states.poll();
                if (current == null) {
                    running = false;
                    break; 
                }
                int x = current.loc.x;
                int y = current.loc.y;
                if (x == 70 && y == 70) break;
                checkAdd(new State(new Location(x-1, y), current.cost + 1), current);
                checkAdd(new State(new Location(x+1, y), current.cost + 1), current);
                checkAdd(new State(new Location(x, y+1), current.cost + 1), current);
                checkAdd(new State(new Location(x, y-1), current.cost + 1), current);
            }

            grid[locs.get(bytes).y][locs.get(bytes).x] = '#';
            bytes++;
            states.clear();
            parent.clear();
        }

        return bytes - 2;
    }

    public static void checkAdd(State state, State current) {
        int x = state.loc.x;
        int y = state.loc.y;

        if (!(x >= 0 && x < 71 && y >= 0 && y < 71)) return;
        if (grid[y][x] == '#') return;
        if (parent.containsKey(state.loc)) {
            return;
        }

        states.add(state);
        parent.put(state.loc, current.loc);
    }

    public static void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }
}