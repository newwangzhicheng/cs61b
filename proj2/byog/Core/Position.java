package byog.Core;

public class Position {
    public int x;
    public int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object obj) {
        Position p = (Position) obj;
        return this.x == p.x && this.y == p.y;
    }
}
