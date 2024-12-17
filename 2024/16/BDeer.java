import java.util.*;

public class BDeer {
    char[][] grid;
    Location start;

    private record Location(int x, int y, int d) {}
    private record State(int x, int y, int d, int v) {}

    public BDeer(int x, int y, char[][] grid) {
        this.grid = grid;
        this.start = new Location(x, y, 1);
    }

    public int printGrid() {
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
                if (grid[i][j] == 'O') total++;
            }
            System.out.println();
        }

        return total;
    }

    public int cost() {
        PriorityQueue<State> states = new PriorityQueue<>((s1, s2) -> Integer.compare(s1.v, s2.v));
        states.add(new State(start.x, start.y, 1, 0));
        Map<Location, Integer> locs = new HashMap<>();
        locs.put(start, 0);
        Map<State, ArrayList<State>> parent = new HashMap<>();
        return cost(states, locs, parent);
    }

    public int cost(PriorityQueue<State> queue, Map<Location, Integer> locs, Map<State, ArrayList<State>> parent) {
        while (true) {
            State current = queue.poll();
            if (grid[current.y][current.x] == 'E') {
                printPath(parent.get(current), parent);
                return current.v;
            }

            switch(current.d) {
                case 0:
                    addQueue(new State(current.x, current.y-1, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 1) % 4, current.v + 1000), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 3) % 4, current.v + 1000), queue, locs, parent, current);
                    break;
                case 1:
                    addQueue(new State(current.x+1, current.y, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 1) % 4, current.v + 1000), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 3) % 4, current.v + 1000), queue, locs, parent, current);
                    break;
                case 2:
                    addQueue(new State(current.x, current.y+1, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 1) % 4, current.v + 1000), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 3) % 4, current.v + 1000), queue, locs, parent, current);
                    break;
                case 3:
                    addQueue(new State(current.x-1, current.y, current.d, current.v + 1), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 1) % 4, current.v + 1000), queue, locs, parent, current);
                    addQueue(new State(current.x, current.y, (current.d + 3) % 4, current.v + 1000), queue, locs, parent, current);
                    break;
            }
        }
    }

    public void addQueue(State state, PriorityQueue<State> queue, Map<Location, Integer> locs, Map<State, ArrayList<State>> parent, State current) {
        Location loc = new Location(state.x, state.y, state.d);
        if (grid[loc.y][loc.x] == '#') return;
        if (locs.containsKey(loc) && locs.get(loc) < state.v) return;
        else if (locs.containsKey(loc) && locs.get(loc) == state.v) {
            locs.put(loc, state.v);
            queue.add(state);
            parent.get(state).add(current);
        } else {
            locs.put(loc, state.v);
            queue.add(state);
            parent.put(state, new ArrayList<>(List.of(current)));
        }
    }

    private void printPath(ArrayList<State> children, Map<State, ArrayList<State>> parentMap) {
        if (children == null) return;

        for (State state : children) {
            if (grid[state.y][state.x] == 'O') continue;
            printPath(parentMap.get(state), parentMap);
            grid[state.y][state.x] = 'O';
        }
    }
}
