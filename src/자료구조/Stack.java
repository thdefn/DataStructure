package 자료구조;

public class Stack {
    static int[] arr;
    static int top = -1;

    Stack(int capacity) {
        arr = new int[capacity];
    }

    public void push(int n) {
        if (isFull())
            throw new RuntimeException("stack over flow");
        arr[++top] = n;
    }

    public int pop() {
        if (isEmpty())
            throw new RuntimeException("stack under flow");
        return arr[top--];
    }

    public int peek() {
        if (top == -1)
            throw new RuntimeException("stack under flow");
        return arr[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == arr.length - 1;
    }

    public static void main(String[] args) {
        Stack stack = new Stack(8);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        stack.push(4);
        stack.push(5);
        stack.push(6);
        stack.push(7);
        stack.push(8);
        //stack.push(9);
        System.out.println(stack.isFull());


        System.out.println(stack.top);
        System.out.println(stack.peek());
        System.out.println(stack.pop());
        System.out.println(stack.top);
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.pop());
        System.out.println(stack.isEmpty());
        //System.out.println(stack.pop());
    }
}

