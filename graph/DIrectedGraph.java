package graph;  // folder untuk menyimpan class

import java.util.*;  

class DirectedGraph {  // class untuk  graph berarah
    private Map<String, List<String>> adjacencyList;  // Daftar data untuk menyimpan graph

    public DirectedGraph() {  // Konstruktor untuk menginisialisasi graf
        adjacencyList = new HashMap<>();
    }

    // Metode untuk tambahkan simpul node ke grp
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());  // Tambahkan simpul jika tidak ada
    }

    // Metode untuk tambahkan sisi (edge) ke graph (hanya satu arah untuk graph berarah)
    public void addEdge(String source, String destination) {
        // Pastikan node sumber ada dalam adjacency list
        adjacencyList.putIfAbsent(source, new ArrayList<>());

        // Tambahkan edge dari sumber ke tujuan
        adjacencyList.get(source).add(destination);
    }

    // Metode untuk mendapatkan daftar data dari node
    public List<String> getNeighbors(String node) {
        return adjacencyList.getOrDefault(node, Collections.emptyList());  // Kembalikan daftar data, atau daftar kosong jika tidak ada
    }

    // Metode untuk mencetak grph
    public void printGraph() {
        for (String node : adjacencyList.keySet()) {
            System.out.print("Node " + node + " terhubung dengan: ");
            for (String neighbor : adjacencyList.get(node)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }

    // Metode utama untuk menjalnkan program
    public static void main(String[] args) {
        DirectedGraph graph = new DirectedGraph();
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        graph.addEdge("A", "B");  // Buat edge dari A ke B
        graph.addEdge("A", "C");  // Buat edge dari A ke C
        graph.addEdge("B", "C");  // Buat edge dari B ke C
        graph.addEdge("C", "A");  // Buat edge dari C ke A
        graph.addEdge("C", "D");  // Buat edge dari C ke D

        graph.printGraph();  // Cetak graf untuk melihat hubungan antar node
    }
}
