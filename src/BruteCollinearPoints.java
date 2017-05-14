import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BruteCollinearPoints {

    private LineSegment[] segments;

    public BruteCollinearPoints(Point[] points) {

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

        List<LineSegment> segmentsList = new ArrayList<>();

        for (int i = 0; i < pointsCopy.length - 3; i++) {
            for (int j = i + 1; j < pointsCopy.length - 2; j++) {
                for (int k = j + 1; k < pointsCopy.length - 1; k++) {
                    for (int l = k + 1; l < pointsCopy.length; l++) {
                        double slopeJ = pointsCopy[i].slopeTo(pointsCopy[j]);
                        double slopeK = pointsCopy[i].slopeTo(pointsCopy[k]);
                        double slopeL = pointsCopy[i].slopeTo(pointsCopy[l]);
                        if (slopeJ == slopeK && slopeK == slopeL) {
                            LineSegment ls = new LineSegment(pointsCopy[i], pointsCopy[l]);
                            segmentsList.add(ls);
                        }
                    }
                }
            }
        }

        segments = new LineSegment[segmentsList.size()];
        int i = 0;
        for (LineSegment seg : segmentsList) {
            segments[i] = seg;
            i++;
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
        BruteCollinearPoints collinear = new BruteCollinearPoints(points);
        for (LineSegment segment : collinear.segments()) {
            StdOut.println(segment);
            segment.draw();
        }
        StdDraw.show();
    }

}