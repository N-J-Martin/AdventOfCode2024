import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day8Part2 {
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
                        int posX = nodes.get(i).get(0);
                        int posY = nodes.get(i).get(1);
                        while (posX >= 0 && posX < maxRowLength && posY >= 0 && posY < y){
                            antinodes.add(List.of(posX, posY));
                            posX = posX + xDiff;
                            posY = posY + yDiff;
                        }

                        posX = nodes.get(i).get(0) - xDiff;
                        posY = nodes.get(i).get(1) - yDiff;
                        while (posX >= 0 && posX < maxRowLength && posY >= 0 && posY < y){
                            antinodes.add(List.of(posX, posY));
                            posX = posX - xDiff;
                            posY = posY - yDiff;
                        }
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
