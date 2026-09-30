package src.id.ac.polban.pbo.kantin.app;

import src.id.ac.polban.pbo.kantin.model.Mahasiswa;
import src.id.ac.polban.pbo.kantin.model.MenuItem;
import src.id.ac.polban.pbo.kantin.model.Pesanan;

public class Main {
    public static void main(String[] args) {
        Mahasiswa m1 = new Mahasiswa("241001", "Asep");
        Mahasiswa m2 = new Mahasiswa("241002", "Siti");
        

        MenuItem nasi = new MenuItem("M01", "Nasi Goreng", 18000);
        MenuItem kopi = new MenuItem("M02", "Kopi Susu", 12000);

        kopi.tandaiHabis();

        Pesanan p1 = new Pesanan(m1, nasi, 2);
        Pesanan p2 = new Pesanan(m2, kopi, 1);
        Pesanan p3 = new Pesanan(m1, nasi, 1);
        Pesanan p4 = new Pesanan(m2, nasi, 0);

        System.out.println("--- PENGUJIAN PESANAN ---");
        System.out.println("P1 (Menu Tersedia, Jml 2) -> Dapat Diproses: " + p1.dapatDiproses() + " | Total: " + p1.hitungTotal());
        System.out.println("P2 (Menu Habis, Jml 1)     -> Dapat Diproses: " + p2.dapatDiproses());
        System.out.println("P3 (Menu Tersedia, Jml 1) -> Dapat Diproses: " + p3.dapatDiproses() + " | Total: " + p3.hitungTotal());
        System.out.println("P4 (Jumlah 0)              -> Dapat Diproses: " + p4.dapatDiproses());
        System.out.println("Total Pesanan Dibuat: " + Pesanan.getJumlahPesananDibuat());
        
        System.out.println("\n--- PENGUJIAN KASIR (USES-A) ---");
        Kasir kasir = new Kasir();
        kasir.proses(p1);
        kasir.proses(p2);
        kasir.proses(p4);
    }
}