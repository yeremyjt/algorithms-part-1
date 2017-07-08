import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;

public class KdTree
{
    private Node root;
    private int size;

    private static class Node {
        private Point2D p;
        private RectHV rect;
        private Node lb;
        private Node rt;
        Direction direction;

        public Node(Point2D p, RectHV rect,  Node lb, Node rt, Direction direction)
        {
            this.p = p;
            this.rect = rect;
            this.lb = lb;
            this.rt = rt;
            this.direction = direction;
        }

        public enum Direction {
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
        root = insert(root, p, Node.Direction.VERTICAL);
    }

    private Node insert(Node node, Point2D p, Node.Direction direction)
    {
        if (node == null)
        {
            if (contains(p))
            {
                return null;
            }
            size++;
            return new Node(p, null, null, null, direction);
        }

        if (node.direction == Node.Direction.VERTICAL)
        {
            if (p.x() < node.p.x())        node.lb = insert(node.lb, p, Node.Direction.HORIZAONTAL);
            else                           node.rt = insert(node.rt, p, Node.Direction.HORIZAONTAL);
        }
        else
        {
            if (p.y() < node.p.y())        node.lb = insert(node.lb, p, Node.Direction.VERTICAL);
            else                           node.rt = insert(node.rt, p, Node.Direction.VERTICAL);
        }

        return node;
    }

    public boolean contains(Point2D p)
    {
        return contains(root, p);
    }

    private boolean contains(Node node, Point2D p)
    {
        if (node == null) return false;

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

    public static void main(String[] args)
    {
        KdTree kdTree = new KdTree();
        Point2D point2D1 = new Point2D(1, 1);
        Point2D point2D2 = new Point2D(1, 2);

        kdTree.insert(point2D1);
        kdTree.insert(point2D2);

    }
}
