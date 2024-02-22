

package graph;
//folder untuk menyimpan class
import java.util.*;

class Edge {
    String destination; // simpul tujuan
    int weight; //bobot sisi

    public Edge(String destination, int weight) { //konstruktor untk melakukan sisi
        this.destination = destination;
        this.weight = weight;
    }
}


//class utama untuk algoritma nya
class WeightedGraph {
    private Map<String, List<Edge>> adjacencyList; // untuk simpan graph

    public WeightedGraph() {
        adjacencyList = new HashMap<>();
    }

    // Menambahkan node baru ke graf
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Menambahkan edge berbobot ke graf
    public void addEdge(String source, String destination, int weight) {
        // Pastikan node sumber ada dalam adjacency list
        adjacencyList.putIfAbsent(source, new ArrayList<>());

        // Tambahkan edge berbobot
        adjacencyList.get(source).add(new Edge(destination, weight));
    }

    // Mencetak graf
    public void printGraph() {
        for (String node : adjacencyList.keySet()) {
            System.out.print("Node " + node + " terhubung dengan: ");
            for (Edge edge : adjacencyList.get(node)) {
                System.out.print(edge.destination + "(" + edge.weight + ") ");
            }
            System.out.println();
        }
    }

//metode utama untuk jalankan program
    public static void main(String[] args) {
        WeightedGraph graph = new WeightedGraph(); //memanggil objek graph nya
        //tambahkan node ke graf
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        //tambahkan edge ke graf
        graph.addEdge("A", "B", 5);
        graph.addEdge("A", "C", 3);
        graph.addEdge("B", "C", 2);
        graph.addEdge("C", "D", 4);


        //cetak graf
        graph.printGraph();
    }
}