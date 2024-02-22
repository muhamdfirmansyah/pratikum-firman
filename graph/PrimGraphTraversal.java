package graph;

//folder untuk menyimpan clas
import java.util.*;

// Kelas Edge merepresentasikan sisi (edge) dalam graf.
class Edge implements Comparable<Edge> {
    // Node sumber sisi
    String source;

    // Node tujuan sisi
    String destination;

    // Bobot sisi
    int weight;

    // Konstruktor untuk membuat sisi baru
    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Metode untuk membandingkan sisi dasrakan bobotnya (digunakan untuk
    // pengurutan)
    @Override
    public int compareTo(Edge other) {
        // Kembalikan nilai negatif jika sisi ini lebih kecil dari sisi lain,
        // nilai positif jika sisi ini lebih besar dari sisi lain,
        // dan 0 jika kedua sisi sama.
        return this.weight - other.weight;
    }
}

// Kelas PrimGraphTraversal mengimplementasikan algoritma Prim untuk mencari
// Minimum Spanning Tree (MST).
public class PrimGraphTraversal {

    // Map untuk menyimpan graf sebagai daftar ketetanggaan (adjacency list)
    private Map<String, List<Edge>> adjacencyList;

    // Konstruktor untuk menginisialisasi graf
    public PrimGraphTraversal() {
        adjacencyList = new HashMap<>();
    }

    // Metode untuk menambahkan simpul (node) ke graf
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Metode untuk menambahkan sisi ke graf
    public void addEdge(String source, String destination, int weight) {
        // Tambahkan sisi dari source ke destination dan sebaliknya (untuk graf tidak
        // terarah)
        adjacencyList.get(source).add(new Edge(source, destination, weight));
        adjacencyList.get(destination).add(new Edge(destination, source, weight));
    }

    // Metode untuk mendapatkan daftar tetangga dari suatu node
    public List<Edge> getNeighbors(String node) {
        // Kembalikan daftar tetangga, atau daftar kosong jika node tidak ada
        return adjacencyList.getOrDefault(node, Collections.emptyList());
    }

    // Metode untuk menjalankan algoritma Prim dan mencetak MST
    public void primMST(String start) {
        // PriorityQueue untuk menyimpan sisi yang akan dipertimbangkan, diurutkan
        // berdasarkan bobot
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.weight));

        // Set untuk menyimpan node yang sudah ada di MST
        Set<String> inMST = new HashSet<>();

        // Map untuk menyimpan sisi yang digunakan untuk mencapai suatu node dalam MST
        Map<String, Edge> edgeTo = new HashMap<>();

        // Mulai algoritma dari node start
        inMST.add(start);
        for (Edge edge : adjacencyList.getOrDefault(start, Collections.emptyList())) {
            pq.add(edge);
            edgeTo.put(edge.destination, edge);
        }

        int totalWeight = 0; // Menyimpan total bobot MST

        // perulangan (while) sampai semua node masuk ke MST
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            // Jika node tujuan sudah ada di MST, abaikan sisi ini
            if (inMST.contains(edge.destination)) {
                continue;
            }

            // Tambahkan node tujuan ke MST
            inMST.add(edge.destination);
            System.out
                    .println(edgeTo.get(edge.destination).source + " - " + edge.destination + " [" + edge.weight + "]");
            totalWeight += edge.weight;

            for (Edge nextEdge : adjacencyList.getOrDefault(edge.destination, Collections.emptyList())) {
                if (!inMST.contains(nextEdge.destination)) {
                    pq.add(nextEdge);
                    edgeTo.put(nextEdge.destination, nextEdge);
                }
            }
        }

        System.out.println("Total bobot MST: " + totalWeight);
    }

    // Metode utama untuk menjalankan algoritma
    public static void main(String[] args) {

        PrimGraphTraversal graph = new PrimGraphTraversal();

        //tambahkan node ke graf
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");
        graph.addNode("E");

        //tambhakan sisi ke graf
        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "C", 7);
        graph.addEdge("B", "E", 10);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "E", 3);


        //program dijalankan dengan node A
        graph.primMST("A");
    }

}
