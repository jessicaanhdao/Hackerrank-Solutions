import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

public class Solution {

    static int findParents(int child, int[] parents) {
        if (parents[child] == child) {
            return child;
        } 
        return findParents(parents[child], parents);
    }
   
    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));


        String[] np = br.readLine().split(" ");

        int n = Integer.parseInt(np[0]);

        int p = Integer.parseInt(np[1]);

        int[] parents = new int[n];
        int[] children = new int[n];
        for (int i = 0 ; i < n; i++) {
            parents[i] = i;
            children[i] = 1;
        } 

        for (int i = 0; i < p; i++) {
            String[] astronautRowItems = br.readLine().split(" ");

            int parent1 = findParents(Integer.parseInt(astronautRowItems[0]), parents);
            int parent2 = findParents(Integer.parseInt(astronautRowItems[1]), parents);
            if (parent1 != parent2) {
                if (children[parent1] > children[parent2]) {
                    parents[parent2] =  parent1;
                    children[parent1] += children[parent2];
                    children[parent2] = 0;
                } else {
                    parents[parent1] =  parent2;
                    children[parent2] += children[parent1];
                    children[parent1] = 0;
                    groups.add(parent2);

                }
            }
           
        }
        long previousSum = 0;
        long result = 0;
        for (int i = 0 ; i < children.length; i++) {
            result += children[i]*previousSum;
            previousSum+=children[i];
        }
        //int result = journeyToMoon(n, astronaut);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

    }
}
