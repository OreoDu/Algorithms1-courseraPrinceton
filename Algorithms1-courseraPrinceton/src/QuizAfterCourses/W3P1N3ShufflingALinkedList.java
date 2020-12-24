import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Coursera - Algorithms Part I
 * Week 3 - Interview Questions Part.1
 *
 * Question 3: Shuffling A Linked List
 *
 * Given a singly-linked list containing n items, rearrange the items uniformly at random.
 * Your algorithm should consume a logarithmic (or constant) amount of extra memory
 * and run in time proportional to nlogn in the worst case.
 *
 * - Solution: we can disconnect the node of the list and merge them from bottom to the top,
 *  during the process of merging, we randomly choose the item from one part of the list.
 *
 */

public class W3P1N3ShufflingALinkedList {
    public static class LinkedList<T extends Comparable<T>> implements Iterable<T>{
        private int size;
        private node<T> sentinel;

        public LinkedList() {
            sentinel = new node<>(null, null);
            size = 0;
        }

        private class node<T> {
            public T item;
            public node<T> next;

            public node(T item, node<T> next) {
                this.item = item;
                this.next = next;
            }
        }

        @Override
        public Iterator<T> iterator () { return new LinkedListIterator();}

        private class LinkedListIterator implements Iterator<T> {
            private node<T> current = sentinel.next;

            @Override
            public boolean hasNext() {
                return current != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {throw new NoSuchElementException("There is no more element."); }
                T tmp = current.item;
                current = current.next;
                return tmp;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("This operation has been abandoned");
            }
        }

        public boolean isEmpty() {
            return size == 0;
        }

        // add elements in the front.
        public void add(T item) {
            node<T> n = sentinel.next == null ? new node<>(item, null) : new node<>(item, sentinel.next);
            sentinel.next = n;
            size++;
        }

        public void printList(){
            Iterator<T> iter = new LinkedListIterator();
            String s = "List: ";
            while(iter.hasNext()) {
                s += String.format("%d ", iter.next());
            }
            System.out.println(s);
        }

        private node<T> merge(node<T> left, node<T> right) {
            node<T> aux = new node<>(null, null);
            node<T> current = aux;
            while (left != null && right != null) {
                int rand = StdRandom.uniform(2);
                if (rand == 0) {
                    current.next = left;
                    current = current.next;
                    left = left.next;
                } else {
                    current.next = right;
                    current = current.next;
                    right = right.next;
                }
            }
            if (right == null) current.next = left;
            else if (left == null) current.next = right;
            return aux.next;
        }

        public node<T> shuffleList(node<T> head) {
            if(head == null || head.next == null) return head;
            node<T> fast = head;
            node<T> slow = head;

            while(fast.next != null && fast.next.next != null) {
                fast = fast.next.next;
                slow = slow.next;
            }

            node<T> left = head;
            node<T> right = slow.next;
            slow.next = null;
            left = shuffleList(left);
            right = shuffleList(right);
            return merge(left, right);
        }

        public void shuffleList() {
            sentinel.next = shuffleList(sentinel.next);
        }
    }

    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        list.printList();
        list.shuffleList();
        list.printList();
    }
}
