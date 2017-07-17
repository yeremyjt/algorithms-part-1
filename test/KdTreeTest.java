import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class KdTreeTest
{
    @Test
    public void testInsert()
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(1, 2);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);

        assertTrue(kdTree.contains(point2D1));
        assertTrue(kdTree.contains(point2D2));
    }

    @Test
    public void testDuplicatePoint()
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(1, 1);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);

        assertTrue(kdTree.contains(point2D1));
        assertTrue(kdTree.size() == 1);
    }

    @Test
    public void testRangeSearch_AllPointsInside()
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.1);
        Point2D point2D2 = new Point2D(0.1, 0.2);
        Point2D point2D3 = new Point2D(0.1, 0.3);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        RectHV rectHV = new RectHV(0, 0, 1, 1);
        Iterable<Point2D>  pointsInRange = kdTree.range(rectHV);
        List<Point2D> pointsList = new ArrayList<>();
        pointsInRange.forEach(pointsList::add);
        assertTrue(pointsList.size() == 3);
    }

    @Test
    public void testRangeSearch_OnePointOutside()
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.1);
        Point2D point2D2 = new Point2D(0.1, 0.2);
        Point2D point2D3 = new Point2D(0.1, 0.3);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        RectHV rectHV = new RectHV(0, 0, 1, 0.2);
        Iterable<Point2D>  pointsInRange = kdTree.range(rectHV);
        List<Point2D> pointsList = new ArrayList<>();
        pointsInRange.forEach(pointsList::add);
        assertTrue(pointsList.size() == 2);
    }

    @Test
    public void testNearestNeighbor()
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.1, 0.9);
        Point2D point2D2 = new Point2D(0.5, 0.5);
        Point2D point2D3 = new Point2D(0.9, 0.1);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);

        Point2D point1 = kdTree.nearest(new Point2D(0.1, 0.8));
        assertTrue(point1.equals(point2D1));
        Point2D point2 = kdTree.nearest(new Point2D(0.5, 0.6));
        assertTrue(point2.equals(point2D2));
        Point2D point3 = kdTree.nearest(new Point2D(0.8, 0.1));
        assertTrue(point3.equals(point2D3));
    }

    @Test
    public void testNearestNeighbor_2()
    {
        KdTree kdTree = new KdTree();
        Point2D brutePoint = new Point2D(0.259123, 0.938153);
        Point2D kdTreePoint = new Point2D(0.740877, 0.938153);

        kdTree.insert(brutePoint);
        kdTree.insert(kdTreePoint);

        Point2D nearestPoint = kdTree.nearest(new Point2D(0.279296875, 0.9140625));

        assertTrue(nearestPoint.x() == brutePoint.x() && nearestPoint.y() == brutePoint.y());
    }
}
