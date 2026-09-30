package model;

public class Elektronik extends Barang {
    private String merek;
    private int garansiBulan;

    public Elektronik(String kodeBarang, String namaBarang, String statusKondisi,
                      double hargaBeli, int umurBulan, String merek, int garansiBulan) {
        super(kodeBarang, namaBarang, statusKondisi, hargaBeli, umurBulan);
        this.merek = merek;
        this.garansiBulan = garansiBulan;
    }

    public String getMerek() { return merek; }
    public int getGaransiBulan() { return garansiBulan; }

    @Override
    public double getNilaiTaksir() {
        return Math.max(0.0, getHargaBeli() * (1 - 0.10 * getUmurBulan()));
    }

    @Override
    public String getJenis() { return "Elektronik"; }

    public double getHargaJualPasar() {
        return super.getNilaiTaksir() * 0.9;
    }

    public boolean masihDigaransi() {
        return getUmurBulan() <= garansiBulan;
    }

    @Override
    public String toString() {
        return getJenis() + " " + super.toString()
                + " (" + merek + ", garansi " + garansiBulan + " bln"
                + (masihDigaransi() ? ", aktif" : ", habis") + ")";
    }
}