 import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.function.*;
import java.util.regex.*;
import java.util.stream.*;
import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

public class Solution {

    // Complete the evenForest function below.
    static int evenForest(int tNodes, int t_edges, List<Integer>[] adjacents) {
        int count = 0;
        int[] childrenNums = new int[tNodes+1];
        for (int i = adjacents.length - 1; i > 0; i-- ) {
            if (adjacents[i].size() == 0 ) {
                childrenNums[i] = 0;
            } else {
                for (int j = 0 ; j < adjacents[i].size() ; j++) {
                    childrenNums[i] += 1 + childrenNums[adjacents[i].get(j)];
                }
                
            }
            
        }
        for (int i = 0 ; i < childrenNums.length; i++) {
            if (childrenNums[i] %2 != 0 && childrenNums[i] != tNodes-1  ) {
                count++;
            }
        }
        return count;


    }

    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        String[] tNodesEdges = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int tNodes = Integer.parseInt(tNodesEdges[0]);
        int tEdges = Integer.parseInt(tNodesEdges[1]);


        List<Integer>[] adjacents = new ArrayList[tNodes+1];
        for (int i = 0 ; i < adjacents.length; i++) {
            adjacents[i] = new ArrayList();
        }
        for (int i = 0 ; i < tEdges; i++) {
            String[] tFromTo = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");
            if (Integer.parseInt(tFromTo[0]) < Integer.parseInt(tFromTo[1])) {
                adjacents[Integer.parseInt(tFromTo[0])].add(Integer.parseInt(tFromTo[1]));
            } else {
                adjacents[Integer.parseInt(tFromTo[1])].add(Integer.parseInt(tFromTo[0]));
            }
            
        }

        int res = evenForest(tNodes, tEdges, adjacents);

        bufferedWriter.write(String.valueOf(res));
        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
