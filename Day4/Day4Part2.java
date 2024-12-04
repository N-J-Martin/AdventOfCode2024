import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4Part2 {
    private static String FILE = "Day4/Day4Input.txt";
    public static void main(String[] args) {
        List<char[]> grid = new ArrayList<>();
        try (Scanner sc = new Scanner(new File(FILE))) {
            while (sc.hasNextLine()){
                grid.add(sc.nextLine().toCharArray());
            }

            System.out.println(searchForXmas(grid));
        } catch (FileNotFoundException e) {
            System.err.println("Can't find file");
        }
    }

    private static int searchForXmas(List<char[]> grid) {
        int count = 0;
        for (int row = 0; row < grid.size(); row++){
            for(int col = 0; col < grid.get(row).length; col++) {
                if (grid.get(row)[col] == 'A') {
                      if (isMAS(grid, row, col)){
                          count++;
                      }

                }
            }
        }
        return count;
    }

    private static boolean isMAS(List<char[]> grid, int row, int col) {
        if (row < 1 || col < 1 || row > grid.size()-2 || col > grid.get(row).length-2) {
            return false;
        }
        StringBuilder sb1 = new StringBuilder();
        StringBuilder sb2 = new StringBuilder();
        sb1.append(grid.get(row-1)[col-1]);
        sb1.append(grid.get(row)[col]);
        sb1.append(grid.get(row+1)[col+1]);

        sb2.append(grid.get(row+1)[col-1]);
        sb2.append(grid.get(row)[col]);
        sb2.append(grid.get(row-1)[col+1]);

        return (sb1.toString().equals("MAS") || sb1.reverse().toString().equals("MAS")) && (sb2.toString().equals("MAS") || sb2.reverse().toString().equals("MAS"));
    }
}
