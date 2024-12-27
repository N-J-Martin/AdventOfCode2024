import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Node {
    private int x;
    private int y;

    private int height;
    private Node parent;
    private int cost;

    public Node(int x, int y, Node parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
        this.cost = parent.cost + 1;
        this.height = parent.height + 1;
    }

    public Node(int x, int y) {
        this.x = x;
        this.y = y;
        this.parent = null;
        this.cost = 0;
        this.height = 0;
    }

    public List<Node> successors(List<char[]> grid) {
        List<Node> successors = new ArrayList<>();
        Map<List<Integer>, Integer> moves = new HashMap<>();
        // up
        if (y > 0) {
            moves.put(List.of(x, y - 1), Integer.parseInt(String.valueOf(grid.get(y - 1)[x])));
        }

        // down
        if (y < grid.size() - 1) {
            moves.put(List.of(x, y+1), Integer.parseInt(String.valueOf(grid.get(y+1)[x])));
        }

        //left
        if (x > 0) {
            moves.put(List.of(x-1, y), Integer.parseInt(String.valueOf(grid.get(y)[x-1])));
        }
        //right
        if (x < grid.get(y).length - 1) {
            moves.put(List.of(x+1, y), Integer.parseInt(String.valueOf(grid.get(y)[x+1])));
        }

        for (Map.Entry<List<Integer>, Integer> m: moves.entrySet()) {
            if (m.getValue() == this.height+1) {
                List<Integer> pos = m.getKey();
                successors.add(new Node(pos.get(0), pos.get(1), this));
            }
        }
        return successors;
    }

    public int getHeight(){
        return this.height;
    }

    public List<Integer> getPos(){
        return List.of(this.x, this.y);
    }

    public int getCost(){
        return this.cost;
    }





}
