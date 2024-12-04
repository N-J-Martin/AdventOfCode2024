import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Day4Part1{
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
                if (grid.get(row)[col] == 'X') {
                      int xmasCount = getXmasCount(grid, row, col);
                      count += xmasCount;
                }
            }
        }
        return count;
    }

    private static int getXmasCount(List<char[]> grid, int row, int col) {
        List<String> alldirs = new ArrayList<>();
        // left
        if (col >= 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row)[col - 1] + grid.get(row)[col - 2] + grid.get(row)[col - 3]);
        }
        // right
        if (col < grid.get(row).length - 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row)[col + 1] + grid.get(row)[col + 2] + grid.get(row)[col + 3]);
        }
        // up
        if (row >= 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row-1)[col] + grid.get(row-2)[col] + grid.get(row-3)[col]);
        }
       // down
        if (row < grid.size() - 3){
            alldirs.add("" + grid.get(row)[col] + grid.get(row+1)[col] + grid.get(row+2)[col] + grid.get(row+3)[col]);
        }

       //topleftdiagonal
        if (row >= 3 && col >= 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row-1)[col-1] + grid.get(row-2)[col-2] + grid.get(row-3)[col-3]);
        }

        //topRightDiagonal
        if (row >= 3 && col < grid.get(row).length - 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row-1)[col+1] + grid.get(row-2)[col+2] + grid.get(row-3)[col+3]);
        }

        //bottomLeftdiagonal
        if (row < grid.size() - 3 && col >= 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row+1)[col-1] + grid.get(row+2)[col-2] + grid.get(row+3)[col-3]);

        }

        //bottomRight diagonal
        if (row < grid.size() - 3 && col <grid.get(row).length - 3) {
            alldirs.add("" + grid.get(row)[col] + grid.get(row+1)[col+1] + grid.get(row+2)[col+2] + grid.get(row+3)[col+3]);
        }

        int count = 0;
        for (String dir: alldirs) {
            if (dir.equals("XMAS")) {
                count++;
            }
        }
        return count;
    }
}
