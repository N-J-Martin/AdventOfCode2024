import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part1 {
    private static String FILE = "Day5/Day5Input.txt";
    public static void main(String[] args) {
        try(Scanner sc = new Scanner(new File(FILE))) {
            String line = sc.nextLine();
            Map<String, List<String>> ordering = new HashMap<>();
            while (line.contains("|")) {
                //page ordering rules
                String[] order = line.split("\\|");
                if (ordering.containsKey(order[0].strip())) {
                    ordering.get(order[0].strip()).add(order[1].strip());
                } else {
                    ordering.put(order[0].strip(), new ArrayList<>(List.of(order[1].strip())));
                }

                line = sc.nextLine();
            }
            //System.out.println(ordering);
            // updated pages
            int sum = 0;
            while (sc.hasNextLine()) {
                List<String> updatedPages = List.of(sc.nextLine().split(","));
                //System.out.println(updatedPages);
                if (correctOrder(ordering, updatedPages)) {
               //     System.out.println(updatedPages.get(updatedPages.size()/2));
                    sum += Integer.parseInt(updatedPages.get(updatedPages.size()/2));
                }
            }
            System.out.println(sum);


        } catch (FileNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    private static boolean correctOrder(Map<String, List<String>> ordering, List<String> updatedPages) {
        for (Map.Entry<String, List<String>> entry: ordering.entrySet()) {
            int keyIndex = updatedPages.indexOf(entry.getKey());
            if (keyIndex != -1){
                for (String page: entry.getValue()){
                    int valIndex = updatedPages.indexOf(page);
                    if (valIndex != -1) {
                        if (keyIndex >= valIndex) {
                            return false;
                        }
                    }
                }

            }
        }
        return true;
    }
}
