import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // Fraction of open sites in computational experiment t
    private double[] x;
    private int times;
    private double meanValue;
    private double stddevValue;

    public PercolationStats(int n, int t) {
        if (n <= 0 || t <= 0)
            throw new java.lang.IllegalArgumentException();
        x = new double[t];
        times = t;

        int row;
        int column;

        for (int i = 0; i < t; i++) {
            Percolation p = new Percolation(n);

            boolean percolates = false;

            while (!percolates) {
                row = StdRandom.uniform(1, n+1);
                column = StdRandom.uniform(1, n+1);

                if (!p.isOpen(row, column))
                    p.open(row, column);

                percolates = p.percolates();
            }

            double openSites = p.numberOfOpenSites();
            double totalSites = n*n;
            x[i] = openSites/totalSites;

        }
        meanValue = mean();
        stddevValue = stddev();
    }

    public double mean() {
        return StdStats.mean(x);

    }

    public double stddev() {
        return StdStats.stddev(x);
    }

    public double confidenceLo() {
        return meanValue - ((1.96 * stddevValue) / Math.sqrt(times));
    }

    public double confidenceHi() {
        return meanValue + ((1.96 * stddevValue) / Math.sqrt(times));
    }

    public static void main(String[] args) {
        int n = 200;
        int t = 100;
        PercolationStats ps = new PercolationStats(n, t);
        StdOut.println("mean = " + ps.mean());
        StdOut.println("stddev = " + ps.stddev());
        StdOut.println("95% confidence interval =  " + ps.confidenceLo() + ", " + ps.confidenceHi());
    }
}