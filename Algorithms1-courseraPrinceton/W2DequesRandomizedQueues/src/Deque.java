import java.util.Iterator;

/**
 * Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization of a stack and a queue
 * that supports adding and removing items from either the front or the back of the data structure.
 */

public class Deque<Item> implements Iterable<Item> {
    private int size;
    private final Node<Item> sentinel;

    private class Node<Item> {
        public Item item;
        public Node<Item> next;
        public Node<Item> prev;

        public Node(Node<Item> prev, Item item, Node<Item> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }
    }

    // construct an empty deque
    public Deque() {
        sentinel = new Node<>(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    // is the deque empty?
    public boolean isEmpty() {
        return size() == 0;
    }

    // return the number of items on the deque
    public int size() {
        return size;
    }

    // add the item to the front
    public void addFirst(Item item) {
        assertNullItem(item);
        Node<Item> n = new Node<>(sentinel, item, sentinel.next);
        sentinel.next = n;
        n.next.prev = n;
        size++;
    }

    // add the item to the back
    public void addLast(Item item) {
        assertNullItem(item);
        Node<Item> n = new Node<>(sentinel.prev, item, sentinel);
        n.prev.next = n;
        sentinel.prev = n;
        size++;
    }

    // remove and return the item from the front
    public Item removeFirst() {
        assertEmptyDeque();
        Node<Item> tmp = sentinel.next;
        sentinel.next = tmp.next;
        tmp.next.prev = sentinel;
        tmp.next = null;
        tmp.prev = null;
        size--;
        return tmp.item;
    }

    // remove and return the item from the back
    public Item removeLast() {
        assertEmptyDeque();
        Node<Item> tmp = sentinel.prev;
        sentinel.prev = tmp.prev;
        tmp.prev.next = sentinel;
        tmp.next = null;
        tmp.prev = null;
        size--;
        return tmp.item;
    }

    // return an iterator over items in order from front to back
    @Override
    public Iterator<Item> iterator() { return new DequeIterator(); }

    private class DequeIterator implements Iterator<Item> {
        private Node<Item> current = sentinel.next;

        @Override
        public boolean hasNext() { return current != sentinel; }

        @Override
        public Item next() {
            if (!hasNext()) throw new java.util.NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
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
        if (isEmpty()) throw new java.util.NoSuchElementException("Deque underflow");
    }

    // unit testing (required)
    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(1);
        d.addFirst(2);
        d.addLast(3);
        System.out.println(d.size());

        Iterator<Integer> di = d.iterator();
        while (di.hasNext()) System.out.println(di.next());

        d.removeFirst();
        d.removeLast();

        Iterator<Integer> di1 = d.iterator();
        while (di1.hasNext()) System.out.println(di1.next());

        d.removeFirst();
        //d.removeFirst();

        d.addFirst(null);
    }
}