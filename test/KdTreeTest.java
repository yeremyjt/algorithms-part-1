import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
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
}
