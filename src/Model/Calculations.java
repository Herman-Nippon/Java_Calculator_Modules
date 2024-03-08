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
        Queue<String> outputQueue = new LinkedList<>();
        boolean expectOperand = true; // Flag to track if an operand is expected next

        for (int i = 0; i < mathExpression.length(); i++) {
            char c = mathExpression.charAt(i);
            if (Character.isDigit(c) || c == '.') {
                StringBuilder numBuilder = new StringBuilder();
                while (i < mathExpression.length() && (Character.isDigit(mathExpression.charAt(i)) || mathExpression.charAt(i) == '.')) {
                    numBuilder.append(mathExpression.charAt(i));
                    i++;
                }
                i--; // Move back one position as the loop will advance the index
                outputQueue.add(numBuilder.toString());
                expectOperand = false; // An operand was found
            } else if (c == '(') {
                if (!expectOperand) {
                    // If an operand is not expected, add a multiplication operator
                    outputQueue.add("*");
                }
                operatorStack.push(c);
                expectOperand = true; // After an opening parenthesis, expect an operand
            } else if (c == ')') {
                while (!operatorStack.isEmpty() && operatorStack.peek() != '(') {
                    outputQueue.add(String.valueOf(operatorStack.pop()));
                }
                if (!operatorStack.isEmpty() && operatorStack.peek() == '(') {
                    operatorStack.pop(); // Discard the '('
                } else {
                    // Mismatched parentheses
                    return "Mismatched parentheses!";
                }
                expectOperand = false; // After a closing parenthesis, an operand is not expected
            } else if (isOperator(c)) {
                if (expectOperand && c == '-') {
                    // Handle negative numbers
                    outputQueue.add("("); // Add '(' to indicate the start of a negative number
                    outputQueue.add("0"); // Add '0' as a placeholder for the negative sign
                    operatorStack.push(c); // Push '-' onto the operator stack
                } else {
                    // Regular operator
                    while (!operatorStack.isEmpty() && precedence(operatorStack.peek()) >= precedence(c)) {
                        outputQueue.add(String.valueOf(operatorStack.pop()));
                    }
                    operatorStack.push(c);
                    expectOperand = true; // After an operator, expect an operand
                }
            }
        }

        // Pop remaining operators from stack to output queue
        while (!operatorStack.isEmpty()) {
            outputQueue.add(String.valueOf(operatorStack.pop()));
        }

        // Call processRPN method to calculate the answer
        return processRPN(outputQueue);
    }

    private String processRPN(Queue<String> outputQueue) {
        Stack<Double> stack = new Stack<>();

        while (!outputQueue.isEmpty()) {
            String token = outputQueue.poll();
            if (isNumeric(token)) {
                stack.push(Double.parseDouble(token));
            } else if (isOperator(token.charAt(0))) {
                double num2 = stack.pop();
                double num1 = stack.pop();
                double result = performOperation(num1, num2, token.charAt(0));
                stack.push(result);
            }
        }

        return String.valueOf(stack.pop());
    }

    private boolean isNumeric(String str) {
        return str.matches("-?\\d+(\\.\\d+)?");  // match a number with optional '-' and decimal
    }

    private double performOperation(double num1, double num2, char operator) {
        switch (operator) {
            case '+':
                return num1 + num2;
            case '-':
                return num1 - num2;
            case '*':
                return num1 * num2;
            case '/':
                if (num2 != 0) {
                    return num1 / num2;
                } else {
                    return Double.NaN;
                }
            case '^':
                return Math.pow(num1, num2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }
}
