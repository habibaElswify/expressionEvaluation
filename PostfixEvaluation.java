package Project2;

/* Habiba Elswify
 * 202517637
 * 5/24/24
 * This program takes a postfix expression as input and then loops through it, 
 * and evaluates the result of the expression.
 */


import java.util.Stack;

public class PostfixEvaluation {

    // method
    public int postfixEvaluation(String statement) {
        // creates stack
        Stack<Integer> stack = new Stack<>();

        // loop through input
        for (int i = 0; i < statement.length(); i++) {
            // current char in loop
            char current = statement.charAt(i);

            // if it is a digit
            if (Character.isDigit(current)) {
                // string builder to help with multidigit inputs
                StringBuilder Builder = new StringBuilder();
                
                // loop through statement for digits that are close together 
                while (i < statement.length() && Character.isDigit(statement.charAt(i))) {
                    Builder.append(statement.charAt(i));
                    i++;
                }
                // resets
                i--;

                // push the int to stack
                stack.push(Integer.parseInt(Builder.toString()));
            } else if (current != ' ') {
                // if operator pop twice
                int num2 = stack.pop();
                int num1 = stack.pop();
                int num3 = 0;

                // perform operations
                if (statement.charAt(i) == '+') {
                    num3 = (int)num1 + (int)num2;
                }
                if (statement.charAt(i) == '-') {
                    num3 = (int)num1 - (int)num2;
                }
                if (statement.charAt(i) == '*') {
                    num3 = (int)num1 * (int)num2;
                }
                else if (statement.charAt(i) == '/') {
                    num3 = (int)num1 / (int)num2;
                }

                // push answer to stack
                stack.push(num3);
            }
        }
        // Return final value
        return stack.pop();
    }
}