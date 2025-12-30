package stack.checkForBalancedParanthesis;

import java.util.ArrayDeque;

public class CheckForBalancedParanthesis {

    public static void main(String args[]){
        // String input = "{[({})]}";
        // String input = "((())";
        String input = "([)]";
        System.out.println("are the parenthesis balanced ? "+isBalanced(input));
    }

    public static boolean isBalanced(String input){
        char [] c = input.toCharArray();
        ArrayDeque<Character> s = new ArrayDeque<>();

        for(char character : c){

            switch(character) {
                case '{' :                
                case '[' :
                case '(' :
                    s.push(character);
                    break;
                
                case '}' : 
                case ']' : 
                case ')' : 
                    if(s.size()==0){
                        return false;
                    }
                    char lastCharacter = s.pop();
                    boolean matched = (lastCharacter == '{' && character == '}')||
                    (lastCharacter == '[' && character == ']')||
                    (lastCharacter == '(' && character == ')');

                    if(!matched){
                        return false;
                    }
                    break;
                
            }

        }

        return s.isEmpty() ?  true : false; // stack should be empty at end

    }
    
}
