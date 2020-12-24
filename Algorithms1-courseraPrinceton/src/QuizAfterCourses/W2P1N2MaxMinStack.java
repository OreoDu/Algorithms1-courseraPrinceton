import java.util.Stack;

/**
 * Coursera - Algorithms Part I
 * Week 2 - Interview Questions Part.1
 *
 * Question 2: Design a MaxMin stack that supports push, pop, top, peekMax and popMax.
 *
 * push(x) -- Push element x onto stack.
 * pop() -- Remove the element on top of the stack and return it.
 * top() -- Get the element on the top.
 * peekMax() -- Retrieve the maximum element in the stack.
 * popMax() -- Retrieve the maximum element in the stack, and remove it. If you find more than one maximum elements, only remove the top-most one.
 * peekMin() -- Retrieve the minimum element in the stack.
 * popMin() -- Retrieve the minimum element in the stack, and remove it. If you find more than one minimum elements, only remove the top-most one.
 */
public class W2P1N2MaxMinStack {
    public static class MaxMinStack {
        Stack<Integer> minStack;
        Stack<Integer> maxStack;
        Stack<Integer> store;

        public MaxMinStack() {
            minStack = new Stack<>();
            maxStack = new Stack<>();
            store = new Stack<>();
        }

        public void push(int x) {
            int max = maxStack.isEmpty()? x: maxStack.peek();
            int min = minStack.isEmpty()? x: minStack.peek();
            maxStack.push(x > max? x: max);
            minStack.push(x < min? x: min);
            store.push(x);
        }

        public int pop() {
            minStack.pop();
            maxStack.pop();
            return store.pop();
        }

        public int top() {
            return store.peek();
        }

        public int peekMax() {
            return maxStack.peek();
        }

        public int popMax() {
            int max = maxStack.peek();
            Stack<Integer> buffer = new Stack<>();
            while(top() != max) buffer.push(pop());
            pop();
            while(!buffer.isEmpty()) push(buffer.pop());
            return max;
        }

        public int peekMin() {
            return minStack.peek();
        }

        public int popMin() {
            int min = minStack.peek();
            Stack<Integer> buffer = new Stack<>();
            while(top() != min) buffer.push(pop());
            pop();
            while(!buffer.isEmpty()) push(buffer.pop());
            return min;
        }
    }

    public static void main(String[] args) {
        MaxMinStack stack = new MaxMinStack();
        stack.push(2);
        stack.push(5);
        stack.push(3);
        stack.push(4);
        stack.push(1);
        stack.top();
        System.out.println(stack.popMax());
        System.out.println(stack.peekMax());
        System.out.println(stack.popMin());
        System.out.println(stack.peekMin());
    }
}
