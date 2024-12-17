import java.util.*;

public class Deer {
    char[][] grid;
    Location start;

    private record Location(int x, int y, int d) {}
    private record State(int x, int y, int d, int v) {}

    public Deer(int x, int y, char[][] grid) {
        this.grid = grid;
        this.start = new Location(x, y, 1);
    }

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public int cost() {
        PriorityQueue<State> states = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.v, s2.v));
        states.add(new State(start.x, start.y, 2, 0));
        Set<Location> locs = new HashSet<>();
        Map<State, State> parent = new HashMap<>();
        return cost(states, locs, parent);
    }

    public int cost(PriorityQueue<State> queue, Set<Location> locs, Map<State, State> parent) {
        while (true) {
            State current = queue.poll();
            if (grid[current.y][current.x] == 'S') {
                printPath(current, parent);
                return current.v;
            }

            switch(current.d) {
                case 0:
                    addQueue(new State(current.x, current.y-1, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x+1, current.y, (current.d + 1) % 4, current.v + 1001), queue, locs, parent, current);
                    addQueue(new State(current.x-1, current.y, (current.d + 3) % 4, current.v + 1001), queue, locs, parent, current);
                    break;
                case 1:
                    addQueue(new State(current.x+1, current.y, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y+1, (current.d + 1) % 4, current.v + 1001), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y-1, (current.d + 3) % 4, current.v + 1001), queue, locs, parent, current);
                    break;
                case 2:
                    addQueue(new State(current.x, current.y+1, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x-1, current.y, (current.d + 1) % 4, current.v + 1001), queue, locs, parent, current);
                    addQueue(new State(current.x+1, current.y, (current.d + 3) % 4, current.v + 1001), queue, locs, parent, current);
                    break;
                case 3:
                    addQueue(new State(current.x-1, current.y, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y-1, (current.d + 1) % 4, current.v + 1001), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y+1, (current.d + 3) % 4, current.v + 1001), queue, locs, parent, current);
                    break;
            }
        }
    }

    public void addQueue(State state, PriorityQueue<State> queue, Set<Location> locs, Map<State, State> parent, State current) {
        Location loc = new Location(state.x, state.y, state.d);
        if (grid[loc.y][loc.x] == '#') return;
        if (locs.contains(loc)) return;
        else locs.add(loc);
        queue.add(state);
        parent.put(state, current);
    }

    private void printPath(State goal, Map<State, State> parentMap) {
        State current = goal;

        while (current != null) {
            grid[current.y][current.x] = 'O';
            current = parentMap.get(current);
        }

    }
}
