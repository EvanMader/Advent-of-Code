import java.util.*;
import java.io.*;

public class B {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        String line = br.readLine();
        String[] allTowels = line.replaceAll(" ", "").split(",");
        br.readLine();

        int max = allTowels[0].length();
        for (int i = 1; i < allTowels.length; i++) {
            if (max < allTowels[i].length()) max = allTowels[i].length();
        }

        ArrayList<ArrayList<String>> towels = new ArrayList<>();
        for (int i = 0; i < max; i++) towels.add(new ArrayList<>());

        for (String towel : allTowels) towels.get(towel.length() - 1).add(towel);
        
        long total = 0;
        while ((line = br.readLine()) != null) {
            ArrayList<State> states = createStates(line);
            total += calculate(states, towels);
        }
        br.close();

        System.out.println(total);
    }

    public static long calculate(ArrayList<State> states, ArrayList<ArrayList<String>> towels) {
        for (int i = 0; i < states.size(); i++) {
            for (int j = i + 1; j < states.size(); j++) {
                int difference = states.get(j).str.length() - states.get(i).str.length();
                if (difference > towels.size()) break;

                if (convert(states.get(i), states.get(j), towels.get(difference - 1))) states.get(j).num += states.get(i).num;
            }
        }

        return states.getLast().num;
    }

    public static boolean convert(State one, State two, ArrayList<String> towels) {
        for (String towel : towels) {
            if ((one.str + towel).equals(two.str)) return true;
        }
        return false;
    }

    private static class State {
        String str;
        long num = 0;

        public State(String str) {
            this.str = str;
        }
    }

    public static ArrayList<State> createStates(String str) {
        ArrayList<State> states = new ArrayList<>();

        for (int i = 0; i < str.length() + 1; i++) {
            states.add(new State(str.substring(0, i)));
        }
        states.getFirst().num++;

        return states;
    }
}