package app;

import model.Barang;
import model.Elektronik;
import model.Lokasi;
import model.PencatatanInventaris;
import model.Perabot;

public class Main {
    public static void main(String[] args) {

        Barang laptopLama = new Barang("01", "Laptop", "rusak");
        Barang proyektorLama = new Barang("02", "Proyektor", "bagus");

        Lokasi ruangGudang = new Lokasi("GDG001", "Gudang");
        Lokasi ruangKelas = new Lokasi("KLS001", "Ruang Kelas 1");

        PencatatanInventaris pencatatanLaptop = new PencatatanInventaris(
                "PCT001", laptopLama, ruangGudang);
        PencatatanInventaris pencatatanProyektor = new PencatatanInventaris(
                "PCT002", proyektorLama, ruangKelas);

        System.out.println("=== DATA PENCATATAN INVENTARIS ===");
        System.out.println("Kode pencatatan: " + pencatatanLaptop.getKodePencatatan());
        System.out.println("Barang: " + laptopLama.getKodeBarang() + " - "
                + laptopLama.getNamaBarang());
        System.out.println("Status: " + laptopLama.getStatusKondisi());
        System.out.println("Lokasi: " + ruangGudang.getKodeLokasi() + " - "
                + ruangGudang.getNamaLokasi());
        System.out.println();

        System.out.println("Kode pencatatan: " + pencatatanProyektor.getKodePencatatan());
        System.out.println("Barang: " + proyektorLama.getKodeBarang() + " - "
                + proyektorLama.getNamaBarang());
        System.out.println("Status: " + proyektorLama.getStatusKondisi());
        System.out.println("Lokasi: " + ruangKelas.getKodeLokasi() + " - "
                + ruangKelas.getNamaLokasi());
        System.out.println();

        System.out.println("=== PERUBAHAN STATUS BARANG ===");
        System.out.println("Status laptop sebelum diperbaiki: "
                + laptopLama.getStatusKondisi());
        laptopLama.perbaiki();
        System.out.println("Status laptop sesudah diperbaiki: "
                + laptopLama.getStatusKondisi());

        System.out.println("\n=== SUBCLASS DIBUAT DARI SUPERCLASS ===");

        Elektronik laptop = new Elektronik(
                "B01", "Laptop ASUS", "rusak", 15_000_000, 8, "ASUS", 24);
        Elektronik proyektor = new Elektronik(
                "B02", "Proyektor Epson", "bagus", 12_000_000, 8, "Epson", 6);
        Perabot meja = new Perabot(
                "B03", "Meja Belajar", "bagus", 2_500_000, 12, "Kayu");
        Perabot kursi = new Perabot(
                "B04", "Kursi Plastik", "rusak", 350_000, 20, "Plastik");

        System.out.println("dari Barang     : "
                + laptop.getKodeBarang() + " / " + laptop.getNamaBarang()
                + " / " + laptop.getStatusKondisi());
        System.out.println("dari Elektronik : "
                + laptop.getMerek() + " / " + laptop.getGaransiBulan() + " bln");
        System.out.println("dari Perabot    : " + meja.getBahan());

        System.out.println("\n=== OVERRIDE getNilaiTaksir() ===");
        System.out.println("Laptop   (Elektronik, 10%/bln): "
                + Barang.formatRupiah(laptop.getNilaiTaksir()));
        System.out.println("Proyektor(Elektronik, 10%/bln): "
                + Barang.formatRupiah(proyektor.getNilaiTaksir()));
        System.out.println("Meja     (Perabot,    2%/bln): "
                + Barang.formatRupiah(meja.getNilaiTaksir()));
        System.out.println("Kursi    (Perabot,    2%/bln): "
                + Barang.formatRupiah(kursi.getNilaiTaksir()));

        System.out.println("\n-- dipanggil lewat tipe Barang[] --");
        Barang[] semuaBarang = { laptop, proyektor, meja, kursi };
        for (Barang b : semuaBarang) {
            System.out.println("  " + b.getJenis() + " -> "
                    + Barang.formatRupiah(b.getNilaiTaksir()));
        }

        System.out.println("\n=== super.getNilaiTaksir() DI DALAM OVERRIDE ===");

        Barang pembanding = new Barang("B01", "Laptop ASUS", "rusak", 15_000_000, 8);

        System.out.println("Laptop  (harga beli " + Barang.formatRupiah(laptop.getHargaBeli())
                + ", umur " + laptop.getUmurBulan() + " bln)");
        System.out.println("  getNilaiTaksir()    : "
                + Barang.formatRupiah(laptop.getNilaiTaksir())
                + "   versi Elektronik (10%/bln)");
        System.out.println("  pembanding Barang    : "
                + Barang.formatRupiah(pembanding.getNilaiTaksir())
                + "   versi Barang (5%/bln), override tidak dipakai");
        System.out.println("  getHargaJualPasar() : "
                + Barang.formatRupiah(laptop.getHargaJualPasar())
                + "   memakai super.getNilaiTaksir() * 0.9");
        System.out.println("  garansi " + laptop.getGaransiBulan() + " bln, umur "
                + laptop.getUmurBulan() + " bln -> "
                + (laptop.masihDigaransi() ? "AKTIF" : "HABIS"));
        System.out.println("  garansi " + proyektor.getGaransiBulan() + " bln, umur "
                + proyektor.getUmurBulan() + " bln -> "
                + (proyektor.masihDigaransi() ? "AKTIF" : "HABIS"));

        System.out.println("\n=== METHOD WARISAN DARI SUBCLASS ===");
        System.out.println(" laptop    -> " + laptop.getKodeBarang()
                + " " + laptop.getNamaBarang()
                + "  [" + laptop.getStatusKondisi() + "]");
        laptop.tandaiRusak();
        System.out.println(" setelah tandaiRusak() : " + laptop.getStatusKondisi());
        laptop.perbaiki();
        System.out.println(" setelah perbaiki()    : " + laptop.getStatusKondisi());
        System.out.println(" (tandaiRusak dan perbaiki tidak ditulis di Elektronik)");

        System.out.println("\n=== ENCAPSULATION ===");
        System.out.println("statusKondisi di Barang private dan tanpa setter.");
        System.out.println("Satu-satunya jalan: tandaiRusak, tandaiHilang, perbaiki.");
        System.out.println("kursi sekarang: " + kursi.getStatusKondisi());
        kursi.tandaiHilang();
        System.out.println("setelah tandaiHilang(): " + kursi.getStatusKondisi());

        System.out.println("\n=== KOMPOSISI: PencatatanInventaris (has-a) ===");
        PencatatanInventaris p1 = new PencatatanInventaris("PCT010", laptop, ruangKelas);
        PencatatanInventaris p2 = new PencatatanInventaris("PCT011", meja, ruangGudang);
        System.out.println(" " + p1.getRingkasan());
        System.out.println(" " + p2.getRingkasan());

        System.out.println("\n=== RINGKASAN OBJECT ===");
        for (Barang b : semuaBarang) {
            System.out.println(" " + b);
        }
    }
}
