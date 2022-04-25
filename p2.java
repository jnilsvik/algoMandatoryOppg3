import java.util.*;

public class p2 {

    //mengde knytpunkter
    static int VERTICES = 6;
    char[] nodes = {'A','B','C','D','E','F'};

    // finner min distanse av de som ikke er besøkt
    int minDistance(int[] dist, boolean[] visited){
        // sett til max, sett index til -1 for å ikke peke på noe
        int     min= Integer.MAX_VALUE,
                min_index = -1;
        for (int i = 0; i < VERTICES; i++) {
            if (!visited[i] && dist[i] <= min){
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    void dijkstra(int[][] graph) {
        // output ; vil holde korteste distranse fra start til slutt
        // besøkt? ; sjekker om de har blitt besøkt'

        HashMap<String, Integer> alldata = new HashMap<>();
        HashMap<String, Integer> newdata = new HashMap<>();
        TreeMap<String, Integer> sorted = new TreeMap<>();

        for (int start = 0; start < VERTICES; start++) {

            int[] dist = new int[VERTICES];
            int[] prev = new int[VERTICES];
            boolean[] visited = new boolean[VERTICES];
            // setter inn data
            for (int i = 0; i < VERTICES; i++) {
                dist[i] = Integer.MAX_VALUE;
                visited[i] = false;
                prev[i] = -1;
            }
            // setter distanse til selv som 0
            dist[start] = 0;
            // finn kortest distranse for alle
            for (int count = 0; count < VERTICES -1; count++) {
                // finner min.dist til neste ubesøkte vertex
                int u = minDistance(dist,visited);
                // setter node til "besøkt" / true
                visited[u] = true;
                // finn korteste vei for alle
                for (int v = 0; v < VERTICES; v++) {
                    if (!visited[v]
                            && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                            && dist[u]+ graph[u][v] < dist[v]) {
                        dist[v] = dist[u] + graph[u][v];
                        prev[v] = u;
                    }
                }
            }

            for(int current = 0; current < VERTICES; current++){
                if(current != start){
                    String name = ""+ nodes[start] + " " +  nodes[current];
                    String inverseName = reverseString(name);
                    if (newdata.containsKey(inverseName)) {
                        if (newdata.get(inverseName) > dist[current]) newdata.put(inverseName, dist[current]);
                    }
                    else newdata.put(name, dist[current]);
                //sjekke om
                }
            }
        }
        //test
        sorted.putAll(newdata);
        for(Map.Entry entry : sorted.entrySet()){
            System.out.println("["+entry.getKey() + "] " + entry.getValue());
        }
    }

    public static String reverseString(String str){
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}

