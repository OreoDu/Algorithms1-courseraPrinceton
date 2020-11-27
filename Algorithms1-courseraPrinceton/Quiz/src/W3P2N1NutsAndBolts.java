/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.1
 *
 * Question 3: Nuts and bolts
 *
 *  A disorganized carpenter has a mixed pile of n nuts and n bolts.
 *  The goal is to find the corresponding pairs of nuts and bolts.
 *  Each nut fits exactly one bolt and each bolt fits exactly one nut.
 *  By fitting a nut and a bolt together, the carpenter can see which one is bigger
 *  (but the carpenter cannot compare two nuts or two bolts directly).
 *  Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).
 *
 * - Solution:
 *   http://web.cs.ucla.edu/~rafail/PUBLIC/17.pdf
 */
public class W3P2N1NutsAndBolts {

    public static class Tool {
        private int size;
        public static final String name = "tools";

        public Tool(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    public static class Nut {
        private int size;
        public static final String name = "nut";

        public Nut(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    public static class Bolt {
        private int size;
        public static final String name = "bolt";

        public Bolt(int size) {
            this.size = size;
        }

        public int size() {
            return size;
        }
    }

    public static void main(String[] args) {

    }
}
