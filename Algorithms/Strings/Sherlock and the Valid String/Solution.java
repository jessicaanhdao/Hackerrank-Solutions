import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the isValid function below.
    static String isValid(String s) {
        int[] chars = new int[26];
        boolean twoFlag = false;
        
        int total = 0;
        int countDifChar = 0;
        int i=0;
        for (i=0 ; i <s.length(); i++) {
            if (chars[s.charAt(i)-'a']==0) {
                countDifChar++;
            }
            chars[s.charAt(i)-'a']++;

            total++;
        }
       
        Arrays.sort(chars);
        int max = chars[25];
        int min = chars[26-countDifChar];
       
        if (min == max && total == countDifChar * max ) {
            return "YES";
        } else {
            
            if (total== countDifChar * min+1) {
                return "YES";
            }
            if (total== (countDifChar-1) * max+1) {
                return "YES";
            }
            return "NO";
        }
        

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s = scanner.nextLine();

        String result = isValid(s);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
