package W1Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    private double[] result;

    // perform independent trials on an n-by-n grid
    public PercolationStats(int size, int trials) {
        assertPositive(size);
        assertPositive(trials);
        result = new double[trials];
        runExperiments(size, trials);
    }

    private void assertPositive(int num) {
        if (num < 1) {
            throw new IllegalArgumentException();
        }
    }

    private void runExperiments(int size, int trials) {
        for (int i = 0; i < trials; i++) {
            result[i] = runExperiment(size);
        }
    }

    private int random (int size) {
        return StdRandom.uniform(1, size + 1);
    }

    private double runExperiment(int size) {
        Percolation p = new Percolation(size);
        do {
            int row = random(size);
            int col = random(size);
            if (!p.isOpen(row, col)) {
                p.open(row, col);
            }
        }while (!p.percolates());

        return (double) p.numberOfOpenSites() / ((double) size * size);
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(result);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(result);
    }

    private double confidence()  { return (1.96 * stddev() / Math.sqrt(result.length));}

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean() - confidence();
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean() + confidence();
    }

    // test client (see below)
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int trials  = Integer.parseInt(args[1]);
        PercolationStats ps = new PercolationStats(n, trials);
        System.out.printf("mean                    = %.16f%n", ps.mean());
        System.out.printf("stddev                  = %.16f%n", ps.stddev());
        System.out.printf("95%% confidence interval = [%.10f, %.10f]", ps.confidenceLo(),ps.confidenceHi());
        // System.out.println("95% confidence interval = [" + ps.confidenceLo() + ", " + ps.confidenceHi() +"]");
    }
}