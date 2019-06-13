import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Edge implements Comparable<Edge> {
    int weight;
    int from;
    int to;

    Edge(int weight, int from, int to) {
        this.weight = weight;
        this.from = from;
        this.to = to;
    }

    public int compareTo(Edge e2) {
        return e2.weight - this.weight;
    }
}
public class Solution {

    static int findParent(int i, int[] parents) {
        if (parents[i] == i) {
            return i;
        } else {
            return findParent(parents[i],parents);
        }
    }
    // Complete the minTime function below.
    static int minTime(int n, int[][] roads, HashSet<Integer> machines, PriorityQueue<Edge> edges ) {
       
        int[] parents = new int[n];
        //init parents
        for (int i = 0;  i < n; i++) {
            parents[i] = i;
        }

        int[] parents_machines =  new int[n];
        for (int i = 0;  i < n; i++) {
            if (machines.contains(i)) {
                parents_machines[i] = i;
            } else {
                parents_machines[i] = -1;
            }
            //System.out.println(i+" - "+parents_machines[i]);
        }
        int remove = 0;
        int min = 0;
        while(edges.size() > 0 ) {
            Edge currentEdge = edges.remove();
            int a =  findParent(currentEdge.from,parents);
            int b = findParent(currentEdge.to,parents);
            if (a==b) {
                continue;
            }
            if (parents_machines[a] != -1  && parents_machines[b] != -1 ) {
                min += currentEdge.weight;
                
            } else {
                parents[a] = b;
                parents_machines[a] = Math.max(parents_machines[a],parents_machines[b]);
                 parents_machines[b] =  parents_machines[a];
            }
            
   
            
        }
        return min;


    }

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] nk = scanner.nextLine().split(" ");

        int n = Integer.parseInt(nk[0]);

        int k = Integer.parseInt(nk[1]);

        int[][] roads = new int[n - 1][3];
        PriorityQueue<Edge> edges = new PriorityQueue<Edge>();
        for (int i = 0; i < n - 1; i++) {
            String[] roadsRowItems = scanner.nextLine().split(" ");
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            int weight = Integer.parseInt(roadsRowItems[2]);
            int from = Integer.parseInt(roadsRowItems[0]);
            int to = Integer.parseInt(roadsRowItems[1]);
            edges.add(new Edge(weight,from,to));
        }

        HashSet<Integer> machines = new HashSet<Integer>();

        for (int i = 0; i < k; i++) {
            int machinesItem = scanner.nextInt();
            scanner.skip("(\r\n|[\n\r\u2028\u2029\u0085])?");
            machines.add(machinesItem);
        }

        int result = minTime(n, roads, machines, edges);

        bufferedWriter.write(String.valueOf(result));
        bufferedWriter.newLine();

        bufferedWriter.close();

        scanner.close();
    }
}
