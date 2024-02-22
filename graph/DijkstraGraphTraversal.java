package graph;
//folder untuk menyimpan class

import java.util.*;

class Sisi {
    String destination; //simpul tujuan//
    int weight; // bobot graph nya

    public Sisi(String destination, int weight) { //konstruktor membuat objek sisi
        this.destination = destination;
        this.weight = weight;
    }
}

//class utama dalam algoritma digraph
public class DijkstraGraphTraversal {
    private Map<String, List<Sisi>> adjacencyList = new HashMap<>(); //untk menyimpan graf

    //meotde untuk menambah simpul ke graf
    public void addNode(String node) {
        adjacencyList.putIfAbsent(node, new ArrayList<>()); //tambah simpul
    }

    //metode untk menambahkan sisi ke graf
    public void addEdge(String source, String destination, int weight) {
        
        addNode(source);  //pastikan sumber dan tujuan ada di graf
        addNode(destination);

        adjacencyList.get(source).add(new Sisi(destination, weight)); //uuntuk graf berarah 
        
        adjacencyList.get(destination).add(new Sisi(source, weight)); //tambahknn sisi sebalik nya jika graf tak berarah
    }


    //metode untuk menjalankan algoritma
    public int dijkstra(String start, String end) {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(Map.Entry.comparingByValue());
        Map<String, Integer> distances = new HashMap<>(); //map untuk menyimpan jarak simpul awal ke setiap simpul
        Set<String> settled = new HashSet<>(); //untk menyimpan simpul yamg telah selesai di proses


        //inisialisasi jarak simpul dengan nilai tak terhingga
        for (String node : adjacencyList.keySet()) {
            distances.put(node, Integer.MAX_VALUE);
        }
        //atur jarak simpul awal menjadi 0
        distances.put(start, 0);
        pq.add(new AbstractMap.SimpleEntry<>(start, 0));


        //perulangan algoritma
        while (!pq.isEmpty()) { 
            String current = pq.poll().getKey(); //abmbil simpul dengn jarakk terdekat 
            if (current.equals(end)) { //jika simpul tujuan di temukankembalkan jaraknya
                return distances.get(end);
            }

            if (settled.contains(current)) { // lewati simpul yang selsesai di proses
                continue;
            }
            settled.add(current); //tandai simpul yang sudah di proses


            //perbarui simpul yang belom selsesai di proses
            for (Sisi edge : adjacencyList.getOrDefault(current, Collections.emptyList())) {
                if (!settled.contains(edge.destination)) {
                    int newDistance = distances.get(current) + edge.weight; //hitung jarak baru melalui simpul saat ini
                    if (newDistance < distances.get(edge.destination)) { //perbarui jarak jik lebih pendek
                        distances.put(edge.destination, newDistance);
                        pq.add(new AbstractMap.SimpleEntry<>(edge.destination, newDistance));
                    }
                }
            }
        }

        return distances.get(end); //kembalikan jarak simpul
    }


    //metode utama untuk jalankan program
    public static void main(String[] args) {
        DijkstraGraphTraversal graph = new DijkstraGraphTraversal();
        
    

        graph.addEdge("A", "B", 6);
        graph.addEdge("A", "D", 4);
        graph.addEdge("B", "C", 7);
        graph.addEdge("C", "E", 5);
        graph.addEdge("D", "E", 3);
        graph.addEdge("D", "B", 12);
        graph.addEdge("A", "E", 8);
        graph.addEdge("B", "E", 10);
        
        int distance = graph.dijkstra("A", "E");
        System.out.println("Jarak dari NodeAwal ke NodeAkhir adalah " + distance);
    }
} 
    

