package model;

public class Barang {
    private String kodeBarang;
    private String namaBarang;
    private String statusKondisi;
    private double hargaBeli;
    private int umurBulan;

    public Barang(String kodeBarang, String namaBarang, String statusKondisi) {
        this(kodeBarang, namaBarang, statusKondisi, 0.0, 0);
    }

    public Barang(String kodeBarang, String namaBarang, String statusKondisi,
                  double hargaBeli, int umurBulan) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.statusKondisi = statusKondisi;
        this.hargaBeli = hargaBeli;
        this.umurBulan = umurBulan;
    }

    public String getKodeBarang() { return kodeBarang; }
    public String getNamaBarang() { return namaBarang; }
    public String getStatusKondisi() { return statusKondisi; }
    public double getHargaBeli() { return hargaBeli; }
    public int getUmurBulan() { return umurBulan; }

    public void tandaiRusak()  { this.statusKondisi = "rusak"; }
    public void tandaiHilang() { this.statusKondisi = "hilang"; }
    public void perbaiki()     { this.statusKondisi = "bagus"; }

    public double getNilaiTaksir() {
        return Math.max(0.0, hargaBeli * (1 - 0.05 * umurBulan));
    }

    public String getJenis() { return "Barang"; }

    public static String formatRupiah(double nilai) {
        return "Rp" + String.format("%,.0f", nilai);
    }

    @Override
    public String toString() {
        return getKodeBarang() + " " + getNamaBarang()
                + " [" + getStatusKondisi() + ", " + formatRupiah(getHargaBeli()) + "]";
    }
}