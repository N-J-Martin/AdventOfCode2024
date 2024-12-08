import javax.print.DocFlavor;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day6Part2 {

    private static String FILE = "Day6/Day6Input.txt";
    private static Set<Character> path = new HashSet<>();
    private static Set<Character> north = new HashSet<>();
    private static Set<Character> east = new HashSet<>();
    private static Set<Character> south = new HashSet<>();
    private static Set<Character> west = new HashSet<>();
    private enum Direction {NORTH, SOUTH, EAST, WEST};
    public static void main(String[] args) {
        path.add('N');
        path.add('E');
        path.add('S');
        path.add('W');
        path.add('|'); // both N and S,
        path.add('-'); // both E and W,
        path.add('T'); // both N and E,
        path.add('+'); // both N and W,
        path.add('\\'); // both S and E
        path.add('/'); // both S and W
        path.add('%'); // N, E and W
        path.add('~'); // S, E and W
        path.add('&'); // N, S, and E
        path.add('$'); // N, S, and W
        path.add('*'); // N, S, E and W

        north.add('N');
        north.add('|'); // both N and S,
        north.add('T'); // both N and E,
        north.add('+'); // both N and W,
        north.add('%'); // N, E and W
        north.add('&'); // N, S, and E
        north.add('$'); // N, S, and W
        north.add('*'); // N, S, E and W

        south.add('S');
        south.add('|'); // both N and S,
        south.add('\\'); // both S and E
        south.add('/'); // both S and W
        south.add('~'); // S, E and W
        south.add('&'); // N, S, and E
        south.add('$'); // N, S, and W
        south.add('*'); // N, S, E and W

        east.add('E');
        east.add('-'); // both E and W,
        east.add('T'); // both N and E,
        east.add('\\'); // both S and E
        east.add('%'); // N, E and W
        east.add('~'); // S, E and W
        east.add('&'); // N, S, and E
        east.add('*'); // N, S, E and W

        west.add('W');
        west.add('-'); // both E and W,
        west.add('+'); // both N and W,
        west.add('/'); // both S and W
        west.add('%'); // N, E and W
        west.add('~'); // S, E and W
        west.add('$'); // N, S, and W
        west.add('*'); // N, S, E and W


        List<char[]> grid = new ArrayList<>();
        Set<List<Integer>> searchSpace = new HashSet<>();
        int[] start = new int[2];
        Direction dir = Direction.NORTH;
        int visited = 1;
        int y = 0;
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()){
                String row = sc.nextLine();
                if (row.contains("^")) {
                    int x = row.indexOf('^');
                    //row = row.replace('^', 'X');
                    start[0] = x;
                    start[1] = y;
                    searchSpace.add(List.of(start[0], start[1]));
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
                    if (!searchSpace.contains(List.of(guard_x, guard_y))) {
                        searchSpace.add(List.of(guard_x, guard_y));
                        visited++;
                    }

                    pos[1] = pos[1] - 1;
                }
            } else if (dir == Direction.EAST) {
                if (guard_x < grid.get(0).length - 1 && grid.get(guard_y)[guard_x + 1] == '#') {
                    dir = Direction.SOUTH;
                } else {
                    if (!searchSpace.contains(List.of(guard_x, guard_y))) {
                        searchSpace.add(List.of(guard_x, guard_y));
                        visited++;
                    }

                    pos[0] = pos[0] + 1;
                }
            } else if (dir == Direction.SOUTH) {
                if (guard_y < grid.size() - 1 && grid.get(guard_y + 1)[guard_x] == '#') {
                    dir = Direction.WEST;
                } else {
                    if (!searchSpace.contains(List.of(guard_x, guard_y))) {
                        searchSpace.add(List.of(guard_x, guard_y));
                        visited++;
                    }

                    pos[1] = pos[1] + 1;
                }

            } else {
                if (guard_x > 0 && grid.get(guard_y)[guard_x - 1] == '#') {
                    dir = Direction.NORTH;
                } else {
                    if (!searchSpace.contains(List.of(guard_x, guard_y))) {
                        searchSpace.add(List.of(guard_x, guard_y));
                        visited++;
                    }

                    pos[0] = pos[0] - 1;
                }
            }
        }

        //System.out.println(visited);
        //System.out.println(searchSpace.size());
        //grid.get(8)[1]='#';
        //System.out.println(isCycle(grid, start));
        int count = 0;
        for (List<Integer> s: searchSpace){
            List<char[]> copy = copyGrid(grid);

            copy.get(s.get(1))[s.get(0)] = '#';
            //printGrid(copy);
            if (isCycle(copy, start)){
                count++;
            }

        }
        System.out.println(count);
    }

    private static void printGrid(List<char[]> grid) {
        for (char[] row: grid) {
            for (char c: row){
                System.out.print(c);
                System.out.print(" ");
            }
            System.out.print("\n");
        }
    }

    private static List<char[]> copyGrid(List<char[]> grid) {
        List<char[]> copy = new ArrayList<>(grid);
        for( int i = 0; i < grid.size(); i++){
            char[] row = copy.get(i);
            row = Arrays.copyOf(copy.get(i), grid.size());
            copy.add(i, row);
            copy.remove(i+1);

        }
        return copy;
    }

    public static boolean isCycle(List<char[]> grid, int[] start) {
        // runs and determines if cycle or exits
        Direction dir = Direction.NORTH;

        int[] pos = Arrays.copyOf(start, 2);
        while ((pos[0] >= 0 && pos[0] < grid.get(0).length && pos[1] >= 0 && pos[1] < grid.size())){
            int guard_x = pos[0];
            int guard_y = pos[1];
            if (dir == Direction.NORTH) {
                if (guard_y > 0 && grid.get(guard_y - 1)[guard_x] == '#') {
                    dir = Direction.EAST;
                } else {
                    if (north.contains(grid.get(guard_y)[guard_x])) {
                        return true;
                    } else {
                        grid.get(guard_y)[guard_x] = getNewSymbol(grid.get(guard_y)[guard_x], dir);
                    }
                    pos[1] = pos[1] - 1;
                }
            } else if (dir == Direction.EAST) {
                if (guard_x < grid.get(0).length - 1 && grid.get(guard_y)[guard_x + 1] == '#') {
                    dir = Direction.SOUTH;
                } else {
                    if (east.contains(grid.get(guard_y)[guard_x])) {
                        return true;
                    } else {
                        grid.get(guard_y)[guard_x] = getNewSymbol(grid.get(guard_y)[guard_x], dir);
                    }

                    pos[0] = pos[0] + 1;
                }
            } else if (dir == Direction.SOUTH) {
                if (guard_y < grid.size() - 1 && grid.get(guard_y + 1)[guard_x] == '#') {
                    dir = Direction.WEST;
                } else {
                    if (south.contains(grid.get(guard_y)[guard_x])) {
                        return true;
                    } else {
                        grid.get(guard_y)[guard_x] = getNewSymbol(grid.get(guard_y)[guard_x], dir);
                    }

                    pos[1] = pos[1] + 1;
                }

            } else {
                if (guard_x > 0 && grid.get(guard_y)[guard_x - 1] == '#') {
                    dir = Direction.NORTH;
                } else {
                    if (west.contains(grid.get(guard_y)[guard_x])) {
                        return true;
                    } else {
                        grid.get(guard_y)[guard_x] = getNewSymbol(grid.get(guard_y)[guard_x], dir);
                    }

                    pos[0] = pos[0] - 1;
                }
            }
        }

        return false;
    }

    private static char getNewSymbol(char c, Direction dir) {

        switch (dir) {
            case EAST -> {
                if (north.contains(c) & south.contains(c) & west.contains(c)) {
                   return '*';
                } else if (north.contains(c) & south.contains(c) ) {
                    return '&';
                } else if (north.contains(c) & west.contains(c)) {
                    return '%';
                } else if (south.contains(c) & west.contains(c)) {
                    return '~';
                } else if (south.contains(c)) {
                    return '\\';
                } else if (north.contains(c)) {
                    return 'T';
                } else if (west.contains(c)) {
                    return '-';
                } else {
                    return 'E';
                }
            }

            case NORTH -> {
                if (east.contains(c) & south.contains(c) & west.contains(c)) {
                    return '*';
                } else if (west.contains(c) & south.contains(c) ) {
                    return '$';
                } else if (east.contains(c) & west.contains(c)) {
                    return '%';
                } else if (south.contains(c) & east.contains(c)) {
                    return '&';
                } else if (south.contains(c)) {
                    return '|';
                } else if (east.contains(c)) {
                    return 'T';
                } else if (west.contains(c)) {
                    return '+';
                } else {
                    return 'N';
                }
            }

            case SOUTH -> {
                if (east.contains(c) & north.contains(c) & west.contains(c)) {
                    return '*';
                } else if (west.contains(c) & north.contains(c) ) {
                    return '$';
                } else if (east.contains(c) & west.contains(c)) {
                    return '~';
                } else if (north.contains(c) & east.contains(c)) {
                    return '&';
                } else if (north.contains(c)) {
                    return '|';
                } else if (east.contains(c)) {
                    return '\\';
                } else if (west.contains(c)) {
                    return '/';
                } else {
                    return 'S';
                }

            }

            case WEST -> {
                if (north.contains(c) & south.contains(c) & east.contains(c)) {
                    return '*';
                } else if (north.contains(c) & south.contains(c) ) {
                    return '$';
                } else if (north.contains(c) & east.contains(c)) {
                    return '%';
                } else if (south.contains(c) & east.contains(c)) {
                    return '~';
                } else if (south.contains(c)) {
                    return '/';
                } else if (north.contains(c)) {
                    return '+';
                } else if (east.contains(c)) {
                    return '-';
                } else {
                    return 'W';
                }

            }
        }
        return c;
    }
}
