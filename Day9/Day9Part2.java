import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day9Part2 {
    private static String FILE = "Day9/Day9Input.txt";
    private static List<Integer> sizes = new ArrayList<>();
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
                Arrays.fill(miniArray, String.valueOf(i/2));
                if (count != 0) {
                    sizes.add(0, count);
                }
            } else {
                Arrays.fill(miniArray, ".");
            }
            expanded.addAll(Arrays.asList(miniArray));
        }
        return expanded;
    }

    private static void defragment(List<String> space) {
        for (int file = 0; file < sizes.size(); file++) {
            boolean moved = false;
            int i = 0;
            //System.out.println(space.indexOf(String.valueOf(sizes.size() - file-1)));
            int index = space.indexOf(String.valueOf(sizes.size() - file-1));
            while (i < index && !moved && i < space.size()) {
                if (space.get(i).equals(".")) {
                    int j = i;
                    while (j < space.size() && space.get(j).equals(".")) {
                        j++;
                    }
                    int max = j-i;
                    if (max >= sizes.get(file)) {
                        sub(space, i, file);
                        moved = true;
                    }
                    i = j + 1;
                } else {
                    i++;
                }

            }
            System.out.println(sizes.size()-file-1);
        }
    }

    private static void sub(List<String> space, int i, int file) {
        String val = String.valueOf(sizes.size() - file - 1);
        for (int s = 0; s < sizes.get(file); s++) {
            int index = space.indexOf(val);
            space.remove(val);
            space.add(index, ".");
        }
        for (int j = i; j < i + sizes.get(file); j++){
            space.remove(j);
            space.add(j, val);
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
