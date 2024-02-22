
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Kelas untuk presentasi objek mahasiswa.
 */
class Mahasiswa {
    private String nim;     // NIM Mahasiswa
    private String nama;     // NAMA Mahasiswa
    private String jurusan;  // JURUSAN Mahasiswa

    /**
     * Konstruktor untuk menginisiasi objek Mahasiswa.
     * 
     *  NIM mahasiswa
     * NAMA Nama mahasiswa
     *  JURUSAN Jurusan mahasiswa
     */
    public Mahasiswa(String nim, String nama, String jurusan) {
        this.nim = nim;
        this.nama = nama;
        this.jurusan = jurusan;
    }

    // Getter NIM(untuk dapatkan NIM Mahasiswa)
    public String getNim() {
        return nim;
    }

    // Getter NAMA (untuk dapatkan nama Mahassiwa)
    public String getNama() {
        return nama;
    }

    // Getter JURUSAN (untuk dapatkan nama Mahasiswa)
    public String getJurusan() {
        return jurusan;
    }

    // Metode untuk kembalikan informasi kepada Mahasiswa dalam bentuk String
    @Override
    public String toString() {
        return "NIM: " + nim + ", Nama: " + nama + ", Jurusan: " + jurusan;
    }
}

/**
 * Kelas utama dalam mengelola data Mahasiswa.
 */
public class searching {

