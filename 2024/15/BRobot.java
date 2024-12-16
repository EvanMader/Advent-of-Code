import java.util.*;

public class BRobot {
    public char[][] grid;
    public int x;
    public int y;
    public Set<Location> locs;
    
    public BRobot(int x, int y, char[][] grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public void move(char c) {
        switch (c) {
            case '^':
                if (checkMove(x, y, 0, -1, new HashSet<>())) {
                    movey(x, y, -1, new HashSet<>());
                    this.y--;
                }
                break;
            case '>':
                if (checkMove(x, y, 1, 0, new HashSet<>())) {
                    movex(x, y, 1, '.');
                    this.x++;
                }
                break;
            case 'v':
                if(checkMove(x, y, 0, 1, new HashSet<>())) {
                    movey(x, y, 1, new HashSet<>());
                    this.y++;
                }
                break;
            case '<':
                if(checkMove(x, y, -1, 0, new HashSet<>())) {
                    movex(x, y, -1, '.');
                    this.x--;
                }
                break;
            default:
        }
    }

    public boolean checkMove(int x, int y, int cx, int cy, Set<Location> locs) {
        if (locs.contains(new Location(x, y))) return true;
        locs.add(new Location(x, y));
        if (grid[y][x] == '#') return false;
        if (grid[y][x] == '.') return true;

        if (grid[y][x] == '[') return checkMove(x+cx, y+cy, cx, cy, locs) && checkMove(x+1, y, cx, cy, locs);
        if (grid[y][x] == ']') return checkMove(x+cx, y+cy, cx, cy, locs) && checkMove(x-1, y, cx, cy, locs);

        if (grid[y][x] == '@') return checkMove(x+cx, y+cy, cx, cy, locs);

        return true;
    }

    public void movex(int x, int y, int cx, char c) {
        char newC = grid[y][x];
        grid[y][x] = c;
        if (newC == '.') return;

        movex(x + cx, y, cx, newC);
    }

    public void movey(int x, int y, int cy, Set<Location> locs) {
        if (locs.contains(new Location(x, y))) return;
        locs.add(new Location(x, y));
        if (grid[y][x] == '.') return;
        movey(x, y+cy, cy, locs);

        int dir = 0;
        if (grid[y][x] == '[') dir = -1;
        else if (grid[y][x] == ']') dir = 1;
        dir *= -1;

        if (dir != 0) {
            movey(x+dir, y, cy, locs);
        }

        grid[y+cy][x] = grid[y][x];
        grid[y][x] = '.';
    }

    private record Location(int x, int y) {}

    public void printGrid() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                System.out.print(grid[i][j]);
            }
            System.out.println();
        }
    }

    public int GPS() {
        int total = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] == '[') total += i * 100 + j;
            }
        }

        return total;
    }
}
