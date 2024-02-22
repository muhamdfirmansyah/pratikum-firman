package graph;
//folder untuk menyimpan class
import java.util.*;

class SimpleGraph { //clas simplegraf
    private Map<Integer, List<Integer>> adjacencyList;

    public SimpleGraph() { //konstruktor simple graf
        adjacencyList = new HashMap<>();
    }

    // Menambahkan node baru ke graf
    public void addNode(Integer node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Menambahkan edge ke graf
    public void addEdge(int source, int destination) {
        adjacencyList.get(source).add(destination);
        // Jika graf tidak berrah, tambahkan baris berikut:
        // adjacencyList.get(destination).add(source);
    }

    // Mendapatkan daftar data dari node
    public List<Integer> getNeighbors(int node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    // Mencetak graf
    public void printGraph() {
        for (int node : adjacencyList.keySet()) {
            System.out.print("Node " + node + " terhubung dengan: ");
            for (int neighbor : adjacencyList.get(node)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    //metode utama untuk jalankan program
    public static void main(String[] args) {
        SimpleGraph graph = new SimpleGraph();
        //tambahkan node ke graf
        graph.addNode(0);
        graph.addNode(1);
        graph.addNode(2);
        graph.addNode(3);

        //tambahkan sisi /edge ke graf
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);

        //cetak graf
        graph.printGraph();
    }
}