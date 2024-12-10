import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8Part1 {
    private static String FILE = "Day8/Day8Input.txt";
    public static void main(String[] args) {
        try (Scanner sc = new Scanner(new File(FILE)) ){
            Map<Character, List<List<Integer>>> frequencies = new HashMap<>();
            Set<List<Integer>> antinodes = new HashSet<>();
            int y = 0;
            int maxRowLength = 0;
            while (sc.hasNextLine()){
                char[] row = sc.nextLine().toCharArray();
                maxRowLength = row.length;
                for (int x = 0; x < row.length; x++){
                    if (row[x] != '.') {
                        if (frequencies.containsKey(row[x])) {
                            frequencies.get(row[x]).add(List.of(x, y));
                        } else {
                            List coords = new ArrayList();
                            coords.add(List.of(x, y));
                            frequencies.put(row[x], coords);
                        }
                    }
                }
                y++;
            }
            //System.out.println(y);

            // get distance between all nodes and antinodes
            for (List<List<Integer>> nodes: frequencies.values()){
                for (int i = 0; i < nodes.size()-1; i++) {
                    for (int j = i + 1; j < nodes.size(); j++) {
                        int xDiff = getXdiff(nodes.get(i), nodes.get(j));
                        int yDiff = getYdiff(nodes.get(i), nodes.get(j));
                        antinodes.add(List.of((nodes.get(i).get(0) + xDiff), (nodes.get(i).get(1) + yDiff)));
                        antinodes.add(List.of((nodes.get(j).get(0) - xDiff), (nodes.get(j).get(1) - yDiff)));
                    }
                }
            }

            //System.out.println(antinodes);
            int finalY = y;
            int finalMaxRowLength = maxRowLength;
            System.out.println(antinodes.stream().filter(x -> x.get(0) >= 0 && x.get(0) < finalMaxRowLength && x.get(1) >= 0 && x.get(1) < finalY).count());



        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static int getYdiff(List<Integer> a, List<Integer> b) {
        return a.get(1) - b.get(1);
    }

    private static int getXdiff(List<Integer> a, List<Integer> b) {
        return a.get(0) - b.get(0);
    }
}
