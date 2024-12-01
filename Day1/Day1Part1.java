import java.io.File;
import java.io.FileNotFoundException;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Day1Part1 {
    private static String FILE = "Day1Input.txt";
    public static void main(String[] args) {
        Queue<Integer> queue1  = new PriorityQueue<>();
        Queue<Integer> queue2 = new PriorityQueue<>();

        // read file into priority queues
        try (Scanner reader = new Scanner(new File(FILE))){
            while (reader.hasNextInt()) {
                queue1.add(reader.nextInt());
                queue2.add(reader.nextInt());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        // add difference in all priority queues
        int hammingDistance = 0;
        while (!queue1.isEmpty()){
            hammingDistance = hammingDistance + Math.abs(queue1.remove() - queue2.remove());
        }

        System.out.println(hammingDistance);


    }

}