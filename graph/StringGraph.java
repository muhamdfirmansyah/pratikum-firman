package graph;
//folder untuk menyimpan class

import java.util.*;

class StringGraph { //class string graph
    private Map<String, List<String>> adjacencyList;

    public StringGraph() { //konstruktor string graf
        adjacencyList = new HashMap<>();
    }

    // Menambahkan node baru ke graf
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>());
    }

    // Menambahkan edge ke graf
    public void addEdge(String source, String destination) {
        adjacencyList.get(source).add(destination);
        // Jika graf tidak berarah, tambahkan baris berikut:
        // adjacencyList.get(destination).add(source);
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

    //metode utama untuk jalankan program
    public static void main(String[] args) {
        StringGraph graph = new StringGraph();
        //tambah node ke graf
        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");
        graph.addNode("D");

        //tambah edge ke graf
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");
        graph.addEdge("C", "A");
        graph.addEdge("C", "D");


        //cetak graf
        graph.printGraph();
    }
}
