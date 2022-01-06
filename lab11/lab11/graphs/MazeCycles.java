package lab11.graphs;

import java.util.LinkedList;

/**
 * @author Josh Hug
 */
public class MazeCycles extends MazeExplorer {
    /*
     * Inherits public fields:
     * public int[] distTo;
     * public int[] edgeTo;
     * public boolean[] marked;
     */

    public MazeCycles(Maze m) {
        super(m);
    }

    @Override
    public void solve() {
        // TODO: Your code here!
        int s = 0;
        int parent = s;
        int[] copyEdgeTo = new int[maze.V()];
        System.arraycopy(edgeTo, 0, copyEdgeTo, 0, maze.V());
        LinkedList<Integer> stack = new LinkedList<>();
        stack.addLast(s);
        while (!stack.isEmpty()) {
            int out = stack.removeLast();
            marked[out] = true;
            distTo[out] = out;
            announce();
            for (int w : maze.adj(out)) {
                if (marked[w] && w != copyEdgeTo[out]) {
                    int to = w;
                    int from = out;
                    do {
                        edgeTo[to] = from;
                        announce();
                        to = from;
                        from = copyEdgeTo[from];
                    } while (to != w);
                    return;
                }
                if (!marked[w]) {
                    copyEdgeTo[w] = out;
                    stack.addLast(w);
                }
            }
            parent = out;
        }
    }

    // Helper methods go here
}
