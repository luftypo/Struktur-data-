import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class ProgramBFS {

    // Nilai tiap node: index 0..7 mewakili a1..a8
    // a8 bernilai 7 (2 digit NIM akhir = 07)
    static int[] nilai = {12, 5, 19, 3, 44, 60, 21, 7};

    static ArrayList<ArrayList<Integer>> graf = new ArrayList<ArrayList<Integer>>();

    static String nama(int i) {
        return "a" + (i + 1);
    }

    // Graf tidak berarah: sisi dicatat di kedua node
    static void tambahSisi(int a, int b) {
        graf.get(a).add(b);
        graf.get(b).add(a);
    }

    static String isiAntrean(Queue<Integer> antrean) {
        String hasil = "[";
        for (int n : antrean) {
            hasil += nama(n) + " ";
        }
        return hasil.trim() + "]";
    }

    static void bfs(int awal, int target) {
        boolean[] dikunjungi = new boolean[8];
        Queue<Integer> antrean = new LinkedList<Integer>();
        int langkah = 0;

        dikunjungi[awal] = true;
        antrean.add(awal);

        while (!antrean.isEmpty()) {
            int node = antrean.poll();
            langkah++;
            System.out.println("Langkah " + langkah + ": ambil " + nama(node)
                    + " (nilai = " + nilai[node] + ")");

            if (nilai[node] == target) {
                System.out.println(">> Angka " + target + " DITEMUKAN di node " + nama(node));
                return;
            }

            for (int tetangga : graf.get(node)) {
                if (!dikunjungi[tetangga]) {
                    dikunjungi[tetangga] = true;
                    antrean.add(tetangga);
                    System.out.println("   masukkan " + nama(tetangga) + " ke antrean");
                }
            }
            System.out.println("   isi antrean: " + isiAntrean(antrean));
        }

        System.out.println(">> Angka " + target + " TIDAK ditemukan pada graf");
    }

    public static void main(String[] args) {
        for (int i = 0; i < 8; i++) {
            graf.add(new ArrayList<Integer>());
        }

        // Sisi graf: (a1-a2), (a1-a3), (a2-a4), (a2-a5), (a3-a6), (a3-a7), (a5-a8), (a6-a8)
        tambahSisi(0, 1);
        tambahSisi(0, 2);
        tambahSisi(1, 3);
        tambahSisi(1, 4);
        tambahSisi(2, 5);
        tambahSisi(2, 6);
        tambahSisi(4, 7);
        tambahSisi(5, 7);

        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan angka n yang dicari: ");
        int target = input.nextInt();

        System.out.println("=== Pencarian BFS mulai dari a1, mencari n = " + target + " ===");
        bfs(0, target);
    }
}