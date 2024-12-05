import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class A {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));

        HashMap<Integer, ArrayList<Integer>> map = new HashMap<>();
        String line = br.readLine();
        while (!line.equals("")) {
            String[] nums = line.split("\\|");
            map.putIfAbsent(Integer.parseInt(nums[1]), new ArrayList<>());
            map.get(Integer.parseInt(nums[1])).add(Integer.parseInt(nums[0]));
            line = br.readLine();
        }

        ArrayList<ArrayList<Integer>> pages = new ArrayList<>();

        line = br.readLine();
        while (line != null) {
            ArrayList<Integer> nums = Arrays.stream(line.split(",")).map(Integer::parseInt).collect(Collectors.toCollection(ArrayList::new));
            pages.add(nums);
            line = br.readLine();
        }

        int total = 0;
        boolean count = true;
        for (int i = 0; i < pages.size(); i++) {
            for (int j = 0; j < pages.get(i).size(); j++) {
                if (map.containsKey(pages.get(i).get(j))) {
                    for (int k = j + 1; k < pages.get(i).size(); k++) {
                        if (map.get(pages.get(i).get(j)).contains(pages.get(i).get(k))) {
                            count = false;
                        }
                    }
                }
            }
            if (count) total += pages.get(i).get(pages.get(i).size()/2);
            count = true;
        }

        System.out.println(total);

    }
}