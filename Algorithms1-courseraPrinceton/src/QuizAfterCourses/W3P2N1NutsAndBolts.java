import java.util.Iterator;
import java.util.Map;

/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.2
 *
 * Question 1: Nuts and bolts
 *
 *  A disorganized carpenter has a mixed pile of n nuts and n bolts.
 *  The goal is to find the corresponding pairs of nuts and bolts.
 *  Each nut fits exactly one bolt and each bolt fits exactly one nut.
 *  By fitting a nut and a bolt together, the carpenter can see which one is bigger
 *  (but the carpenter cannot compare two nuts or two bolts directly).
 *  Design an algorithm for the problem that uses at most proportional to nlogn compares (probabilistically).
 *
 * - Solution:
 *   Because we can not compare the elements among the Nut[] or Bolt[]
 *   we can only choose the elements in the Nut[] as pivot to partition the Bolt[]
 *   and use the new pivot found in the Bolt[] of the same size with the older pivot to partition the Nut[]
 *   http://web.cs.ucla.edu/~rafail/PUBLIC/17.pdf
 */

public class W3P2N1NutsAndBolts {

    public static class Tool implements Comparable<Tool> {
        private int size;
        private String name = "tools";
        public Tool(int size, String name) {
            this.size = size;
            this.name = name;
        }

        public int size() {
            return size;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        @Override
        public int compareTo(Tool anotherTool) {
            if (size > anotherTool.size()) return 1;
            else if (size < anotherTool.size()) return -1;
            else return 0;
        }
    }

    public static class Nut extends Tool {
        public Nut(int size,String name) {
            super(size, name);
        }
    }

    public static class Bolt extends Tool {
        public Bolt(int size, String name) {
            super(size, name);
        }
    }

    public static class NutsAndBolts implements Iterable<String> {

        private Nut[] nuts;
        private Bolt[] bolts;
        private int num;

        public NutsAndBolts(Nut[] nuts, Bolt[] bolts, int num) throws IllegalAccessException {
            if (nuts == null) throw new IllegalAccessException("Nuts can not be empty!");
            if (bolts == null) throw new IllegalAccessException("Bolts can not be empty!");
            if (bolts.length != num) throw new IllegalAccessException("The bolts' number is not correct!");
            if (nuts.length != num) throw new IllegalAccessException("The nuts' number is not correct!");

            this.nuts = nuts;
            this.bolts = bolts;
            this.num = num;

            matchNutsAndBolts(this.nuts, this.bolts, 0, num - 1);
        }

        public void matchNutsAndBolts(Nut[] nuts, Bolt[] bolts, int lo, int hi) {
            if (hi <= lo) return;
            int index = partition(bolts, lo, hi, nuts[hi]);
            partition(nuts, lo, hi, bolts[index]);

            matchNutsAndBolts(nuts, bolts, lo, index - 1);
            matchNutsAndBolts(nuts, bolts, index + 1 , hi);
        }

        private int partition(Tool[] tools, int lo, int hi, Tool pivot) {
            int right = num, left = num, i = lo - 1, j = hi + 1;
            while (true) {
                while (less(tools[++i], pivot)) {
                    if (i == hi) break;
                    if (tools[i].size() == pivot.size()) left = i;
                }
                while (less(pivot, tools[--j])) {
                    if (j == lo) break;
                    if (tools[j].size() == pivot.size()) right = j;
                }

                if (i >= j) break;
                exch(tools, i, j);
            }

            if (left != num) {
                // both i and j have went through the pivot.
                if (left == right) return left;
                exch(tools, left, j);
                return j;
            }
            else {
                exch(tools, right, i);
                return i;
            }
        }

        private boolean less(Tool tool1, Tool tool2) {
            return tool1.compareTo(tool2) == -1 || tool1.compareTo(tool2) == 0;
        }

        private void exch(Tool[] tools, int i, int j) {
            Tool tmp = tools[i];
            tools[i] = tools[j];
            tools[j] = tmp;
        }

        @Override
        public Iterator<String> iterator() {
            return new toolIterator();
        }

        public class toolIterator implements Iterator<String>{
            private int n = 0;

            @Override
            public boolean hasNext() {
                return n < num;
            }

            @Override
            public String next() {
                if (!hasNext()) throw new java.util.NoSuchElementException();
                return "Name: " + bolts[n].getName() + " Size :" + String.valueOf(bolts[n].size()) + " -> " + "Name: " + nuts[n].getName() + " Size: " + String.valueOf(nuts[n++].size());
            }
        }


    }

    public static void main(String[] args) throws IllegalAccessException {
        Nut[] nuts = {new Nut(4, "NNo.1"), new Nut(7, "NNo.2"), new Nut(1, "NNo.3"),
                      new Nut(5, "NNo.4"), new Nut(2, "NNo.5"), new Nut(3, "NNo.6")};
        Bolt[] bolts = {new Bolt(7,"BNo.1"), new Bolt(4, "BNo.2"), new Bolt(3, "BNo.3"),
                        new Bolt(1, "BNo.4"), new Bolt(2, "BNo.5"),new Bolt(5, "BNo.6")};

        NutsAndBolts NB = new NutsAndBolts(nuts, bolts, 6);

        for (String s: NB) {
            System.out.println(s);
        }
    }
}
