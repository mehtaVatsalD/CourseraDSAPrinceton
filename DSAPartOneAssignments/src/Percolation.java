import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    private final WeightedQuickUnionUF uf;
    private final WeightedQuickUnionUF uf2;
    private final int n;
    private final boolean[][] openSites;
    private int noOfOpenSites;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Value of n should at-least be 1");
        }
        // n*n objects, 1 for each site in nxn grid. 2 extra objects for top and bottom virtual sites
        this.uf = new WeightedQuickUnionUF(n * n + 2);
        this.uf2 = new WeightedQuickUnionUF(n * n + 1);
        this.n = n;
        this.openSites = new boolean[n][n];
        this.noOfOpenSites = 0;
    }

    private void validate(int p) {
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not between 0 and " + (n-1));
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        row--;
        col--;
        validate(row);
        validate(col);

        if (openSites[row][col]) {
            return;
        }

        openSites[row][col] = true;
        if (row - 1 >= 0 && openSites[row - 1][col]) {
            uf.union((row - 1) * n + col, row * n + col);
            uf2.union((row - 1) * n + col, row * n + col);
        }
        else if (row == 0) {
            uf.union(col, n * n);
            uf2.union(col, n * n);
        }

        if (col - 1 >= 0 && openSites[row][col - 1]) {
            uf.union(row * n + col - 1, row * n + col);
            uf2.union(row * n + col - 1, row * n + col);
        }


        if (row + 1 < n && openSites[row + 1][col]) {
            uf.union((row + 1) * n + col, row * n + col);
            uf2.union((row + 1) * n + col, row * n + col);
        }
        else if (row == n - 1) {
            uf.union(row * n + col, n * n + 1);
        }

        if (col + 1 < n && openSites[row][col + 1]) {
            uf.union(row * n + col + 1, row * n + col);
            uf2.union(row * n + col + 1, row * n + col);
        }

        noOfOpenSites++;
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        row--;
        col--;
        validate(row);
        validate(col);
        return openSites[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        row--;
        col--;
        validate(row);
        validate(col);
        return uf2.find(n * n) == uf2.find(row * n + col);
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        return noOfOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return areSitesConnected(n * n, n * n + 1);
    }

    private boolean areSitesConnected(int site1, int site2) {
        return uf.find(site1) == uf.find(site2);
    }

    // test client (optional)
    public static void main(String[] args) {
        int n = 10000;
        Percolation percolation = new Percolation(n);
        while (!percolation.percolates()) {
            int row = StdRandom.uniform(n);
            int col = StdRandom.uniform(n);
            percolation.open(row+1, col+1);
        }
        System.out.println(percolation.noOfOpenSites * 1.0 / (n * n));

    }

}
