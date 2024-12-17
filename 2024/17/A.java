import java.util.*;
import java.util.regex.*;
import java.io.*;

public class A {
    public static int a = 0;
    public static int b = 0;
    public static int c = 0;
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new FileReader(new File("input.txt")));
        
        Pattern pattern = Pattern.compile("\\d+");
        String line;
        line = br.readLine();
        Matcher matcher = pattern.matcher(line);
        matcher.find();
        a = Integer.parseInt(matcher.group());
        line = br.readLine();
        matcher = pattern.matcher(line);
        matcher.find();
        b = Integer.parseInt(matcher.group());
        line = br.readLine();
        matcher = pattern.matcher(line);
        matcher.find();
        c = Integer.parseInt(matcher.group());
        
        line = br.readLine();
        line = br.readLine();

        matcher = pattern.matcher(line);
        ArrayList<Integer> inputs = new ArrayList<>();

        while (matcher.find()) {
            inputs.add(Integer.parseInt(matcher.group()));
        }

        StringBuilder out = new StringBuilder();

        for (int i = 0; i < inputs.size(); i++) {
            switch(inputs.get(i)) {
                case 0:
                    i++;
                    int value0 = (int) Math.pow(2, getCombo(inputs.get(i)));
                    if (value0 == 0) a = 0;
                    else a = a / value0;
                    break;
                case 1:
                    i++;
                    b = b ^ inputs.get(i);
                    break;
                case 2:
                    i++;
                    b = getCombo(inputs.get(i)) % 8;
                    break;
                case 3:
                    i++;
                    if (a == 0) continue;
                    i = inputs.get(i) - 1;
                    break;
                case 4:
                    i++;
                    b = b ^ c;
                    break;
                case 5:
                    i++;
                    out.append(getCombo(inputs.get(i)) % 8 + ",");
                    break;
                case 6:
                    i++;
                    int value6 = (int) Math.pow(2, getCombo(inputs.get(i)));
                    if (value6 == 0) b = 0;
                    else b = a / value6;
                    break;
                case 7:
                    i++;
                    int value7 = (int) Math.pow(2, getCombo(inputs.get(i)));
                    if (value7 == 0) c = 0;
                    else c = a / value7;
                    break;
                default:
                    System.out.println("ERROR BAD OPCODE");
            }
        }

        System.out.println(out);
    }

    public static int getCombo(int v) {
        switch (v) {
            case 0:
            case 1:
            case 2:
            case 3:
                return v;
            case 4:
                return a;
            case 5:
                return b;
            case 6:
                return c;
            default:
                System.out.println("GET COMBO ERROR " + v);
        }

        return -1;
    }
}