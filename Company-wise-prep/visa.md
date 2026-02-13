1. Problem 1: Sum of Uppercase and Lowercase Characters

You are given a string consisting of alphabets and other characters.
The task was to calculate the sum of ASCII values of all lowercase and uppercase letters separately.

```

import java.util.*;

class Main {
    
    public static void main(String args[]){
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        sc.close();
        
        int[] res = computeAscii(input);
        
    }
    
    public static int[] computeAscii(String input){
        int lowerCaseSum = 0;
        int upperCaseSum = 0;
        
        for(char ch : input.toCharArray()){
            if(Character.isLowerCase(ch)){
                lowerCaseSum += ch;
            }else if(Character.isUpperCase(ch)){
                upperCaseSum += ch;
            }
        }
        
        return new int[]{lowerCaseSum, upperCaseSum};
        
    }

}
```