package leet150.stack;

import java.util.ArrayList;

public class MinStack {
    ArrayList<Integer> value;
    ArrayList<Integer> min;
    int top = -1;

    public MinStack() {
        value = new ArrayList<>();
        min = new ArrayList<>();
    }

    public void push(int val) {
        value.add(val);
        if (top != -1) min.add(Math.min(min.get(top), val));
        else min.add(val);
        top++;
    }

    public void pop() {
        value.remove(top);
        min.remove(top);
        top--;
    }

    public int top() {
        return value.get(top);
    }

    public int getMin() {
        return min.get(top);
    }

    public static void main(String[] args) {
        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println(minStack.getMin()); // return -3
        minStack.pop();
        System.out.println(minStack.top());    // return 0
        System.out.println(minStack.getMin()); // return -2
    }
}
