import java.util.Stack;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions Part.1
 *
 * Question 1: Queue with Two Stacks
 *
 * Implement a queue with two stacks so that each queue operations
 * takes a constant amortized number of stack operations.
 */
public class W2P1N1QueuewithTwoStacks {
    class MyQueue {
        private Stack<Integer> enqueue;
        private Stack<Integer> dequeue;
        private int front;

        /** Initialize your data structure here. */
        public MyQueue() {
            enqueue = new Stack<>();
            dequeue = new Stack<>();
        }

        /** Push element x to the back of queue. */
        public void push(int x) {
            if (enqueue.isEmpty()) front = x;
            enqueue.push(x);
        }

        /** Removes the element from in front of queue and returns that element. */
        public int pop() {
            if (!dequeue.isEmpty()) {
                return dequeue.pop();
            } else {
                if (!enqueue.isEmpty()) {
                    while(!enqueue.isEmpty()) {dequeue.push(enqueue.pop());}
                    return dequeue.pop();
                } else {
                    System.out.println("There is no element in the stack!");
                    return -1;
                }
            }
        }

        /** Get the front element. */
        public int peek() {
            if (!dequeue.isEmpty()) return dequeue.peek();
            else if (!enqueue.isEmpty()) return front;
            else {
                System.out.println("The Queue is empty!");
                return -1;
            }
        }

        /** Returns whether the queue is empty. */
        public boolean empty() {
            return enqueue.isEmpty() && dequeue.isEmpty();
        }
    }
}
