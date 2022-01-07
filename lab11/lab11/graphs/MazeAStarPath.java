package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    private class VertexComparator implements Comparator<Integer> {
        @Override
        public int compare(Integer v1, Integer v2) {
            if ((distTo[v1] + h(v1)) - (distTo[v2] + h(v2)) <= 0) {
                return -1;
            }
            return 1;
        }
    }

    public MazeAStarPath(Maze m, int sourceX, int sourceY, int targetX, int targetY) {
        super(m);
        maze = m;
        s = maze.xyTo1D(sourceX, sourceY);
        t = maze.xyTo1D(targetX, targetY);
        distTo[s] = 0;
        edgeTo[s] = s;
    }

    /** Estimate of the distance from v to the target. */
    private int h(int v) {
        int sourceX = maze.toX(v);
        int sourceY = maze.toY(v);
        int targetX = maze.toX(t);
        int targetY = maze.toY(t);
        return Math.abs(targetX - sourceX) + Math.abs(targetY - sourceY);
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        PriorityQueue<Integer> pq = new PriorityQueue<>(new VertexComparator());
        pq.offer(s);

        while (!pq.isEmpty()) {
            int out = pq.poll();
            marked[out] = true;
            announce();
            if (out == t) {
                return;
            }
            for (int w : maze.adj(out)) {
                if (!marked[w]) {
                    distTo[w] = distTo[out] + 1;
                    edgeTo[w] = out;
                    pq.offer(w);
                }
            }
        }

    }

    @Override
    public void solve() {
        astar(s);
    }
}
