/**
 * Coursera - Algorithms Part I
 * Week 1 - Interview Questions Part.1 - Union Find
 *
 * Question 1: Social network connectivity
 *
 * Given a social network containing N members and a log file containing M
 * timestamps at which times pairs of members formed friendships, design an
 * algorithm to determine the earliest time at which all members are connected
 * (i.e., every member is a friend of a friend of a friend ... of a friend).
 * Assume that the log file is sorted by timestamp and that friendship is an
 * equivalence relation. The running time of your algorithm should be MlogN or
 * better and use extra space proportional to N.
 */

/**
 * Solution:
 *
 * Use a union-find data structure with each site representing a social network
 * member. Add unions between sites in time order of friendships being formed.
 * After each union is added, check the number of connected components
 * within the union-find data structure. If only one, all members are connected.
 *
 * Must keep track of number of unique components. Decreases when a union occurs
 * between different components.
 */

import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Determine when all members of a social network are connected.
 */


public class W1P1N1SocialNetworkConnectivity {
    private WeightedQuickUnionUF uf;
    private int numComponents;

    public W1P1N1SocialNetworkConnectivity(int N) {
        numComponents = N;
        uf = new WeightedQuickUnionUF(N);
    }

    public void addFriendship(int p, int q) {
        if(!(uf.find(p) == uf.find(q))) {
            numComponents--;
            uf.union(p,q);
        }
    }

    public boolean fullyConnected() {
        return this.numComponents == 1;
    }

    public static void main(String args[]) {
        int N = StdIn.readInt();
        W1P1N1SocialNetworkConnectivity s = new W1P1N1SocialNetworkConnectivity(N);
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            System.out.println("Start connect two members...");
            s.addFriendship(p,q);
            if (s.fullyConnected()) {
                System.out.println("All members are connected.");
                break;
            }
        }
    }
}
