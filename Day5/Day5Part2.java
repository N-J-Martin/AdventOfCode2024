import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day5Part2 {
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
                if (!correctOrder(ordering, updatedPages)) {
                    List<String> corrected = correctOrdering(ordering, updatedPages);
                    //System.out.println(corrected);
                    sum += Integer.parseInt(corrected.get(corrected.size()/2));
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

    private static List<String> correctOrdering(Map<String, List<String>> ordering, List<String> updatedPages) {
        List<String> copy = new ArrayList<>(updatedPages);
        copy.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {

                if (ordering.containsKey(o1) && ordering.get(o1).contains(o2)){
                    return -1;
                } else if (ordering.containsKey(o2) && ordering.get(o2).contains(o1)){
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        return copy;
    }
}
