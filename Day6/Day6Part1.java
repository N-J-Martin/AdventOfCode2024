import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Day6Part1 {

    private static String FILE = "Day6/Day6Input.txt";
    private enum Direction {NORTH, SOUTH, EAST, WEST};
    public static void main(String[] args) {
        List<char[]> grid = new ArrayList<>();
        int[] start = new int[2];
        Direction dir = Direction.NORTH;
        int visited = 1;
        int y = 0;
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                if (row.contains("^")) {
                    int x = row.indexOf('^');
                    row = row.replace('^', 'X');
                    start[0] = x;
                    start[1] = y;
                }
                grid.add(row.toCharArray());
                y++;
            }

            System.out.println(start[0] + ", " + start[1]);
        } catch (FileNotFoundException e) {
            System.err.println("Can't find file");
        }

        int[] pos = Arrays.copyOf(start, 2);
        while (pos[0] >= 0 && pos[0] < grid.get(0).length && pos[1] >= 0 && pos[1] < grid.size()) {
            int guard_x = pos[0];
            int guard_y = pos[1];
            if (dir == Direction.NORTH) {
                if (guard_y > 0 && grid.get(guard_y - 1)[guard_x] == '#') {
                    dir = Direction.EAST;
                } else {
                    if (grid.get(guard_y)[guard_x] != 'X') {
                        grid.get(guard_y)[guard_x] = 'X';
                        visited++;
                    }

                    pos[1] = pos[1] - 1;
                }
            } else if (dir == Direction.EAST) {
                if (guard_x < grid.get(0).length - 1 && grid.get(guard_y)[guard_x + 1] == '#') {
                    dir = Direction.SOUTH;
                } else {
                    if (grid.get(guard_y)[guard_x] != 'X') {
                        grid.get(guard_y)[guard_x] = 'X';
                        visited++;
                    }

                    pos[0] = pos[0] + 1;
                }
            } else if (dir == Direction.SOUTH) {
                if (guard_y < grid.size() - 1 && grid.get(guard_y + 1)[guard_x] == '#') {
                    dir = Direction.WEST;
                } else {
                    if (grid.get(guard_y)[guard_x] != 'X') {
                        grid.get(guard_y)[guard_x] = 'X';
                        visited++;
                    }

                    pos[1] = pos[1] + 1;
                }

            } else {
                if (guard_x > 0 && grid.get(guard_y)[guard_x - 1] == '#') {
                    dir = Direction.NORTH;
                } else {
                    if (grid.get(guard_y)[guard_x] != 'X') {
                        grid.get(guard_y)[guard_x] = 'X';
                        visited++;
                    }

                    pos[0] = pos[0] - 1;
                }
            }
        }

        System.out.println(visited);
    }
}
