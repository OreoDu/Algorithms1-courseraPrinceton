/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.1
 *
 * Question 2: Union-find with specific canonical element.
 *
 * Add a method find() to the union-find data type so that find(i)
 * returns the largest element in the connected component containing i.
 * The operations, union(), connected(), and find() should all take logarithmic time or better.
 * For example, if one of the connected components is {1,2,6,9},
 * then the find() method should return 9 for each of the four elements in the connected components.
 *
 * Solution :
 * We can use a array to store the max index (max[i]) of which root is i.
 *
 */

import edu.princeton.cs.algs4.StdIn;

public class W1P1N2FindMax {
    private int[] roots;
    private int[] size;
    private int[] max;
    public int rootsNumber;

    public W1P1N2FindMax(int N){
        rootsNumber = N;
        roots = new int[N];
        size = new int[N];
        max = new int[N];
        for (int i = 0; i<N; i++) {
            roots[i] = i;
            size[i] = 1;
            max[i] = i;
        }
    }

    public boolean connected(int i, int j){
        return root(i) == root(j);
    }

    public int root(int i){
        int node = i;
        while(roots[i]!=i) i = roots[i];
        while(node!= roots[node]) {
            int temp = roots[node];
            roots[node] = i;
            node = temp;
        }
        return i;
    }

    public void union(int i, int j) {
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

    public int find(int i) {
        int r = root(i);
        return max[r];
    }

    public static void main(String args[]) {
        int N = StdIn.readInt();
        W1P1N2FindMax s = new W1P1N2FindMax(N);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            System.out.println("Start connect two members...");
            s.union(p,q);
            if (s.rootsNumber == 2) {
                System.out.println("All members are connected into two parts.");
                break;
            }
        }
        System.out.printf("Find the max in %d -th the group: %d", 0, s.find(0));
    }
}