package Project2;

/* Habiba Elswify
 * 202517637
 * 5/24/24
 * This program takes an infix expression as input and loops through the 
 * expression, converting it to postfix using the stack. 
 */


import java.util.*;

public class InfixToPostfix {
    // Creates a string for post anwser
    public static String post = "";
    // Creates hashmap for the precedence
    public static HashMap<Character, Integer> precedence = new HashMap<>();
    
    // Main function
    public String infix2postfix(String infix) {

        // loads precedence hashmap
        loadPrecedence();
        
        // new stack 
        Stack<Character> stack = new Stack<Character>();   
        post = "";

        // string builder object to help make numbers
        StringBuilder Number = new StringBuilder();

        // for loop to go through input statement
        for (int j=0; j<infix.length(); j++) {

            
            char c = infix.charAt(j);

            // if number add to builder else add to post
            if (Character.isDigit(c)) {
                Number.append(c);
            } else {
                if (Number.length() > 0) {
                    post += Number.toString();
                    post += ' ';
                    Number.setLength(0); 
                }
            }
            
            // if bracket add to stack
            if (c == '(' || c == '{') {
                stack.push(c);
            }

            // if end braket pop
            if (c == ')') {
                char p = ' ';
                while (stack.peek() != '(') {
                    p = stack.pop(); 
                    post += p;
                    post += ' ';
                }
                p = stack.pop();
            }

            // if end bracket pop
            if (c == '}') {
                char p = ' ';
                while (stack.peek() != '{') {
                    p = stack.pop(); 
                    post += p;
                    post += ' ';
                }
                p = stack.pop();
            }

            // if operator add to stack
            if (c == '+' || c == '-' || c == '*' || c == '/') {
                while (!stack.isEmpty() && precedence.get(stack.peek()) >= precedence.get(c)) {
                    char p = stack.pop();
                    post += p;
                    post += ' ';
                }
                stack.push(c);
            }
        }

        // if number is multiple digit convert to string add to post and add space
        if (Number.length() > 0) {
            post += Number.toString();
            post += ' ';
        }

        // if is not empty pop the remaining and add space
        while (!stack.isEmpty()){
            char p = stack.pop();
            post += p;
            post += ' ';
        }

        // trim extra spaces
        return post.trim(); 
    }

    // adds the values for precedence hashmap
    public static void loadPrecedence(){
        precedence.put('+', 1);
        precedence.put('-', 1);
        precedence.put('*', 2);
        precedence.put('/', 2);
        precedence.put('(', 0);
        precedence.put('{', 0);
    }
}