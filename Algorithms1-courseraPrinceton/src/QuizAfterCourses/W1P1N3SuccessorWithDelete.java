import edu.princeton.cs.algs4.StdIn;

/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.1
 *
 * Question 3: Successor with Delete.
 *  Given a set of nn integers S = {0,1,...,n−1} and a sequence of requests of the following form:
 *  Remove x from S
 *  Find the successor of x: the smallest y in S such that y≥x.
 *  design a data type so that all operations (except construction) take logarithmic time or better in the worst case.
 *
 * Solution :
 * When we delete some items, we can connect them into a subset and record the max index of the subset.
 * The successor will be the max_index+1 if it has been removed, itself if it isn't removed.
 *
 */

public class W1P1N3SuccessorWithDelete {
    private int[] roots;
    private int[] size;
    private int[] max;
    public int rootsNumber;

    public W1P1N3SuccessorWithDelete(int N){
        rootsNumber = N;
        roots = new int[N];
        size = new int[N];
        max = new int[N];
        for (int i = 0; i<N; i++) {
            roots[i] = -1;
            size[i] = 1;
            max[i] = i;
        }
    }

    private int root(int i) {
        int node = i;
        while (roots[i] != i) i = roots[i];
        while (node != roots[node]) {
            int temp = roots[node];
            roots[node] = i;
            node = temp;
        }
        return i;
    }

    private void union(int i, int j) {
        int ri = roots[i];
        int rj = roots[j];
        int m = Math.max(max[rj],max[ri]);
        if (size[ri]<size[rj]) {
            roots[ri] = rj;
            size[rj] += size[ri];
            max[rj] = m;
        }
        else {
            roots[rj] = ri;
            size[ri] = size[rj];
            max[ri] = m;
        }
        // Once a connection is formed, the number of the roots will decrease one.
        rootsNumber--;
    }

    private int find(int i) {
        int r = root(i);
        return max[r];
    }

    public void remove(int i) {
        assertInRange(i);
        // the node has not been removed.
        if (roots[i] == -1) {
            roots[i] = i;
            if (i > 0 && roots[i-1] != -1) union(i,i-1);
            if (i < roots.length - 1 && roots[i + 1] != -1) union(i, i + 1);
        }
    }

    public int getSuccessor(int i) {
        if (roots[i] == -1) return i;
        else {
            int successor = find(i) + 1;
            if (successor < roots.length) return find(i) + 1;
            else {
                System.out.println("There is no successor!");
                return -1;
            }
        }
    }

    private void assertInRange(int i) {
        if (i < 0 || i >= roots.length) throw new IllegalArgumentException();
    }

    public static void main(String args[]) {
        int N = StdIn.readInt();
        W1P1N3SuccessorWithDelete s = new W1P1N3SuccessorWithDelete(N);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            System.out.println("Start removing members...");
            s.remove(p);
            if (s.rootsNumber == 5) {
                System.out.println("Finish removing");
                break;
            }
        }
        // 10 0 1 2 4 5 6 8 9
        System.out.println(s.getSuccessor(4)); //7
        System.out.println(s.getSuccessor(1)); //3
        System.out.println(s.getSuccessor(3)); //3
        System.out.println(s.getSuccessor(8)); //There is no successor!
    }
}
