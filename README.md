# Praktikum Pemrograman Berorientasi Objek

D3 Teknik Informatika - Politeknik Negeri Bandung

Kumpulan source code dan laporan praktikum PBO.
Satu repo untuk semua pertemuan, dipisahkan per folder praktikum.

| | |
|---|---|
| **Nama** | Ananda Firdaus |
| **NIM** | 251511003 |
| **Kelas** | 2A |

---

## Navigasi

| Pertemuan | Topik | Folder | Laporan |
|---|---|---|---|
| **Praktikum 3** | Class and Object - Studi Kasus Kantin | [`praktikum3/`](praktikum3/) | - |
| **Praktikum 4** | Inheritance, Generalization & Overriding | [`praktikum4/`](praktikum4/) | [`Laporan_PBO4.pdf`](praktikum4/Laporan_PBO4.pdf) |
| **Praktikum 5** | Abstract Class & Interface | [`praktikum5/`](praktikum5/) | [`Laporan_PBO5.pdf`](praktikum5/laporan/251511003_Ananda%20Firdaus_Laporan_PBO5.pdf) |

---

## Praktikum 3 - Class and Object

**Studi kasus:** aplikasi kantin kampus. Menguji pemesanan menu dan proses kasir.

### Isi folder

```
praktikum3/
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
javac -d out (Get-ChildItem "praktikum3" -Recurse -Filter *.java | ForEach-Object { $_.FullName })
java -cp out src.id.ac.polban.pbo.kantin.app.Main
```

> Nama package diawali `src.` (`src.id.ac.polban.pbo.kantin.app`) karena
> folder `src/` ikut menjadi bagian dari nama package. Karena itu class
> utamanya ditulis `src.id...Main`, bukan `id.ac...Main`.

### Konsep yang dipraktikkan

Encapsulation (field `private` + getter), constructor, static counter
`nextNumber` untuk nomor pesanan otomatis, dan hubungan *uses-a* antara
`Kasir` dan `Pesanan`.

---

## Praktikum 4 - Inheritance, Generalization & Overriding

**Laporan:** [`Laporan_PBO4.pdf`](praktikum4/Laporan_PBO4.pdf)

### Isi folder

```
praktikum4/
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

**Praktikum 1 - folder `praktikum 1/`**

```powershell
cd "praktikum4/source/praktikum 1"
javac -d out *.java
java -cp out TestCylinder
```

**Praktikum 2 - folder `praktikum 2/`**

```powershell
cd "praktikum4/source/praktikum 2"
javac -d out *.java
java -cp out TestShape
```

**Studi kasus inventaris - folder `kasus-sebelumnya/`**

```powershell
cd "praktikum4/source/kasus-sebelumnya"
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

## Praktikum 5 - Abstract Class & Interface

**Laporan:** [`Laporan_PBO5.pdf`](praktikum5/laporan/251511003_Ananda%20Firdaus_Laporan_PBO5.pdf)

### Isi folder

```
praktikum5/
  laporan/
    251511003_Ananda Firdaus_Laporan_PBO5.pdf
  source/
    Sortable.java        - abstract class, kewajiban compare()
    Employee.java        - extends Sortable, implements compare()
    Manager.java         - extends Employee, override raiseSalary()
    EmployeeTest.java
    Goods.java           - superclass
    Food.java            - extends Goods (tanpa Taxable)
    Taxable.java         - interface, kontrak calculateTax()
    Toy.java             - extends Goods implements Taxable
    Book.java            - extends Goods implements Taxable
    GoodsTest.java
```

### Cara menjalankan

```powershell
cd "praktikum5/source"
javac -d out *.java
java -cp out EmployeeTest
java -cp out GoodsTest
```

### Dua hierarchy dalam praktikum ini

```
Sortable  <<abstract>>            Goods
    |                          /    |    \
 Employee                   Food   Toy   Book
    |                              \    /
 Manager                           Taxable  <<interface>>
```

> Perhatikan: `Food` **tidak** implements `Taxable`, sedangkan `Toy` dan
> `Book` implements. Ini sengaja supaya terlihat bahwa interface berguna
> justru untuk kemampuan yang **tidak** dimiliki semua anak.

### Konsep yang dipraktikkan

| Konsep | Ditemukan di |
|---|---|
| `abstract class` tidak bisa diinstansiasi | Step 5, 12 |
| `abstract method` wajib diimplementasikan subclass | Step 6, 16 |
| **Inheritance chain** - Manager dapat `compare()` tanpa menulisnya | Step 8 |
| Java menolak `extends` lebih dari satu | Step 8 |
| `interface` sebagai kontrak | Step 12 |
| `extends` + `implements` pada class yang sama | Step 13, 14 |
| Casting `(Employee) other` | Step 6 |
| Interface hanya boleh konstanta (`public static final`) | Step 12 |
| Komposisi vs inheritance tetap dijaga | Bagian sebelumnya |

### Bukti error yang dikumpulkan

| Error | Membuktikan |
|---|---|
| `Sortable is abstract; cannot be instantiated` | abstract class tidak bisa di-`new` |
| `Employee is not abstract and does not override abstract method compare(Sortable) in Sortable` | abstract method wajib diisi |
| `'{' expected` pada `extends Employee extends Sortable` | Java hanya boleh 1 parent class |
| `Taxable is abstract; cannot be instantiated` | interface juga tidak bisa di-`new` |
| `Toy is not abstract and does not override abstract method calculateTax() in Taxable` | `implements` adalah kontrak yang ditegakkan |
| `Food cannot be converted to Taxable` | yang tidak berjanji tidak bisa menyamar |
| `cannot assign a value to static final variable taxRate` | field interface otomatis konstanta |

Seluruh 8 skenario uji T5-01 s.d. T5-08 **PASS**.

---

## Catatan

- File `.class` dan folder hasil compile (`out/`, `bin/`) diabaikan lewat
  `.gitignore`.
- Laporan tiap praktikum ada di dalam folder praktikumnya masing-masing.
