import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Day3Part1 {
    private static String FILE = "Day3/Day3Input.txt";

    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new File(FILE))) {
            int sum = 0;
            boolean enabled = true;
            String match = sc.findInLine(Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))|(don't\\(\\))|(do\\(\\))"));
            while (match != null) {
                if (match.equals("don't()")) {
                    enabled = false;
                } else if (match.equals("do()")) {
                    enabled = true;
                } else if (enabled) {
                    match = match.replace("mul(", "");
                    match = match.replace(")", "");
                    String[] ints = match.split(",");
                    sum = sum + (Integer.parseInt(ints[0]) * Integer.parseInt(ints[1]));
                }
                match = sc.findInLine(Pattern.compile("(mul\\(\\d{1,3},\\d{1,3}\\))|(don't\\(\\))|(do\\(\\))"));
            }
            System.out.println(sum);
        } catch (FileNotFoundException e) {
            System.out.println("can't find file");
        }
    }
}
