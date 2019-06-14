import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {
    static int LCS(String s1, String s2, int[][] memo) {
        int i =0, j=0;
        for(i = 0; i<=s1.length(); i++ ) {
            for( j = 0; j<=s2.length(); j++ ) {
                if (i == 0 || j == 0) {
                    memo[i][i] = 0;
                } else {
                    if (s1.charAt(i-1) == s2.charAt(j-1)) {
                        memo[i][j] = 1+memo[i-1][j-1];
                    } else {
                        memo[i][j] = Math.max(memo[i-1][j],memo[i][j-1]);
                    }
                }
            }   
        }
        return memo[i-1][j-1];
    }
    // Complete the commonChild function below.
    static int commonChild(String s1, String s2) {
        int[][] memo = new int[s1.length()+1][s2.length()+1];
        int count = LCS(s1,s2,memo);
        return count;

    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String s1 = scanner.nextLine();

        String s2 = scanner.nextLine();

        int result = commonChild(s1, s2);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
