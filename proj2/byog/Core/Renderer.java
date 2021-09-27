package byog.Core;

import java.util.Random;

import byog.TileEngine.TETile;
import byog.Core.Rectangle.*;
import java.util.List;
import java.util.ArrayList;

/**
 * The renderer initial with width and height that are same with the world
 */
public class Renderer {
    private int width;
    private int height;

    private final int MAX = 10;
    /**
     * type of the floor: 0 represents nothing, 1 represents floor, 2 represents
     * wall
     */
    private int[][] type;
    private List<Point> linkPoint = new ArrayList<>();

    public Renderer(int width, int height) {
        this.width = width;
        this.height = height;
        type = new int[width][height];
    }

    /** fill the world with tile */
    public void fill(TETile[][] world, TETile tile) {
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                world[i][j] = tile;
                type[i][j] = 0;
            }
        }
    }

    /** Render the world at random */
    public void randomRender(TETile[][] world, TETile floor, TETile wall, Random random) {
        randomFloorRender(world, floor, random);
        wallRender(world, wall);
    }

    /**
     * Render the floor with random floor valid bounding box is (1, 1, width-2,
     * height-2)
     */
    private void randomFloorRender(TETile[][] world, TETile floor, Random random) {
        int rectCount = RandomUtils.uniform(random, 1, (width - 2) * (height - 2) + 1);
        for (int count = 0; count < rectCount; count++) {
            /** Generate random link point */
            Point startPoint;
            if (count == 0) {
                /** first linkPoint is random */
                int linkPointX = RandomUtils.uniform(random, 1, MAX + 1);
                int linkPointY = RandomUtils.uniform(random, 1, MAX + 1);
                startPoint = new Point(linkPointX, linkPointY);
            } else {
                int randomIndex = RandomUtils.uniform(random, linkPoint.size());
                startPoint = linkPoint.get(randomIndex);
            }

            /** Generate random */
            int maxWidth = rectMaxWidth(startPoint);
            int maxHeight = rectMaxHeight(startPoint);
            Rectangle rect = randomRect(maxWidth, maxHeight, random);
            if (rect != null) {
                floorRender(world, floor, startPoint, rect);
            }

            /** Update linkPoint, break the loop if linkPoint is null */
            updateLinkPoint(startPoint, rect);
            if (linkPoint.size() == 0) {
                break;
            }
        }

    }

    /** calculate the maxinum width that rect can generate */
    private int rectMaxWidth(Point p) {
        Point newP = new Point(p.x, p.y);
        int maxWidth = 1;
        while (type[newP.x][newP.y] != 1 && newP.x < width - 1) {
            newP.x++;
            maxWidth++;
        }
        return maxWidth;
    }

    /** calculate the maxinum width that rect can generate */
    private int rectMaxHeight(Point p) {
        Point newP = new Point(p.x, p.y);
        int maxHeight = 1;
        while (type[newP.x][newP.y] != 1 && newP.y < height - 1) {
            newP.y++;
            maxHeight++;
        }
        return maxHeight;
    }

    /** Generate random new reactangle */
    private Rectangle randomRect(int maxWidth, int maxHeight, Random random) {
        int randomRect = random.nextInt(4);
        // int randomWidth = RandomUtils.uniform(random, 1, maxWidth + 1);
        // int randomHeight = RandomUtils.uniform(random, 1, maxHeight + 1);
        int max = maxWidth < MAX ? maxWidth : MAX;
        int min = maxHeight < MAX ? maxHeight : MAX;
        int randomWidth = RandomUtils.uniform(random, 1, max + 1);
        int randomHeight = RandomUtils.uniform(random, 1, min + 1);
        if (randomRect == 0) {
            return new Hallway("horizontal", randomWidth);
        }
        if (randomRect == 1) {
            return new Hallway("vertical", randomHeight);
        }
        return new Room(randomWidth, randomHeight);
    }

    /** Generate valid random point */
    private void updateLinkPoint(Point startPoint, Rectangle rect) {
        Point p;
        /** left and right bounding */
        for (int i = 0; i < rect.height(); i++) {
            if (startPoint.x - 1 > 0) {
                p = new Point(startPoint.x - 1, startPoint.y + i);
                pushToLinkPoint(p);
            }

            if (startPoint.x + rect.width() < width - 1) {
                p = new Point(startPoint.x + rect.width(), startPoint.y + i);
                pushToLinkPoint(p);
            }
        }
        /** Top and bottom bounding */
        for (int i = 0; i < rect.width(); i++) {
            if (startPoint.y - 1 > 0) {
                p = new Point(startPoint.x + i, startPoint.y - 1);
                pushToLinkPoint(p);
            }

            if (startPoint.y + rect.height() < height - 1) {
                p = new Point(startPoint.x + i, startPoint.y + rect.height());
                pushToLinkPoint(p);
            }
        }
    }

    /** Push to linkPoint if unique, remove from list if duplicate */
    private void pushToLinkPoint(Point p) {
        if (linkPoint.contains(p)) {
            linkPoint.remove(p);
        } else {
            linkPoint.add(p);
        }
    }

    /** get the orientation points, first is top-left,sort by clockwise */
    private Point[] Orientation8Points(Point point) {
        Point[] points = new Point[8];
        points[0] = new Point(point.x - 1, point.y + 1);
        points[1] = new Point(point.x, point.y + 1);
        points[2] = new Point(point.x + 1, point.y + 1);
        points[3] = new Point(point.x + 1, point.y);
        points[4] = new Point(point.x + 1, point.y - 1);
        points[5] = new Point(point.x, point.y - 1);
        points[6] = new Point(point.x - 1, point.y - 1);
        points[7] = new Point(point.x - 1, point.y);
        return points;
    }

    /** Render the wall that surrounds the floor, assume the floor is valid */
    private void wallRender(TETile[][] world, TETile wall) {
        for (int i = 1; i < width - 1; i++) {
            for (int j = 1; j < height - 1; j++) {
                if (type[i][j] == 1) {
                    Point currentPoint = new Point(i, j);
                    Point[] orientation = Orientation8Points(currentPoint);
                    for (int k = 0; k < orientation.length; k++) {
                        Point p = orientation[k];
                        if (type[p.x][p.y] == 0) {
                            world[p.x][p.y] = wall;
                            type[p.x][p.y] = 2;
                        }
                    }
                }
            }
        }
    }

    /** Render a floor start with startpoint */
    private void floorRender(TETile[][] world, TETile floor, Point startPoint, Rectangle rect) {
        for (int i = startPoint.x; i < rect.width() + startPoint.x; i++) {
            for (int j = startPoint.y; j < rect.height() + startPoint.y; j++) {
                world[i][j] = floor;
                type[i][j] = 1;
            }
        }
    }

}
