package lab11.graphs;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 *  @author Josh Hug
 */
public class MazeAStarPath extends MazeExplorer {
    private int s;
    private int t;
    private boolean targetFound = false;
    private Maze maze;

    // private class Vertex {
    //     public int id;
    //     public int distance;
    //     public Vertex(int id, int distance) {
    //         this.id = id;
    //         this.distance = distance;
    //     }
    // }

    // private class VertexComparator implements Comparator<Vertex> {
    //     public int compare(Vertex v1, Vertex v2) {
    //         return v1.distance - v2.distance;
    //     }
    // }

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
        return -1;
    }

    /** Finds vertex estimated to be closest to target. */
    private int findMinimumUnmarked() {
        return -1;
        /* You do not have to use this method. */
    }

    /** Performs an A star search from vertex s. */
    private void astar(int s) {
        // TODO
        // PriorityQueue<Vertex> pq = new PriorityQueue<>(new VertexComparator());
        // pq.offer(new Vertex(s, 0));
        
        for(int w : maze.adj(s)) {

        }

    }

    @Override
    public void solve() {
        astar(s);
    }
}

