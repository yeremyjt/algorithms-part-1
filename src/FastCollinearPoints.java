
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FastCollinearPoints {

    private LineSegment[] segments;

    public FastCollinearPoints(Point[] points) {

        if (points == null) {
            throw new NullPointerException();
        }

        for (Point p : points) {
            if (p == null) {
                throw new NullPointerException();
            }
        }

        Point[] pointsCopy = points.clone();
        Arrays.sort(pointsCopy);
        for (int i = 0; i < pointsCopy.length - 1; i++) {
            if (pointsCopy[i].compareTo(pointsCopy[i + 1]) == 0) {
                throw new IllegalArgumentException();
            }
        }

        List<LineSegment> segmentsLst = new ArrayList<>();
        List<Double> slopes = new ArrayList<>();
        List<Point> startPoints = new ArrayList<>();
        Point[] pointsSort = pointsCopy.clone();

        for (int i = 0; i < pointsCopy.length - 3; i++) {
            Arrays.sort(pointsSort, pointsCopy[i].slopeOrder());
            List<Point> line = null;
            int n = 0;
            int count = 0;

            while (n < pointsSort.length - 2) {
                double slope = pointsCopy[i].slopeTo(pointsSort[n]);

                if (count == 0) {
                    line = new ArrayList<Point>();
                    line.add(pointsSort[n]);
                    count++;
                }

                while (n < pointsSort.length - 1
                        && slope == pointsCopy[i].slopeTo(pointsSort[n + 1])) {
                    line.add(pointsSort[++n]);
                    count++;
                }

                if (count >= 3) {

                    line.add(pointsCopy[i]);
                    Point[] lineArray = new Point[line.size()];
                    int j = 0;
                    for (Point p : line) {
                        lineArray[j++] = p;
                    }
                    Arrays.sort(lineArray);

                    boolean existFlag = false;
                    for (int k = 0; k < startPoints.size(); k++) {
                        if (startPoints.get(k).compareTo(lineArray[0]) == 0
                                && slopes.get(k) == slope) {
                            existFlag = true;
                            break;
                        }
                    }

                    if (!existFlag) {
                        segmentsLst.add(new LineSegment(lineArray[0], lineArray[lineArray.length - 1]));
                        slopes.add(slope);
                        startPoints.add(lineArray[0]);
                    }

                }

                count = 0;
                n++;
            }
        }

        segments = new LineSegment[segmentsLst.size()];
        int i = 0;
        for (LineSegment ls : segmentsLst) {
            segments[i++] = ls;
        }

    }

    public int numberOfSegments() {
        return segments.length;
    }

    public LineSegment[] segments() {
        return segments;
    }

    public static void main(String[] args) {

        // read the n points from a file
        In in = new In(args[0]);
        int n = in.readInt();
        Point[] points = new Point[n];
        for (int i = 0; i < n; i++) {
            int x = in.readInt();
            int y = in.readInt();
            points[i] = new Point(x, y);
        }

        // draw the points
        StdDraw.enableDoubleBuffering();
        StdDraw.setXscale(0, 32768);
        StdDraw.setYscale(0, 32768);
        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        // print and draw the line segments
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }
}