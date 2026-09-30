package model;

public class PencatatanInventaris {
    private String kodePencatatan;
    private Barang barang;
    private Lokasi lokasi;

    public PencatatanInventaris(String kodePencatatan, Barang barang, Lokasi lokasi) {
        this.kodePencatatan = kodePencatatan;
        this.barang = barang;
        this.lokasi = lokasi;
    }

    public String getKodePencatatan() { return kodePencatatan; }
    public Barang getBarang() { return barang; }
    public Lokasi getLokasi() { return lokasi; }

    public String getRingkasan() {
        return kodePencatatan + " -> " + barang
                + " di " + lokasi.getKodeLokasi() + " " + lokasi.getNamaLokasi();
    }
}