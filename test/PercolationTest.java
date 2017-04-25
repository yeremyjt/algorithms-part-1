import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PercolationTest
{
    @Test
    public void isOpen() throws Exception
    {
        Percolation percolation = new Percolation(4);
        assertTrue(!percolation.isOpen(1, 3));
        percolation.open(1,3);
        assertTrue(percolation.isOpen(1, 3));
    }

    @Test
    public void isFull() throws Exception
    {
        Percolation percolation = new Percolation(4);
        assertTrue(!percolation.isFull(1, 3));
        percolation.open(1, 3);
        assertTrue(percolation.isFull(1, 3));
        percolation.open(2, 4);
        assertTrue(!percolation.isFull(2, 4));
        percolation.open(3, 4);
        assertTrue(!percolation.isFull(3, 4));
        percolation.open(4, 4);
        assertTrue(!percolation.isFull(4, 4));
        percolation.open(1, 4);
        assertTrue(percolation.isFull(1, 4));
        assertTrue(percolation.isFull(2, 4));
        assertTrue(percolation.isFull(3, 4));
        assertTrue(percolation.isFull(4, 4));

    }

    @Test
    public void percolates() throws Exception
    {
        Percolation percolation = new Percolation(4);
        assertTrue(!percolation.percolates());
        percolation.open(1, 3);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        assertTrue(!percolation.percolates());
        percolation.open(1, 4);
        assertTrue(percolation.percolates());
    }

    @Test
    public void numberOfOpenSites() throws Exception
    {
        Percolation percolation = new Percolation(4);
        percolation.open(1, 3);
        percolation.open(2, 4);
        percolation.open(3, 4);
        percolation.open(4, 4);
        percolation.open(1, 4);
        assertTrue(percolation.numberOfOpenSites() == 5);
    }

}