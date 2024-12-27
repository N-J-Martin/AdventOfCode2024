import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day10Part1 {

    private static String FILE = "Day10/Day10Input.txt";

    public static void main(String[] args) {
        List<char[]> grid = new ArrayList<>();
        List<List<Integer>> start = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(FILE))) {
            int y = 0;
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                grid.add(row.toCharArray());
                for (int x = 0; x < row.length(); x++) {
                    if (row.charAt(x) == '0') {
                        start.add(List.of(x, y));
                    }
                }
                y++;
            }

            System.out.println(DFS(start, grid));
        } catch (FileNotFoundException e) {
            System.err.println("Can't find file");
        }
    }

    public static int DFS(List<List<Integer>> start, List<char[]> grid) {
        int total = 0;
        for (List<Integer> s: start) {
            int score = 0;
            Set<List<Integer>> ends = new HashSet<>();
            Deque<Node> stack = new ArrayDeque<>();
            stack.addFirst(new Node(s.get(0), s.get(1)));

            while (!stack.isEmpty()) {
                Node current = stack.removeFirst();
                if (current.getHeight() == 9) {
                    if (!ends.contains(current.getPos())) {
                        score++;
                        ends.add(current.getPos());
                    }

                } else {
                    List<Node> successors = current.successors(grid);
                    for (Node next: successors){
                        stack.addFirst(next);
                    }
                }
            }
            System.out.println(score);
            total = total + score;
        }

        return total;
    }
}
