# Praktikum 10 - Collections

## Struktur

```text
praktikum10/
├── Laporan_PBO10.pdf
├── README.md
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

Folder `evidence/`, `report/`, dan `belajar/` tetap ada di lokal tetapi tidak di-push.

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

Laporan final tersedia di `Laporan_PBO10.pdf`. Source DOCX, markdown, dan builder disimpan lokal di `report/` dan tidak di-push.

## Bukti

Output dan error disimpan lokal di `evidence/` dan sudah dimasukkan ke laporan.
