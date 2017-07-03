import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

import java.util.TreeSet;

public class PointSET
{
    private TreeSet<Point2D> treeSet;

    public PointSET()
    {
        treeSet = new TreeSet<>();
    }

    public boolean isEmpty()
    {
        return treeSet.isEmpty();
    }

    public int size()
    {
        return treeSet.size();
    }

    public void insert(Point2D p)
    {
        if (p == null) throw new IllegalArgumentException();
        treeSet.add(p);
    }

    public boolean contains(Point2D p)
    {
        if (p == null) throw new IllegalArgumentException();
        return treeSet.contains(p);
    }

    public void draw()
    {
        for (Point2D p : treeSet)
        {
            p.draw();
        }
    }

    public Iterable<Point2D> range(RectHV rect)
    {
        if (rect == null) throw new IllegalArgumentException();
        TreeSet<Point2D> range = new TreeSet<>();
        for (Point2D p : treeSet)
        {
            if (rect.contains(p))
            {
                range.add(p);
            }
        }

        return range;
    }

    public Point2D nearest(Point2D p)
    {
        if (p == null) throw new IllegalArgumentException();
        boolean found = false;
        for (Point2D point : treeSet)
        {
            if (found)
            {
                return point;
            }
            if (point.equals(p))
            {
                found = true;
            }
        }

        return null;
    }

    public static void main(String[] args)
    {

    }
}
