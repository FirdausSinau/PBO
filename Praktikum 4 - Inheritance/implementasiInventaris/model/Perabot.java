package model;

public class Perabot extends Barang {
    private String bahan;

    public Perabot(String kodeBarang, String namaBarang, String statusKondisi,
                   double hargaBeli, int umurBulan, String bahan) {
        super(kodeBarang, namaBarang, statusKondisi, hargaBeli, umurBulan);
        this.bahan = bahan;
    }

    public String getBahan() { return bahan; }

    @Override
    public double getNilaiTaksir() {
        return Math.max(0.0, getHargaBeli() * (1 - 0.02 * getUmurBulan()));
    }

    @Override
    public String getJenis() { return "Perabot"; }

    @Override
    public String toString() {
        return getJenis() + " " + super.toString() + " (" + bahan + ")";
    }
}