import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day2Part1 {
    private static String FILE = "Day2/Day2Input.txt";
    private enum Direction {
            INCREASING,
            DECREASING
    };

    public static void main(String[] args){

        try(Scanner sc = new Scanner(new File(FILE))) {
            int safeCounter = 0;
            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                List<Integer> report = Arrays.stream(line.split(" ")).map(x -> Integer.valueOf(x)).toList();
                if (isSafe(report)) {
                    safeCounter++;
                }
            }

            System.out.println(safeCounter);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }

    private static boolean isSafe(List<Integer> report) {
        // can safely assume at least 2 levels in report
        Direction dir = null;
        for (int i = 0; i < report.size(); i++) {
            if (i > 0){
                int diff = Math.abs(report.get(i) - report.get(i-1));
                if (diff > 3 || diff < 1) {
                    return false;
                }
                // no has a difference at this point
                if (dir == null) {
                    if (report.get(i) > report.get(i-1)) {
                        dir = Direction.INCREASING;
                    } else {
                        dir = Direction.DECREASING;
                    }
                } else if (dir == Direction.INCREASING && report.get(i) < report.get(i-1)) {
                    return false;
                } else if (dir == Direction.DECREASING && report.get(i) > report.get(i-1)) {
                    return false;
                }
            }

        }
        return true;
    }


}


