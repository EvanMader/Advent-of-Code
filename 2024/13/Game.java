import java.math.BigInteger;
public class Game {
    long x;
    long y;
    Button a;
    Button b;

    public Game(Long x, Long y, int ax, int ay, int bx, int by) {
        this.x = x;
        this.y = y;
        this.a = new Button(ax, ay);
        this.b = new Button(bx, by);
    }

    public BigInteger tokens() {
        long determinant = a.x * b.y - a.y * b.x;
        if (determinant == 0) return new BigInteger("0");
        double X = (b.y * x - b.x * y) * 1.0 / determinant;
        double Y = (a.x * y - a.y * x) * 1.0 / determinant;

        if (X % 1 == 0 && Y % 1 == 0) {
            return new BigInteger(String.valueOf((long) X * 3 + (long) Y * 1));
        }
        return new BigInteger("0");
    }

    private class Button {
        public int x;
        public int y;

        public Button (int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

}

