import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class B {
    public static void main(String[] args) throws Exception {
        ArrayList<String> lines = Files.lines(Paths.get("input.txt")).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ArrayList<BigInteger>> nums = new ArrayList<>();
        for (String line : lines) {
            String newLine = line.replaceAll(":", "");
            nums.add(Arrays.stream(newLine.split("\\s+")).map(BigInteger::new).collect(Collectors.toCollection(ArrayList::new)));
        }

        BigInteger total = BigInteger.ZERO;

        for (ArrayList<BigInteger> calibration : nums) {
            if (calculate(calibration)) total = total.add(calibration.get(0));
            System.out.println(total);
        }

        System.out.println(total);
    }

    public static boolean calculate(ArrayList<BigInteger> nums) {
        BigInteger value = nums.get(0);
        ArrayList<BigInteger> numbers = new ArrayList<>(nums.subList(1, nums.size()));
        ArrayList<BigInteger> heap = new ArrayList<>();
        heap.add(nums.get(1));

        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < Math.pow(3, i-1); j++) {
                heap.add(heap.get((heap.size() + 2) / 3 - 1).add(numbers.get(i)));
                if (i == numbers.size() - 1 && heap.get(heap.size() - 1).equals(value)) return true;

                heap.add(heap.get((heap.size() + 2) / 3 - 1).multiply(numbers.get(i)));
                if (i == numbers.size() - 1 && heap.get(heap.size() - 1).equals(value)) return true;

                BigInteger concatenatedValue = new BigInteger(heap.get((heap.size() + 2) / 3 - 1).toString() + numbers.get(i).toString());
                heap.add(concatenatedValue);
                if (i == numbers.size() - 1 && heap.get(heap.size() - 1).equals(value)) return true;
            }
        }

        return false;
    }
}