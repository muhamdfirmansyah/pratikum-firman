package graph;
//folder untuk menyimpan graf

import java.util.*;

class UndirectedGraph { //class nya
    private Map<String, List<String>> adjacencyList;

    public UndirectedGraph() { //konstruktor
        adjacencyList = new HashMap<>(); // map untuk menyimpan graph nya
    }

    // Menambahkan node baru ke graf
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Menambahkan edge ke graf (tambahkan di kedua arah untuk graf tidak berarah)
    public void addEdge(String source, String destination) {
        // Pastikan kedua node ada dalam adjacency list
        adjacencyList.putIfAbsent(source, new ArrayList<>());
        adjacencyList.putIfAbsent(destination, new ArrayList<>());

        // Tambahkan edge dari sumber tujuan dan sebaliknya
        adjacencyList.get(source).add(destination);
        adjacencyList.get(destination).add(source);
    }

    // Mendapatkan daftar tetangga dari node
    public List<String> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    // Mencetak graf
    public void printGraph() {
        for (String node : adjacencyList.keySet()) {
            System.out.print("Node " + node + " terhubung dengan: ");
            for (String neighbor : adjacencyList.get(node)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }


    //metode utama untuk jalankan program graf
    public static void main(String[] args) {
        UndirectedGraph graph = new UndirectedGraph();
        //tambahkan node ke graf
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");


        //tambahkan edge ke graf
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "D");


        //cetak graf
        graph.printGraph();
    }
}