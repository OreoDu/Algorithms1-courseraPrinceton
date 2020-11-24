import java.util.Iterator;
import java.util.NoSuchElementException;

import edu.princeton.cs.algs4.StdRandom;

public class RandomizedQueue<Item> implements Iterable<Item> {
    private static final int REFACTOR = 2;
    private int size;
    private Item[] items;

    // construct an empty randomized queue
    public RandomizedQueue() {
        items = (Item[]) new Object[5];
    }

    // is the randomized queue empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the randomized queue
    public int size() {
        return size;
    }

    // add the item
    public void enqueue(Item item) {
        assertNullItem(item);
        if (size == items.length) resize(size * REFACTOR);
        if (size == 0) items[size++] = item;
        else {
            int randomIndex = StdRandom.uniform(size);
            Item tmp = items[randomIndex];
            items[randomIndex] = item;
            items[size++] = tmp;
        }
    }

    // remove and return a random item
    public Item dequeue() {
        assertEmptyDeque();
        if (size <= items.length / 4) resize(items.length / 2);
        int randomIndex = StdRandom.uniform(size);
        Item item = items[randomIndex];
        items[randomIndex] = items[--size];
        items[size] = null;
        return item;
    }

    // return a random item (but do not remove it)
    public Item sample() {
        assertEmptyDeque();
        return items[StdRandom.uniform(size)];
    }

    // return an independent iterator over items in random order
    @Override
    public Iterator<Item> iterator() {
        return new RanDequeIterator();
    }

    private class RanDequeIterator implements Iterator<Item> {
        private int current;
        private final int[] randomIndices;

        public RanDequeIterator() {
            current = 0;
            randomIndices = new int[size];
            for (int i = 0; i < size; i++) {
                randomIndices[i] = i;
            }
            StdRandom.shuffle(randomIndices);
        }

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[randomIndices[current++]];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    private void assertNullItem(Item item) {
        if (item == null) throw new IllegalArgumentException("input must be not null");
    }

    private void assertEmptyDeque() {
        if (isEmpty()) throw new NoSuchElementException("Deque underflow");
    }

    private void resize(int capacity) {
        Item[] newArray = (Item[]) new Object[capacity];
        System.arraycopy(items, 0, newArray, 0 , size);
        items = newArray;
    }

    // unit testing (required)
    public static void main(String[] args) {
        RandomizedQueue<Integer> r = new RandomizedQueue<>();
        r.enqueue(3);
        r.enqueue(4);
        r.enqueue(2);

        Iterator<Integer> ri = r.iterator();
        for (int i = 0; ri.hasNext(); i++) System.out.printf("The %d-th is %d.", i, ri.next());

        System.out.println("The size of the randomized dequeue is : " + r.size());

        r.dequeue();
        r.dequeue();
        r.dequeue();

        // r.dequeue();
        r.enqueue(null);
    }
}