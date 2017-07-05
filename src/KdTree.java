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

        public Point2D getP() {
            return p;
        }

        public void setP(Point2D p) {
            this.p = p;
        }

        public RectHV getRect() {
            return rect;
        }

        public void setRect(RectHV rect) {
            this.rect = rect;
        }

        public Node getLb() {
            return lb;
        }

        public void setLb(Node lb) {
            this.lb = lb;
        }

        public Node getRt() {
            return rt;
        }

        public void setRt(Node rt) {
            this.rt = rt;
        }

        public Direction getDirection() {
            return direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
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
        root = insert(root, p);
    }

    private Node insert(Node node, Point2D p)
    {
        if (node == null)
        {
            size++;
            return new Node(p, null, null, null, Node.Direction.VERTICAL);
        }
        if (node.getDirection() == Node.Direction.VERTICAL)
        {
            if (p.x() < node.p.x())        node.lb = insert(node.lb, p);
            else if (p.x() > node.p.x())   node.rt = insert(node.rt, p);
        }
        return node;
    }
}
