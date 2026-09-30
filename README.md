# Praktikum Pemrograman Berorientasi Objek

D3 Teknik Informatika - Politeknik Negeri Bandung

Kumpulan source code dan laporan praktikum PBO.
Satu repo untuk semua pertemuan, dipisahkan per folder minggu.

| | |
|---|---|
| **Nama** | Ananda Firdaus |
| **NIM** | 251511003 |
| **Kelas** | 4KA / Group 03 |

---

## Navigasi

Pilih minggu yang kamu cari:

| Pertemuan | Topik | Buka folder ini | Laporan |
|---|---|---|---|
| **Week 3** | Class and Object - Studi Kasus Kantin | [`Week 3/`](Week%203/) | - |
| **Week 4** | Inheritance, Generalization & Overriding | [`251511003_Ananda Firdaus_PBO4/`](251511003_Ananda%20Firdaus_PBO4/) | [`Laporan_PBO4.pdf`](251511003_Ananda%20Firdaus_PBO4/Laporan_PBO4.pdf) |

---

## Week 3 - Class and Object

**Studi kasus:** aplikasi kantin kampus. Menguji pemesanan menu dan proses kasir.

### Isi folder

```
Week 3/
  src/id/ac/polban/pbo/kantin/
    app/
      Main.java        - driver pengujian
      Kasir.java       - memproses pesanan (uses-a)
    model/
      Mahasiswa.java   - nim, nama
      MenuItem.java    - kode, nama, harga, status tersedia
      Pesanan.java     - nomor urut otomatis, pemesan, menu, jumlah
```

### Cara menjalankan

```powershell
javac -d out (Get-ChildItem "Week 3" -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out src.id.ac.polban.pbo.kantin.app.Main
```

> Nama package diawali `src.` (`src.id.ac.polban.pbo.kantin.app`) karena
> folder `src/` ikut menjadi bagian dari nama package. Karena itu class
> principalnya ditulis `src.id...Main`, bukan `id.ac...Main`.

### Konsep yang dipraktikkan

Encapsulation (field `private` + getter), constructor, static counter
`nextNumber` untuk nomor pesanan otomatis, dan hubungan *uses-a* antara
`Kasir` dan `Pesanan`.

---

## Week 4 - Inheritance, Generalization & Overriding

**Laporan lengkap:** [`Laporan_PBO4.pdf`](251511003_Ananda%20Firdaus_PBO4/Laporan_PBO4.pdf) (35 halaman)

### Isi folder

```
251511003_Ananda Firdaus_PBO4/
  Laporan_PBO4.pdf
  source/
    praktikum 1/                - Circle menjadi Cylinder
      Circle.java               - superclass
      Cylinder.java             - subclass
      TestCircle.java
      TestCylinder.java
    praktikum 2/                - Shape hierarchy
      Shape.java                - superclass
      Circle.java               - subclass
      Rectangle.java            - subclass
      Square.java               - subclass dari Rectangle
      TestShape.java
    kasus-sebelumnya/           - refactor tugas inventaris
      app/
        Main.java
      model/
        Barang.java             - superclass
        Elektronik.java         - subclass
        Perabot.java            - subclass
        Lokasi.java
        PencatatanInventaris.java
```

> **Kenapa `praktikum 1` dan `praktikum 2` dipisah?**
> Keduanya punya class `Circle` yang isinya **beda**. Yang di Praktikum 1
> `extends Object` dan punya `color`; yang di Praktikum 2 `extends Shape`
> dan tidak punya `color`. Kalau digabung dalam satu folder, satu akan
> menimpa yang lain. Modul 2.1: *"Jangan mencampur file dengan package
> berbeda dalam satu folder source."*

### Cara menjalankan

Ketiga folder di bawah bisa dijalankan terpisah (satu punya `package`, dua
lainnya default package).

**Praktikum 1 - folder `praktikum 1/`**

```powershell
cd "251511003_Ananda Firdaus_PBO4/source/praktikum 1"
javac -d out *.java
java -cp out TestCylinder
```

**Praktikum 2 - folder `praktikum 2/`**

```powershell
cd "251511003_Ananda Firdaus_PBO4/source/praktikum 2"
javac -d out *.java
java -cp out TestShape
```

**Studi kasus inventaris - folder `kasus-sebelumnya/`**

```powershell
cd "251511003_Ananda Firdaus_PBO4/source/kasus-sebelumnya"
javac -d out model/*.java app/*.java
java -cp out app.Main
```

### Konsep yang dipraktikkan

| Konsep | Ditemukan di |
|---|---|
| `extends`, constructor chain (`super(...)`) | Praktikum 1 |
| Method warisan tanpa copy-paste | Praktikum 1 |
| **Bug**: override `getArea()` membuat `getVolume()` salah 12 kali lipat | Praktikum 1 |
| Perbaikan dengan `super.getArea()` | Praktikum 1 |
| `@Override` menangkap typo saat compile | Praktikum 1 |
| Generalization: menarik state/behavior umum ke atas | Praktikum 2 |
| **Bug**: `Square.setWidth(8)` menghasilkan 8 x 5 | Praktikum 2 |
| Override setter menjaga invarian `width == length` | Praktikum 2 |
| `super.method()` pada override | Studi kasus |
| Komposisi vs inheritance (`PencatatanInventaris`) | Studi kasus |

### Temuan menarik

Tiga bug yang ditemukan semuanya **diam-diam**. Program jalan normal, angka
yang tercetak terlihat wajar, dan compiler tidak protoksi sedikit pun. Tidak
satu pun ketahuan tanpa membandingkan prediksi dengan output.

- `super(radius)` dihapus -> `radius` jatuh ke default `1.0`, tanpa error
- `getArea()` di-override -> `getVolume()` dihitung dari luas permukaan
- `Square` kehilangan sifat persegi -> objek 8 x 5 yang sahih secara hitungan

---

## Struktur Folder

Struktur Week 4 mengikuti modul 6.3:

```
NIM_Nama_PBO4
  Laporan_PBO4.pdf
  source/
```

Deviasi dari modul: modul menuliskan `source/` datar, sedangkan repo ini
memecahnya menjadi `praktikum 1/`, `praktikum 2/`, dan `kasus-sebelumnya/`.
Alasannya dijelaskan di atas.
