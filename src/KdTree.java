import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdDraw;

public class KdTree
{
    private Node root;
    private int size;

    private static class Node
    {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        Direction direction;

        public Node(Point2D p, RectHV rect, Node lb, Node rt, Direction direction)
        {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.direction = direction;
        }

        public enum Direction
        {
            VERTICAL, HORIZAONTAL;
        }
    }

    public boolean isEmpty()
    {
        return (this.root == null);
    }

    public int size()
    {
        return this.size;
    }

    public void insert(Point2D p)
    {
        root = insert(root, p, Node.Direction.VERTICAL, 0.0, 0.0, 1.0, 1.0);
    }

    private Node insert(Node node, Point2D p, Node.Direction direction,
            double xmin, double ymin, double xmax, double ymax)
    {
        if (node == null)
        {
            if (contains(p))
            {
                return null;
            }
            size++;
            RectHV rectHV = new RectHV(xmin,ymin, xmax, ymax);
            return new Node(p, rectHV, null, null, direction);
        }

        if (node.direction == Node.Direction.VERTICAL)
        {
            if (p.x() < node.p.x())
                node.lb = insert(
                        node.lb,
                        p,
                        Node.Direction.HORIZAONTAL,
                        node.rect.xmin(),
                        node.rect.ymin(),
                        node.p.x(), // xmax
                        node.rect.ymax()
                );
            else
                node.rt = insert(
                        node.rt,
                        p,
                        Node.Direction.HORIZAONTAL,
                        node.p.x(), // xmin
                        node.rect.ymin(),
                        node.rect.xmax(),
                        node.rect.ymax()
                );
        }
        else // Node.Direction.HORIZONTAL
        {
            if (p.y() < node.p.y())
                node.lb = insert(
                        node.lb,
                        p,
                        Node.Direction.VERTICAL,
                        node.rect.xmin(),
                        node.rect.ymin(),
                        node.rect.xmax(),
                        node.p.y() // ymax
                );
            else
                node.rt = insert(
                        node.rt,
                        p,
                        Node.Direction.VERTICAL,
                        node.rect.xmin(),
                        node.p.y(), //ymin
                        node.rect.xmax(),
                        node.rect.ymax()
                );
        }

        return node;
    }

    public boolean contains(Point2D p)
    {
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p)
    {
        if (node == null)
            return false;

        if (node.p.x() == p.x() && node.p.y() == p.y())
        {
            return true;
        }
        else if (node.direction == Node.Direction.VERTICAL)
        {
            if (p.x() < node.p.x())
                return contains(node.lb, p);
            else
                return contains(node.rt, p);
        }
        else
        {
            if (p.y() < node.p.y())
                return contains(node.lb, p);
            else
                return contains(node.rt, p);
        }
    }

    public void draw()
    {
        draw(root);
    }

    private void draw(Node node)
    {
        if (node == null)
            return;

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.01);
        node.p.draw();
        if (node.direction == Node.Direction.VERTICAL)
        {
            StdDraw.setPenColor(StdDraw.RED);
            StdDraw.setPenRadius();
            StdDraw.line(node.p.x(), node.rect.ymin(), node.p.x(), node.rect.ymax());
        }
        else // Node.Direction.HORIZONTAL
        {
            StdDraw.setPenColor(StdDraw.BLUE);
            StdDraw.setPenRadius();
            StdDraw.line(node.rect.xmin(), node.p.y(), node.rect.xmax(), node.p.y());
        }

        draw(node.lb);
        draw(node.rt);
    }

    public static void main(String[] args)
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(0.7, 0.2);
        Point2D point2D2 = new Point2D(0.5, 0.4);
        Point2D point2D3 = new Point2D(0.2, 0.3);
        Point2D point2D4 = new Point2D(0.4, 0.7);
        Point2D point2D5 = new Point2D(0.9, 0.6);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);
        kdTree.insert(point2D3);
        kdTree.insert(point2D4);
        kdTree.insert(point2D5);
    }
}