   /**
    * 
    * metode dalam melakukan pencarian data Mahasiswa dengan algoritma squential seacrh
    * menggunakan NIM
    * 
    */
    public static int sequentialSearch(ArrayList<Mahasiswa> list, String nimTarget) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNim().equals(nimTarget)) {
                return i;
            }
        }
        return -1;
    }

    /**
    * 
    * metode dalam melakukan pencarian data Mahasiswa dengan algoritma binary seacrh
    * menggunakan NIM
    * 
    */
    public static int binarySearch(ArrayList<Mahasiswa> list, String nimTarget) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (list.get(mid).getNim().compareTo(nimTarget) < 0) {
                low = mid + 1;
            } else if (list.get(mid).getNim().compareTo(nimTarget) > 0) {
                high = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
    * 
    * metode dalam melakukan pencarian data Mahasiswa menggunakan algoritma interpolation seacrh
    * menggunakan NIM
    * 
    */
    public static int interpolationSearch(ArrayList<Mahasiswa> list, String nimTarget) {
        int low = 0;
        int high = list.size() - 1;
        while (low <= high && nimTarget.compareTo(list.get(low).getNim()) >= 0 && nimTarget.compareTo(list.get(high).getNim()) <= 0) {
            int position = low + (((int) (high - low) / (list.get(high).getNim().compareTo(list.get(low).getNim())))
                                * (nimTarget.compareTo(list.get(low).getNim()) - low));
            if (list.get(position).getNim().compareTo(nimTarget) < 0) {
                low = position + 1;
            } else if (list.get(position).getNim().compareTo(nimTarget) > 0) {
                high = position - 1;
            } else {
                return position;
            }
        }
        return -1;
    }

    /**
     * Metode utamat untuk tampilkan data Mahasiswa
     */
    public static void tampilkanHeaderTabel() {
        System.out.printf("+--------------+---------------------+--------------------+%n");
        System.out.printf("|     NIM      |         Nama        |      Jurusan       |%n");
        System.out.printf("+--------------+---------------------+--------------------+%n");
    }

   //meetode utama menjalakan program data Mahasiswa yang berisi menu dan logika yang menjalankan program.
    public static void main(String[] args) {
        // Membuat objek scanner dalam menerima input pengguna
        Scanner scanner = new Scanner(System.in);

        // Membuat daftar dalam menyimpan semua data Mahasiswa
        ArrayList<Mahasiswa> listMahasiswa = new ArrayList<>();

        boolean continueInput = true;

        // Melakukan Loopi selama pengguna tidak memilih keluar
        while (continueInput) {
            // Menampilkan menu ke pengguna
            System.out.println("Menu:");
            System.out.println("1. Tambah Data Mahasiswa");
            System.out.println("2. Tampilkan Semua Data Mahasiswa");
            System.out.println("3. Hapus Data Mahasiswa");
            System.out.println("4. Pencarian Mahasiswa (Sequential Search)");
            System.out.println("5. Pencarian Mahasiswa (Binary Search)");
            System.out.println("6. Pencarian Mahasiswa (Interpolation Search)");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi (1-7): ");

            // Menerima inputpilihan menudari pengguna
            int choice = scanner.nextInt();
            scanner.nextLine(); // Mengosongkan Buffer

            switch (choice) {
                case 1:
                    // nerima data mahasiswa pengguna dan menambahkannya ke daftar
                    System.out.print("Masukkan NIM: ");
                    String nim = scanner.nextLine();
                    System.out.print("Masukkan Nama: ");
                    String nama = scanner.nextLine();
                    System.out.print("Masukkan Jurusan: ");
                    String jurusan = scanner.nextLine();

                    listMahasiswa.add(new Mahasiswa(nim, nama, jurusan));
                    break;
                case 2:
                    // Menampilkan header tabell dan data mahasiswa dalam format tabel
                    tampilkanHeaderTabel();
                    for (Mahasiswa mhs : listMahasiswa) {
                        System.out.printf("| %-12s | %-19s | %-18s |%n", mhs.getNim(), mhs.getNama(), mhs.getJurusan());
                    }
                    System.out.printf("+--------------+---------------------+--------------------+%n");
                    break;

                case 3:
                    // case dalam menghapus data mahasiswa dasarkan indeks
                    System.out.print("Masukkan indeks data mahasiswa yang ingin dihapus: ");
                    int index = scanner.nextInt();
                    if (index >= 0 && index < listMahasiswa.size()) {
                        listMahasiswa.remove(index);
                        System.out.println("Data mahasiswa pada indeks " + index + " telah dihapus.");
                    } else {
                        System.out.println("Indeks tidak valid.");
                    }
                    break;
                case 4:
                    // Menerima NIM dari pengguna juga mencari nim gunakan metode squential seacrh yang telah di buat
                    System.out.print("Masukkan NIM yang dicari: ");
                    String nimSeq = scanner.nextLine();
                    int indexSeq = sequentialSearch(listMahasiswa, nimSeq);
                    if (indexSeq != -1) {
                        System.out.println("Data ditemukan pada indeks " + indexSeq);
                        System.out.println(listMahasiswa.get(indexSeq));
                    } else {
                        System.out.println("Data dengan NIM " + nimSeq + " tidak ditemukan.");
                    }
                    break;

                case 5:
                    // Menerima NIM dari pengguna juga mencari nim gunakan metode binary seacrh yang telah di buat
                    System.out.print("Masukkan NIM yang dicari: ");
                    String nimBin = scanner.nextLine();
                    int indexBin = binarySearch(listMahasiswa, nimBin);
                    if (indexBin != -1) {
                        System.out.println("Data ditemukan pada indeks " + indexBin);
                        System.out.println(listMahasiswa.get(indexBin));
                    } else {
                        System.out.println("Data dengan NIM " + nimBin + " tidak ditemukan.");
                    }
                    break;

                case 6:
                    // Menerima NIM dari pengguna juga mencari nim gunakan metode interpolation seacrh yang telah di buat
                    System.out.print("Masukkan NIM yang dicari: ");
                    String nimInt = scanner.nextLine();
                    int indexInt = interpolationSearch(listMahasiswa, nimInt);
                    if (indexInt != -1) {
                        System.out.println("Data ditemukan pada indeks " + indexInt);
                        System.out.println(listMahasiswa.get(indexInt));
                    } else {
                        System.out.println("Data dengan NIM " + nimInt + " tidak ditemukan.");
                    }
                    break;
                case 7:
                    // hentikann perulangan dan keluar dari pemograman
                    continueInput = false;
                    break;
                default:
                    // Menampilkan tanda pesan kesalahan ketika pilihan tidak valid
                    System.out.println("Pilihan tidak valid.");
            }
        }
        scanner.close();
        // Menampilkan tanda pesan keluar
        System.out.println("Terima kasih anda sudah menggunakan program ini.");
    }
}