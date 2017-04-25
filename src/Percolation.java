import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private static final int TOP = 0;
    private final int bottom;
    private Site[][] grid;
    private int openSites;
    private int nBase;
    private WeightedQuickUnionUF uf;

    public Percolation(int n) {
        if (n <= 0)
            throw new java.lang.IllegalArgumentException();
        nBase = n;
        // creating the Union-Find object. We add two virtual sites that will
        // connect to
        // top and bottom row
        uf = new WeightedQuickUnionUF(n * n + 2);
        grid = new Site[n][n];

        // Initializing all sites to be blocked and empty
        int counter = 1;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                Site site = new Site();
                site.value = counter;
                site.setBlocked(true);
                site.setFull(false);
                grid[i][j] = site;
                counter++;
            }
        }

        bottom = n * n + 1;
        openSites = 0;
    }

    public void open(int i, int j) {
        validateInput(i, j);

        i--;
        j--;

        if (grid[i][j].isBlocked())
        {
            grid[i][j].setBlocked(false);
            openSites++;

            // Connecting site with adjacent open sites.
            // The Try-Catch's will only make sure we only perform the union
            // operation on valid sites.

            // Connecting to upper site
            try {
                if (!grid[i - 1][j].isBlocked())
                {
                    if (!uf.connected(grid[i][j].getValue(), grid[i - 1][j].getValue()))
                    {
                        uf.union(grid[i][j].getValue(), grid[i - 1][j].getValue());
                    }

                    if (grid[i - 1][j].isFull())
                    {
                        grid[i][j].setFull(true);
                    }
                }

            } catch (java.lang.IndexOutOfBoundsException e) {
                // If top row, connect to virtual TOP.
                uf.union(TOP, grid[i][j].getValue());
                grid[i][j].setFull(true);

            }

            // Connecting to lower site
            try {
                if (!grid[i + 1][j].isBlocked())
                {
                    if (!uf.connected(grid[i][j].getValue(), grid[i + 1][j].getValue()))
                    {
                        uf.union(grid[i][j].getValue(), grid[i + 1][j].getValue());
                    }

                    if (grid[i + 1][j].isFull())
                    {
                        grid[i][j].setFull(true);
                    }
                }

            } catch (java.lang.IndexOutOfBoundsException e) {
                // If bottom row, connect to virtual bottom.
                uf.union(grid[i][j].getValue(), bottom);
            }

            // Connecting to left site
            try {
                if (!grid[i][j - 1].isBlocked())
                {
                    if (!uf.connected(grid[i][j].getValue(), grid[i][j - 1].getValue()))
                    {
                        uf.union(grid[i][j].getValue(), grid[i][j - 1].getValue());
                    }

                    if (grid[i][j - 1].isFull())
                    {
                        grid[i][j].setFull(true);
                    }
                }
            } catch (java.lang.IndexOutOfBoundsException e) {
                // The site does not exist, no need to connect. Do nothing
            }

            // Connecting to right site
            try {
                if (!grid[i][j + 1].isBlocked())
                {
                    if (!uf.connected(grid[i][j].getValue(), grid[i][j + 1].getValue()))
                    {
                        uf.union(grid[i][j].getValue(), grid[i][j + 1].getValue());
                    }

                    if (grid[i][j + 1].isFull())
                    {
                        grid[i][j].setFull(true);
                    }
                }
            } catch (java.lang.IndexOutOfBoundsException e) {
                // The site does not exist, no need to connect. Do nothing
            }

            if (grid[i][j].isFull())
            {
                checkAdjacentFull(i, j);
            }
        }
    }

    private void checkAdjacentFull(int i, int j)
    {
        boolean change = false;
        // check site above
        try
        {
            if (!grid[i - 1][j].isBlocked())
            {
                if (!grid[i - 1][j].isFull())
                {
                    change = true;
                    grid[i - 1][j].setFull(true);
                }
                if (change)
                {
                    checkAdjacentFull(i - 1, j);
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            // do nothing
        }

        // check site below
        try
        {
            if (!grid[i + 1][j].isBlocked())
            {
                if (!grid[i + 1][j].isFull())
                {
                    change = true;
                    grid[i + 1][j].setFull(true);
                }
                if (change)
                {
                    checkAdjacentFull(i + 1, j);
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            // do nothing
        }

        // check site to the left
        try
        {
            if (!grid[i][j - 1].isBlocked())
            {
                if (!grid[i][j- 1].isFull())
                {
                    change = true;
                    grid[i][j - 1].setFull(true);
                }
                if (change)
                {
                    checkAdjacentFull(i, j - 1);
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            // do nothing
        }

        // check site to the right
        try
        {
            if (!grid[i][j + 1].isBlocked())
            {
                if (!grid[i][j + 1].isFull())
                {
                    change = true;
                    grid[i][j + 1].setFull(true);
                }
                if (change)
                {
                    checkAdjacentFull(i, j + 1);
                }
            }
        }
        catch (IndexOutOfBoundsException e)
        {
            // do nothing
        }

        return;
    }

    public boolean isOpen(int i, int j) {
        validateInput(i, j);
        i--;
        j--;
        return (!grid[i][j].isBlocked());
    }

    public boolean isFull(int i, int j) {
        validateInput(i, j);
        i--;
        j--;
        return grid[i][j].isFull();
    }

    public boolean percolates() {
        return uf.connected(TOP, bottom);
    }

    public int numberOfOpenSites() {
        return this.openSites;
    }

    private void validateInput(int i, int j) {
        if ((i < 1 || j < 1) || (i > nBase || j > nBase))
            throw new IndexOutOfBoundsException();
    }

    private class Site {
        private int value;
        private boolean blocked;
        private boolean full;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public boolean isBlocked() {
            return blocked;
        }

        public void setBlocked(boolean blocked) {
            this.blocked = blocked;
        }

        public boolean isFull() {
            return full;
        }

        public void setFull(boolean full) {
            this.full = full;
        }
    }
}