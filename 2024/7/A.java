import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

    
public class A {
    public static void main(String[] args) throws Exception {
        ArrayList<String> lines = Files.lines(Paths.get("input.txt")).collect(Collectors.toCollection(ArrayList::new));

        ArrayList<ArrayList<Long>> nums = new ArrayList<>();
        for (String line : lines) {
            String newLine = line.replaceAll(":", "");
            nums.add(Arrays.stream(newLine.split("\\s+")).map(Long::parseLong).collect(Collectors.toCollection(ArrayList::new)));
        }

        Long total = 0l;

        for (ArrayList<Long> calibration : nums) {
            if (calculate(calibration)) total += calibration.get(0);
            System.out.println(total);
        }

        System.out.println(total);
    }

    public static boolean calculate(ArrayList<Long> nums) {
        Long value = nums.get(0);

        ArrayList<Long> numbers = new ArrayList<>(nums.subList(1, nums.size()));
        ArrayList<Long> heap = new ArrayList<>();
        heap.add(nums.get(1));

        for (int i = 1; i < numbers.size(); i++) {
            for (int j = 0; j < Math.pow(2, i-1); j++) {
                heap.add(heap.get((heap.size() + 1 )/2 - 1) + numbers.get(i));
                if (i == numbers.size() - 1 && heap.getLast().equals(value)) return true;
                heap.add(heap.get((heap.size() + 1)/2 - 1) * numbers.get(i));
                if (i == numbers.size() - 1 && heap.getLast().equals(value)) return true;
            }
        }

        return false;
    }
}