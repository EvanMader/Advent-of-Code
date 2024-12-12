import java.util.*;
import java.nio.file.*;
import java.io.*;
import java.util.stream.*;

public class A {
    public static void main(String[] args) throws Exception {
        ArrayList<Long> stones = Files.lines(Paths.get("input.txt")).findFirst().map(line -> Arrays.stream(line.split(" ")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new))).orElse(new ArrayList<>());

        for (int blink = 0; blink < 25; blink++) {
            for (int i = 0; i < stones.size(); i++) {
                if (stones.get(i).equals(0l)) {
                    stones.set(i, 1l);
                    continue;
                }
                if (even(stones.get(i))) {
                    long num = stones.get(i);
                    stones.set(i, leftHalf(stones.get(i)));
                    i++;
                    stones.add(i, rightHalf(num));
                    continue;
                }
                stones.set(i, stones.get(i) * 2024);
            }
            System.out.println(blink);
        }

        System.out.println(stones.size());

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