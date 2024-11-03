package Project2;

/* Habiba Elswify
 * 5/24/24
 * This program takes an expression as input and determines if it is a proper 
 * statement. If any errors are found within the expression, the program
 * will print a carrot under the problem and print an error to the user.
 */

import java.util.HashMap;
import java.util.Stack;

public class ExpressionEvaluation {
    // Create hashmap for errors and pairs
    static HashMap<Integer, String> errorMessage = new HashMap<>();
    static HashMap<Character, Character> pair = new HashMap<>();

    // method
    public boolean expressionEvaluation(String statement) {

        // load the maps up
        loadErrorMessage();
        loadPair();
        
        // create new stack
        Stack<Character> stack = new Stack<>();

        // value for tracking validity of statements
        boolean valid = true;

        // loop through statement
        for (int j = 0; j < statement.length(); j++) {
            // current char
            char c = statement.charAt(j);

            // if start bracket add to stack
            if ((c=='{') || (c=='(')) {
                stack.push(c);
            }

            // end bracket
            if (c==')') {
                // check statck empty and if so print error 
                if (stack.isEmpty()) {
                    printError(statement, j, 3);
                    valid=false;
                    break;
                } else {
                    // else check if poped char matches
                    char PopedChar = stack.pop();
                    if (!(PopedChar == pair.get(c))) {
                        printError(statement, j, 1);
                        valid = false;
                        break;
                    }
                }
            }

            // other end bracket
            else if (c=='}') {
                // check if empty give error
                if (stack.isEmpty()) {
                    printError(statement, j, 3);
                    valid=false;
                    break;
                }
                else {
                    // check if poped char has pair
                    char PopedChar = stack.pop();
                    if (!(PopedChar == pair.get(c))) {
                        printError(statement, j, 2);
                        valid=false;
                        break;
                    }
                }
            }
        }
        // if valid is true and the stack is not empty, give error
        if (valid && !stack.isEmpty()) {
            printError(statement, statement.length()-1, 3);
            valid=false;
        }
        // returns valid which will either be true or false
        return valid;

    }

    // loads hashmap for checking pairs
    public static void loadPair() {
        pair.put('}', '{');
        pair.put(')', '(');
    }

    // prints errors based on statement, location and errorNo
    public static void printError(String statement, int location, int errorNo) {
        System.out.println(statement);
        for (int i = 0; i < location; i++) {
            System.out.print(" ");
        }
        System.out.print("^ ");
        System.out.println(errorMessage.get(errorNo));
        System.out.println();
    }

    // loads error hashmap with errors
    public static void loadErrorMessage() {
        errorMessage.put(1, "} expected");
        errorMessage.put(2, ") expected");
        errorMessage.put(3, "Incomplete Expression");
    }
}
