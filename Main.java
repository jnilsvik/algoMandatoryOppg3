public class Main {
    public static void main(String[] args) {
        // write your code here

        // 6 * 6 data -> list(list())
        // table
        int[][] graph = new int[][]{
               //A  B   C   D    E      F
                {0, 10, 5, 9999,   3,  12},// A
                {10, 0, 17, 9,    17,  12},// B
                {5, 17, 0, 35, 3,      12},// C
                {9999, 9, 35, 0, 9999, 12},// D
                {3, 17, 3, 9999, 0,    12},// E
                {12, 12, 12, 12, 12,    0}};// F

        // NetworkManagment problem1 = new NetworkManagment();
        // problem1.dijkstra(graph, 0, 1);
        
        p2 path = new p2();
        path.dijkstra(graph);



    }
}
