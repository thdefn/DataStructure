package leet150.stack;

import java.util.ArrayList;

public class EvaluateReversePolishNotation {
    public static int evalRPN(String[] tokens) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (String token : tokens) {
            if (token.equals("+")) {
                int b = numbers.remove(numbers.size() - 1);
                int a = numbers.remove(numbers.size() - 1);
                numbers.add(a + b);
            } else if (token.equals("-")) {
                int b = numbers.remove(numbers.size() - 1);
                int a = numbers.remove(numbers.size() - 1);
                numbers.add(a - b);
            } else if (token.equals("*")) {
                int b = numbers.remove(numbers.size() - 1);
                int a = numbers.remove(numbers.size() - 1);
                numbers.add(a * b);
            } else if (token.equals("/")) {
                int b = numbers.remove(numbers.size() - 1);
                int a = numbers.remove(numbers.size() - 1);
                numbers.add(a / b);
            } else {
                numbers.add(Integer.parseInt(token));
            }
        }
        return numbers.get(numbers.size() - 1);
    }

    public static void main(String[] args) {
        String[] tokens = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
        System.out.println(evalRPN(tokens));
    }
}
