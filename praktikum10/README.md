# Praktikum 10 - Collections

## Struktur

```text
praktikum10/
├── Laporan_PBO10.pdf
├── evidence/
│   ├── task1-output.txt
│   ├── task2-output.txt
│   ├── task2-sort-set-error.txt
│   ├── task3-output.txt
│   ├── task3-exception-output.txt
│   └── task3-type-error.txt
├── report/
│   ├── Laporan_PBO10.md
│   ├── Laporan_PBO10.docx
│   └── build_docx.py
└── source/
    ├── task1-student/
    │   ├── Student.java
    │   └── StudentCollectionDemo.java
    ├── task2-set/
    │   └── CountrySetDemo.java
    └── task3-stack/
        ├── ArrayStack.java
        ├── ArrayStackDemo.java
        └── ArrayStackExceptionDemo.java
```

Folder `belajar/` adalah ruang latihan bertahap dan bukan bagian dari pengumpulan.

## Cara menjalankan

Task 1:

```powershell
cd praktikum10/source/task1-student
javac Student.java StudentCollectionDemo.java
java StudentCollectionDemo
```

Task 2:

```powershell
cd praktikum10/source/task2-set
javac CountrySetDemo.java
java CountrySetDemo
```

Task 3:

```powershell
cd praktikum10/source/task3-stack
javac ArrayStack.java ArrayStackDemo.java ArrayStackExceptionDemo.java
java ArrayStackDemo
java ArrayStackExceptionDemo
```

## Ringkasan

- Task 1: `ArrayList<Student>` untuk sorting berdasarkan name, `Vector<Student>` untuk filtering department `Computer Science`. `department` bertipe `Object`, sehingga filter memakai `instanceof`.
- Task 2: `HashSet<String>` menyimpan negara tanpa duplikat. `Collections.sort` hanya menerima `List`, jadi Set disalin ke `ArrayList` sebelum diurutkan.
- Task 3: `ArrayStack<T>` memakai `ArrayList<T>` di dalamnya. Perilaku LIFO, overflow, dan underflow tetap sama.

## Laporan

- Final PDF: `Laporan_PBO10.pdf`
- Source markdown: `report/Laporan_PBO10.md`
- Builder: `report/build_docx.py`
- DOCX editable: `report/Laporan_PBO10.docx`

Gaya laporan: Times New Roman 11, Courier New 9 untuk code, tabel 11, justify, tanpa header, footer, atau nomor halaman.

Membangun ulang DOCX:

```powershell
cd praktikum10/report
python build_docx.py --body Laporan_PBO10.md --out Laporan_PBO10.docx
```

## Bukti

Output dan error tersimpan di `evidence/`.
