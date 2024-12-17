import java.math.BigInteger;

public class B {
    public static void main(String[] args) {
        BigInteger a = new BigInteger("0");
        int[] answer = {2,4,1,7,7,5,1,7,0,3,4,1,5,5,3,0};

        BigInteger b, c;
        BigInteger save = BigInteger.ZERO;

        for (int result = 15; result >= 0; result--) {
            for (int i = result; i < 16;) {
                b = a.mod(BigInteger.valueOf(8));
                b = b.xor(BigInteger.valueOf(7));
                c = a.shiftRight(b.intValue());
                a = a.divide(BigInteger.valueOf(8));
                b = b.xor(BigInteger.valueOf(7));
                b = b.xor(c);
                if (b.mod(BigInteger.valueOf(8)).intValue() == answer[result]) {
                    i++;
                    continue;
                } else {
                    save = save.add(BigInteger.ONE);
                    a = save.add(BigInteger.ZERO);
                    i = result;
                }
            }
            a = save.multiply(BigInteger.valueOf(8));
            save = save.multiply((BigInteger.valueOf(8)));
            System.out.println(a);
        }
    }
}