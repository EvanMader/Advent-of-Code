public class Robot {
    int x;
    int y;
    int vx;
    int vy;
    
    public Robot(int x, int y, int vx, int vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }

    public String toString() {
        return "X: "  + x + " Y: " + y;
    }

    public void move() {
        move(1);
    }

    public void move(int i) {
        for (int j = 0; j < i; j++) {
            x = ((x + vx) % 101 + 101) % 101;
            y = ((y + vy) % 103 + 103) % 103;
        }
    }

    public int quad() {
        if (x > 50) {
            if (y > 51) return 4;
            if (y < 51) return 1;
        }
        if (x < 50) {
            if (y < 51) return 2;
            if (y > 51) return 3;
        }

        return -1;
    }
}
