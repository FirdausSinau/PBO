package src.id.ac.polban.pbo.kantin.model;

public class MenuItem {
    private String kode;
    private String nama;
    private int harga;
    private boolean tersedia;

    public MenuItem(String kode, String nama, int harga) {
        this.kode = kode;
        this.nama = nama;
        this.harga = harga;
        this.tersedia = true;
    }

    public String getKode() {
        return kode;
    }

    public String getNama() {
        return nama;
    }

    public int getHarga() {
        return harga;
    }

    public boolean isTersedia() {
        return tersedia;
    }

    public void tandaiHabis() {
        this.tersedia = false;
    }
}