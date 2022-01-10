package hw4.puzzle;

import java.util.LinkedList;

import edu.princeton.cs.algs4.MinPQ;

public class Solver {

    private class SearchNode implements Comparable<SearchNode> {
        private WorldState worldState;
        private int moves;
        private SearchNode previous;
        private int priority;

        public SearchNode(WorldState worldState, int moves, SearchNode previous) {
            this.worldState = worldState;
            this.moves = moves;
            this.previous = previous;
            priority = moves + worldState.estimatedDistanceToGoal();
        }

        @Override
        public int compareTo(SearchNode s) {
            return this.priority - s.priority;
        }
    }

    private SearchNode result;
    private LinkedList<WorldState> solution;

    public Solver(WorldState initial) {
        MinPQ<SearchNode> pq = new MinPQ<>();
        SearchNode initialNode = new SearchNode(initial, 0, null);
        pq.insert(initialNode);

        while (!pq.isEmpty()) {
            SearchNode out = pq.delMin();
            if (out.worldState.isGoal()) {
                result = out;
                break;
            }
            for (WorldState e : out.worldState.neighbors()) {
                if (!e.equals(out.worldState)) {
                    SearchNode node = new SearchNode(e, out.moves + 1, out);
                    pq.insert(node);
                }
            }
        }

        solution = new LinkedList<>();
        SearchNode p = result;
        while (p != null) {
            solution.addFirst(p.worldState);
            p = p.previous;
        }
    }

    public int moves() {
        return result.moves;
    }

    public Iterable<WorldState> solution() {
        return solution;
    }

}
