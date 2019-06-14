import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    // Complete the highestValuePalindrome function below.
    static String highestValuePalindrome(String s, int n, int k) {
        if (n == 1 && k >= 1) {
            return "9";
        }
        if (n == 1 && k == 0) {
            return s;
        }
        int startBack = n/2;
        int startFront = n/2 - 1;        
        if (n % 2 == 1) {
            startBack = n/2+1;
            startFront = n/2 - 1;
        }
        StringBuilder sb = new StringBuilder(s.substring(0,startBack));
        HashSet<Integer> changes = new HashSet<Integer>();
        int copyOfStartBack = startBack;
        int copyOfStartFront = startFront;
        int copyOfK = k;

        int count =  0;
        while (startFront >= 0 && k >=0) {
            if (s.charAt(startBack) != s.charAt(startFront)) {
                k--;
                count++;
                if (Character.getNumericValue(s.charAt(startBack)) <= Character.getNumericValue(s.charAt(startFront) )) {
                    sb.append(s.charAt(startFront));
                } else {
                    sb.append(s.charAt(startBack));
                    sb.replace(startFront,startFront+1, Character.toString(s.charAt(startBack)));
                }
                changes.add(startBack);

            } else {
                sb.append(s.charAt(startBack));
            }
            startBack++;
            startFront--;    
        }
        
        if (k < 0 ) {
            return "-1";
        }
        
        if (count >= copyOfK) {
            return sb.toString();
        }
        int extra = -1;
        if (count < copyOfK) {
            extra = copyOfK - count;
        }
        int half = copyOfStartFront;
        int front = 0;
        int back = s.length()-1;
        while(front < n/2) {
            if ( changes.contains(back) && sb.charAt(back) != '9' && extra >= 1) {
                sb.replace(front,front+1, "9");
                sb.replace(back,back+1, "9");
                extra-=1;
            }else if (sb.charAt(front) != '9' && extra >= 2) {
                sb.replace(front,front+1, "9");
                sb.replace(back,back+1, "9");
                extra-=2;
            }
            
            front++;
            back--;
        }
        if (n%2 == 1 && extra >= 1) {
            sb.replace(n/2,n/2+1, "9");
        }
        return sb.toString();

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        String s = scanner.nextLine();

        String result = highestValuePalindrome(s, n, k);

        bufferedWriter.write(result);
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
