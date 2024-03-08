package Model;

import java.util.*;

public class Calculations {
    private boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/' || c == '^';
    }

    private int precedence(char c) {
        return switch (c) {
            case '+', '-' -> 1;
            case '*', '/' -> 2;
            case '^' -> 3;
            default -> 0;
        };
    }

    public String processExpression(String mathExpression) {
        Stack<Character> operatorStack = new Stack<>();
        Queue<Character> outputQueue = new LinkedList<>();

        for (int i = 0; i < mathExpression.length(); i++) {
            char c = mathExpression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                outputQueue.add(c);
            } else if (isOperator(c)) {
                while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                    outputQueue.add(operatorStack.pop());
                }
                operatorStack.push(c);
            } else if (c == '(') {
                operatorStack.push(c);
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    outputQueue.add(operatorStack.pop());
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Discard the '('
                } else {
                    // Mismatched parentheses
                    return "Mismatched parentheses!";
                }
            }
        }

        // Pop remaining operators from stack to output queue
        while (!operatorStack.isEmpty()) {
            char topOperator = operatorStack.pop();
            if (topOperator == '(') {
                // Mismatched parentheses
                return "Mismatched parentheses!";
            }
            outputQueue.add(topOperator);
        }

        // Call processRPN method to calculate the answer
        return processRPN(outputQueue);
    }

    private String processRPN(Queue<Character> outputQueue) {
        Stack<Double> stack = new Stack<>();

        while (!outputQueue.isEmpty()) {
            char c = outputQueue.poll();
            if (Character.isDigit(c) || c == '.') {
                stack.push(Double.parseDouble(String.valueOf(c)));
            } else if (isOperator(c)) {
                double num2 = stack.pop();
                double num1 = stack.pop();
                double result = switch (c) {
                    case '+' -> num1 + num2;
                    case '-' -> num1 - num2;
                    case '*' -> num1 * num2;
                    case '/' -> (num2 != 0) ? num1 / num2 : Double.NaN;
                    case '^' -> Math.pow(num1, num2);
                    default -> 0; // Handle invalid operator gracefully
                };
                stack.push(result);
            }
        }

        return String.valueOf(stack.pop());
    }
}
