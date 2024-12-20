import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day9Part1 {
    private static String FILE = "Day9/Day9Input.txt";
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File(FILE))) {
            String line = sc.nextLine();
            List<String> memory = RLEexpansion(line);
            System.out.println(memory);
            defragment(memory);
            System.out.println(memory);
            System.out.println(sum(memory));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static List<String> RLEexpansion(String encoding ) {
        List<String> expanded = new ArrayList<>();
        for (int i = 0; i < encoding.length() ; i++) {
            int count = Integer.parseInt(String.valueOf(encoding.charAt(i)));
            String[] miniArray = new String[count];
            if (i % 2 == 0) {
                /*
                if (count == 0) {
                    System.out.println("error?");
                }
                */
                Arrays.fill(miniArray, String.valueOf(i/2));
            } else {
                Arrays.fill(miniArray, ".");
            }
            expanded.addAll(Arrays.asList(miniArray));
        }
        return expanded;
    }

    private static void defragment(List<String> space) {
        int i = 0;
        while (i < space.size()) {
            if (space.get(i).equals(".")) {
                int removal = space.size() - 1;
                while (removal > i && space.get(removal).equals(".")) {
                    removal--;
                }
                if (removal > i){
                    space.remove(i);
                    space.add(i, space.get(removal-1));
                    space.remove(removal);
                    space.add(removal, ".");
                }

            }
            i++;
        }
    }

    private static long sum(List<String> space) {
        long sum = 0;
        for (int i = 0; i < space.size(); i++) {
            if (!space.get(i).equals(".")){
                sum = sum + (i * Integer.parseInt(space.get(i)));
            }

        }
        return sum;
    }

}
