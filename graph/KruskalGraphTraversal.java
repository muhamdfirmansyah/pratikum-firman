package graph;
//folder untuk menyimpan class

import java.util.*;

class Edge implements Comparable<Edge> {
    String source;       // Node sumber sisi
    String destination;  // Node tujuan sisi
    int weight;          // Bobot sisi

    // Konstruktor untuk melakukan sisi baru
    public Edge(String source, String destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }

    // Metode comparet untuk membandingkan sisi berdasarkan bobotnya.
    // Digunakan untuk urutan sisi.
    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

// Kelas KruskalGraph Traversal mengimplementasikan algoritma krukal untuk mencari Minimum Spanning Tree.
public class KruskalGraphTraversal {
    private List<Edge> edges = new ArrayList<>(); // Daftar untuk simpan semua sisi.
    private Map<String, String> parent = new HashMap<>(); // Map untuk simpan representasi set (Union-Find).

    // Metode untuk menambahkan sisi ke graf.
    public void addEdge(String source, String destination, int weight) {
        edges.add(new Edge(source, destination, weight));
    }

    // Metode 'find' untuk Union-Find. Mencari rpresentasi set dari sebuah node.
    // Jika sebuah node tidak memiliki representasi, ia melakukannya sendiri.
    public String find(String node) {
        if (!parent.containsKey(node)) {
            parent.put(node, node);
        }

        // Path Compression: Meningkatkan efisiensi dengan hubungkan node langsung ke representasi setnya.
        if (!parent.get(node).equals(node)) {
            parent.put(node, find(parent.get(node)));
        }

        return parent.get(node);
    }

    // Metode 'union' untuk menggabungkan dua set dalam Union-Find.
    public void union(String node1, String node2) {
        String parent1 = find(node1);
        String parent2 = find(node2);
        parent.put(parent1, parent2);
    }

    // Metode untuk menjalankan algoritma Kruskal dan mencetak Minimum Spanning Tree.
    public void kruskalMST() {
        Collections.sort(edges); // Mengurutkan semua sisi berdasarkan bobot.

        int mstWeight = 0; // Menyimpan total bobot dari MST.
        for (Edge edge : edges) {
            String root1 = find(edge.source);
            String root2 = find(edge.destination);

            // Jika dua node tidak berada dalam set yang sama, tambahkan sisi ke MST dan gabungkan setnya.
            if (!root1.equals(root2)) {
                System.out.println(edge.source + " - " + edge.destination + " [" + edge.weight + "]");
                mstWeight += edge.weight;
                union(edge.source, edge.destination);
            }
        }

        System.out.println("Total bobot MST: " + mstWeight);
    }

    // Metode utama untuk menjalankan algoritma.
    public static void main(String[] args) {
        KruskalGraphTraversal graph = new KruskalGraphTraversal();
        
        // Menambahkan sisi-sisi ke graf.
        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "C", 7);
        graph.addEdge("B", "E", 10);
        graph.addEdge("C", "D", 8);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "E", 3);
        
        // Menjalankan algoritma Kruskal.
        graph.kruskalMST();
    }

}