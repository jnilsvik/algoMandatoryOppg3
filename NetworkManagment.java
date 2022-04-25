import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.SynchronousQueue;

class NetworkManagment {
    //mengde knytpunkter
    static int VERTICES = 6;
    char[] nodes = {'A', 'B', 'C', 'D', 'E', 'F'};

    // finner min distanse av de som ikke er besøkt
    int minDistance(int[] dist, boolean[] visited) {
        // sett til max, sett index til -1 for å ikke peke på noe
        int min = Integer.MAX_VALUE,
                min_index = -1;
        for (int i = 0; i < VERTICES; i++) {
            if (!visited[i] && dist[i] <= min) {
                min = dist[i];
                min_index = i;
            }
        }
        return min_index;
    }

    void dijkstra(int[][] graph, int start, int assignment) {
        // output ; vil holde korteste distranse fra start til slutt
        // besøkt? ; sjekker om de har blitt besøkt'

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
        for (int count = 0; count < VERTICES - 1; count++) {
            // finner min.dist til neste ubesøkte vertex
            int u = minDistance(dist, visited);
            // setter node til "besøkt" / true
            visited[u] = true;
            // finn korteste vei for alle
            for (int v = 0; v < VERTICES; v++) {
                if (!visited[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                    prev[v] = u;
                }
            }
        }
        /*
        System.out.println("ver. \t d.start \t prev.");
        for (int i = 0; i < VERTICES; i++) {
            System.out.println(nodes[i] + " \t\t\t" + dist[i]+ " \t\t\t" + prev[i]);
        }
        */
        if (assignment == 1) {
            this.printP1(start, dist);
        } else {
            HashMap<String, Integer> alldata = new HashMap<>();
            HashMap<String, Integer> newdata = new HashMap<>();

            // fyller på kartenere
            for (int current = 0; current < VERTICES ; current++) {
                for (int j = 0; j < VERTICES ; j++) {
                    if(j != current){
                        newdata.put(nodes[j - 1] + " " + nodes[j], dist[j]);
                        System.out.println(nodes[j - 1] + " " + nodes[j] + " " + dist[j]);
                    }
                }
                System.out.println("---------------------");
            }

            // retrieving iterator from the routing tables HashMap
            Set entrySet = newdata.entrySet();
            Iterator it = entrySet.iterator();

            // outer loop on all routing tables
            while (it.hasNext()) {
                Map.Entry entry = (Map.Entry) it.next();
                // inner loop each entry in each routing table
                for (Map.Entry entry2 : alldata.entrySet()) {

                    // if the key exists
                    if(entry2.getKey() == entry.getKey()){
                        if((int)entry2.getValue() > (int)entry.getValue()){
                            alldata.put((String)entry.getKey(), (int) entry.getValue());
                        }
                    }else if(reverseString((String) entry2.getKey()) == entry.getKey()){
                        if((int)entry2.getValue() > (int)entry.getValue()){
                            alldata.put((String)reverseString((String) entry2.getKey()),(int) entry.getValue());
                        }
                    }

                }
                //if(entry2.getValue(entry.getKey()) > entry.getValue()){}
            }
        }


    }

    void printP1(int start, int[] dist){
        for(int current = 0; current < VERTICES; current++){

            if(current != start){
                System.out.println( "["+nodes[start] + " " +nodes[current]+ "]" + " \t\t\t" + dist[current]);
            }
        }
        System.out.println("_______________________");
    }



    void printP2(int start, int[] dist){
        for(int current = 0; current < VERTICES; current++){

            if(current != start){
                System.out.println( "["+nodes[start] + " " +nodes[current]+ "]" + " \t\t\t" + dist[current]);
            }
        }
        System.out.println("_______________________");
    }

    public static String reverseString(String str){
        StringBuilder sb=new StringBuilder(str);
        sb.reverse();
        return sb.toString();
    }
}
