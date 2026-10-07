package src.id.ac.polban.pbo.kantin.app;

import src.id.ac.polban.pbo.kantin.model.Pesanan;

public class Kasir {
    public void proses(Pesanan pesanan) {
        if (pesanan.dapatDiproses()) {
            System.out.println("Pesanan nomor " + pesanan.getNomor() + " berhasil diproses.");
        } else {
            System.out.println("Pesanan nomor " + pesanan.getNomor() + " ditolak.");
        }
    }
}