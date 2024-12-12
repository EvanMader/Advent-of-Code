import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.math.BigInteger;
import java.util.stream.*;

public class B {
    public static void main(String[] args) throws Exception {
        ArrayList<Long> stones = Files.lines(Paths.get("input.txt")).findFirst().map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new))).orElse(new ArrayList<>());

        ArrayList<Rock> rocks = new ArrayList<>();
        for (Long stone : stones) {
            rocks.add(new Rock(stone, 1l));
        }



        for (int blink = 0; blink < 75; blink++) {
            for (int i = 0; i < rocks.size(); i++) {
                for (int j = 0; j < rocks.size(); j++) {
                    if (i == j) continue;
                    if (rocks.get(i).equals(rocks.get(j))) {
                        rocks.get(i).multiplier += rocks.get(j).multiplier;
                        rocks.remove(j);
                        j--;
                    }
                }
            }

            for (int i = 0; i < rocks.size(); i++) {
                if (rocks.get(i).rock.equals(0l)) {
                    rocks.get(i).rock = 1l;
                    continue;
                }
                if (even(rocks.get(i).rock)) {
                    long num = rocks.get(i).rock;
                    rocks.get(i).rock = leftHalf(rocks.get(i).rock);
                    rocks.add(i + 1, new Rock(rightHalf(num), rocks.get(i).multiplier));
                    i++;
                    continue;
                }
                rocks.get(i).rock *= 2024;
            }
        }

        BigInteger total = new BigInteger("0");
        for (int i = 0; i < rocks.size(); i++) {
            total = total.add(new BigInteger(String.valueOf(rocks.get(i).multiplier)));
        }
        System.out.println(total);

    }

    public static boolean even(long e) {
        String str = String.valueOf(e);
        if (str.length() % 2 == 0) return true;
        return false;
    }

    public static long leftHalf(long e) {
        String str = String.valueOf(e);
        str = str.substring(0, (str.length()/2));
        return Long.parseLong(str);
    }

    public static long rightHalf(long e) {
        String str = String.valueOf(e);
        str = str.substring((str.length()/2), str.length());
        return Long.parseLong(str);
    }
}