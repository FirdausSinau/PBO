# Praktikum 11 - Java GUI dengan Swing

## Struktur

```text
praktikum11/
├── Laporan_PBO11.pdf
├── README.md
└── source/
    ├── ComponentDemo.java
    ├── Product.java
    ├── CartItem.java
    ├── POSFrame.java
    └── Main.java
```

Folder `evidence/` dan `report/` tetap ada di lokal tetapi tidak di-push.

## Cara menjalankan

Masuk ke folder `source`, lalu compile semua file:

```powershell
cd praktikum11/source
javac *.java
```

Menjalankan aplikasi POS:

```powershell
java Main
```

Menjalankan demo komponen:

```powershell
java ComponentDemo
```

## Ringkasan

- `ComponentDemo` menampilkan JLabel, JTextField, JComboBox, JCheckBox, JRadioButton dengan ButtonGroup, JTextArea di dalam JScrollPane, dan JButton. Tombol memvalidasi nama kosong lewat JOptionPane.
- `Product` menyimpan id, nama, dan harga.
- `CartItem` menyimpan product dan qty, memvalidasi qty lebih dari 0, dan menghitung `getSubtotal()`.
- `POSFrame` adalah subclass JFrame. Tampilan memakai BorderLayout, GridLayout(1, 2), dan FlowLayout tanpa `setBounds`.
- `Main` hanya membuat `POSFrame` di dalam `SwingUtilities.invokeLater`.

## Fitur POS

- Tabel produk berisi lima produk.
- Klik baris produk menampilkan info produk terpilih.
- Qty divalidasi: harus angka dan lebih dari 0.
- Add to Cart menambah `CartItem` ke `List<CartItem>` dan menyegarkan tabel keranjang.
- Total dan points dihitung dari seluruh subtotal. Points memakai pembagian integer `total / 1000`.
- Checkout menolak keranjang kosong, membuat struk sebelum mengosongkan keranjang, lalu mereset total dan points.
- Cetak memanggil `receiptArea.print()` dan menangani `PrinterException`.
- Menu File -> Exit dan Help -> About berfungsi.

## Hasil uji

| ID | Skenario | Hasil |
|---|---|---|
| T11-01 | Program dijalankan | Window tampil, tabel Produk berisi 5 data |
| T11-02 | Klik satu produk | Info produk terpilih tampil di label |
| T11-03 | Add to Cart Qty 1 | Item masuk tabel keranjang, subtotal benar |
| T11-04 | Qty kosong atau tidak valid | Operasi ditolak, dialog peringatan tampil |
| T11-05 | P003 Qty 1 + P004 Qty 1 | Total Rp14.000, Points 14 |
| T11-06 | Checkout | Struk tampil, keranjang kosong, total dan points reset |
| T11-07 | Cetak | Dialog print sistem operasi terbuka |
| T11-08 | File -> Exit dan Help -> About | Menu menjalankan fungsi yang sesuai |

## Bukti

Screenshot dan log pengujian disimpan di `praktikum11/evidence/` dan sudah dimasukkan ke laporan.
