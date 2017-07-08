import edu.princeton.cs.algs4.Point2D;
import org.junit.Test;
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
}
