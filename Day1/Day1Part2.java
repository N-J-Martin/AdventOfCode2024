import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Day1Part2 {

    private static String FILE = "Day1Input.txt";
    public static void main (String[] args){
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();

        // read file into lists (don't need ordering anymore)
        try (Scanner reader = new Scanner(new File(FILE))){
            while (reader.hasNextInt()) {
                list1.add(reader.nextInt());
                list2.add(reader.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // calculate similarity score
        int similarityScore = 0;

        for (int i: list1) {
            int count = Collections.frequency(list2, i);
            similarityScore = similarityScore + (i * count);
        }

        System.out.println(similarityScore);
    }

}
