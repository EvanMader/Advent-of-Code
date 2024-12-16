public class ARobot {
    public char[][] grid;
    public int x;
    public int y;
    
    public ARobot(int x, int y, char[][] grid) {
        this.x = x;
        this.y = y;
        this.grid = grid;
    }

    public void move(char c) {
        switch (c) {
            case '^':
                if (checkMove(x, y, 0, -1)) {
                    this.y--;
                }
                break;
            case '>':
                if (checkMove(x, y, 1, 0)) {
                    this.x++;
                }
                break;
            case 'v':
                if(checkMove(x, y, 0, 1)) {
                    this.y++;
                }
                break;
            case '<':
                if(checkMove(x, y, -1, 0)) {
                    this.x--;
                }
                break;
            default:
        }
    }

    public boolean checkMove(int x, int y, int cx, int cy) {
        if (grid[y][x] == '.') return true;
        if (grid[y][x] == '#') return false;
        if (checkMove(x + cx, y + cy, cx, cy)) {
            grid[y+cy][x+cx] = grid[y][x];
            if (grid[y][x] == '@') grid[y][x] = '.';
            return true;
        }
        return false;
    }

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
                if (grid[i][j] == 'O') total += i * 100 + j;
            }
        }

        return total;
    }
}
