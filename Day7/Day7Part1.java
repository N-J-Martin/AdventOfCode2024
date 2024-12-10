import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

public class Day7Part1 {
    private static String FILE = "Day7/Day7Input.txt";

    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File(FILE))) {
            long sum = 0;
            while (sc.hasNextLine()) {
                //page ordering rules
                String line = sc.nextLine();
                String[] equation = line.split(":");
                long target = Long.parseLong(equation[0]);
                String[] values = equation[1].strip().split(" ");
                //System.out.println("found: " + target);
                if (solvable(target, values)) {
                    sum = sum + target;
                    System.out.println(target);
                }
            }

            System.out.println(sum);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static boolean solvable(long target, String[] values) {
        double possibilities = Math.pow(2, values.length-1);
        String format = "%" + (values.length-1) + "s";
        BigInteger counter = new BigInteger("0");
        while (counter.intValue() < possibilities) {
            long partial = Long.parseLong(values[0]);
            String strCount = String.format(format, counter.toString(2)).replace(' ', '0');

            for (int i = 0; i < values.length - 1; i++) {
                if (strCount.charAt(i) == '1') {
                    partial = partial * Long.parseLong(values[i+1]);
                } else {
                    partial = partial + Long.parseLong(values[i+1]);
                }
            }

            if (partial == target) {
                return true;
            }
            counter = counter.add(new BigInteger("1"));

        }

        return false;
    }

}
