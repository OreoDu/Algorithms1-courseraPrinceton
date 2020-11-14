import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] openSites;
    private WeightedQuickUnionUF uf, backwash;
    private final int SIZE, TOP, BOTTOM;
    private int openSpaces = 0;

    private final int[][] index = {{0, 0, 1, -1}, {1, -1, 0, 0}};

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int size) {
        SIZE = size;
        openSites = new boolean[SIZE * SIZE];
        uf = new WeightedQuickUnionUF(SIZE * SIZE + 2);
        backwash = new WeightedQuickUnionUF(SIZE * SIZE + 2);

        TOP = SIZE * SIZE;
        BOTTOM = SIZE * SIZE + 1;
    }

    public int numberOfOpenSites() {
        return openSpaces;
    }

    public void open(int row, int col) {
        assertInRange(row, col);
        if (isOpen(row, col)) return;
        openSite(row, col);
        connectToTopNode(row, col);
        connectToBottomNode(row, col);
        connectToAdjacent(row, col);
    }

    private void assertInRange(int row, int col) {
        if (row < 1 || row > SIZE  || col < 1 || col > SIZE) {
            throw new IllegalArgumentException();
        }
    }

    private void openSite(int row, int col) {
        openSites[toIndex(row, col)] = true;
        openSpaces++;
    }

    private int toIndex(int row, int col) {
        return (row - 1) * SIZE + (col - 1);
    }

    // opens the site (row, col) if it is not open already
    private void connectToAdjacent(int row, int col) {
        for (int i = 0; i < index[0].length; i++) {
            int tempx = row + index[0][i];
            int tempy = col + index[1][i];
            if (tempx >= 1 && tempx <= SIZE && tempy >= 1 && tempy <= SIZE && isOpen(tempx, tempy)) {
                union(toIndex(tempx, tempy), toIndex(row, col));
            }
        }
    }

    private void connectToTopNode(int row, int col) {
        if (row == 1) {
            union(TOP, toIndex(row, col));
        }
    }

    private void connectToBottomNode(int row, int col) {
        if (row == SIZE) {
            backwash.union(BOTTOM, toIndex(row, col));
        }
    }

    private void union(int row, int col) {
        uf.union(row, col);
        backwash.union(row, col);
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        assertInRange(row, col);
        return openSites[toIndex(row, col)];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        assertInRange(row, col);
        return isOpen(row, col) && uf.find(toIndex(row, col)) == uf.find(TOP);
    }

    // does the system percolate?
    public boolean percolates() {
        return backwash.find(BOTTOM) == backwash.find(TOP);
    }
}