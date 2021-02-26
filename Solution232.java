class MyQueue {

    /** Initialize your data structure here. */
    Stack<Integer> stack;
    Stack<Integer> reverseStack;
    public MyQueue() {
        stack = new Stack<>();
        reverseStack = new Stack<>();
    }

    /** Push element x to the back of queue. */
    public void push(int x) {
        stack.push(x);
    }

    /** Removes the element from in front of queue and returns that element. */
    public int pop() {
        transfer();
        return reverseStack.pop();
    }

    /** Get the front element. */
    public int peek() {
        transfer();
        return reverseStack.peek();
    }

    /** Returns whether the queue is empty. */
    public boolean empty() {
        return stack.isEmpty() && reverseStack.isEmpty();
    }

    public void transfer() {
        if (reverseStack.isEmpty()) {
            while (!stack.isEmpty()) {
                reverseStack.push(stack.pop());
            }
        }
    }
}

/**
 * Your MyQueue object will be instantiated and called as such:
 * MyQueue obj = new MyQueue();
 * obj.push(x);
 * int param_2 = obj.pop();
 * int param_3 = obj.peek();
 * boolean param_4 = obj.empty();
 */