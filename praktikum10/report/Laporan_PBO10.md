@center 16 LAPORAN PRAKTIKUM PEMROGRAMAN BERORIENTASI OBJEK
@center 14 Praktikum 10: Collections
@center 11 
@center 14 Disusun Oleh:
@center 14 Ananda Firdaus (251511003)
@center 14 Kelas 2A
@center 11 
@center 14 Jurusan Teknik Komputer dan Informatika
@center 14 Program Studi D-3 Teknik Informatika
@center 14 Politeknik Negeri Bandung
@center 14 2026

\pagebreak

Repo Source Code: https://github.com/FirdausSinau/PBO/tree/main/praktikum10

# A. Pendahuluan

Praktikum 10 membahas Java Collections. Fokus praktikum adalah memilih struktur data berdasarkan kebutuhan: urutan, duplikat, index, sorting, dan keunikan. Laporan ini memuat Task 1 tentang Student, ArrayList, dan Vector; Task 2 tentang List, Set, dan HashSet; Task 3 tentang generic stack dengan ArrayList; serta jawaban checkpoint dan refleksi. Seluruh output diambil dari eksekusi program yang sebenarnya.

# B. Task 1: Student, ArrayList, dan Vector

## B.1 Class Student

```java
public class Student {
    private String studentID;
    private String name;
    private Object department;
    private String address;

    public Student(String studentID, String name,
                   Object department, String address) {
        this.studentID = studentID;
        this.name = name;
        this.department = department;
        this.address = address;
    }

    public String getStudentID() { return studentID; }
    public String getName() { return name; }
    public Object getDepartment() { return department; }
    public String getAddress() { return address; }

    @Override
    public String toString() {
        return studentID + " | " + name + " | "
            + department + " | " + address;
    }
}
```

~ Kode class Student.

Class Student menerapkan encapsulation: seluruh field private dan hanya diakses melalui getter. Field department bertipe Object agar dapat menampung String maupun Integer sesuai requirement.

## B.2 ArrayList dan Sorting

```java
ArrayList<Student> students = new ArrayList<>();

students.add(new Student("S001", "Rina",
    "Computer Science", "Bandung"));
students.add(new Student("S002", "Andi", 10, "Cimahi"));
students.add(new Student("S003", "Dewi",
    "Information Systems", "Garut"));
students.add(new Student("S004", "Budi",
    "Computer Science", "Bandung"));
students.add(new Student("S005", "Citra", 20, "Sumedang"));

Collections.sort(
    students,
    Comparator.comparing(Student::getName)
);
```

~ Kode ArrayList dan sorting berdasarkan name.

Output sebelum dan sesudah sorting:

```text
Sebelum sort:
S001 | Rina | Computer Science | Bandung
S002 | Andi | 10 | Cimahi
S003 | Dewi | Information Systems | Garut
S004 | Budi | Computer Science | Bandung
S005 | Citra | 20 | Sumedang

Setelah sort by name:
S002 | Andi | 10 | Cimahi
S004 | Budi | Computer Science | Bandung
S005 | Citra | 20 | Sumedang
S003 | Dewi | Information Systems | Garut
S001 | Rina | Computer Science | Bandung
```

Sorting memakai Comparator.comparing(Student::getName) karena Student tidak mengimplementasikan Comparable. Sorting hanya mengubah urutan reference di dalam ArrayList, bukan isi object. Setelah sorting, penambahan S006 Eka di akhir tidak otomatis terurut. get(0) mengembalikan Andi dan remove(0) menghapus Andi sehingga size turun dari 6 menjadi 5.

## B.3 Vector dan Filtering

```java
Vector<Student> vector = new Vector<>();

vector.add(new Student("V001", "Alya",
    "Computer Science", "Bandung"));
vector.add(new Student("V002", "Raka",
    "Accounting", "Cimahi"));
vector.add(new Student("V003", "Sinta",
    "Computer Science", "Bandung"));
vector.add(new Student("V004", "Dimas",
    "Mechanical Engineering", "Garut"));
vector.add(new Student("V005", "Nadia",
    "Computer Science", "Sumedang"));

System.out.println("Computer Science:");
for (Student s : vector) {
    if (s.getDepartment() instanceof String
            && ((String) s.getDepartment())
                .equalsIgnoreCase("Computer Science")) {
        System.out.println(s);
    }
}
```

~ Kode Vector dan filter department Computer Science.

Output filter:

```text
Computer Science:
V001 | Alya | Computer Science | Bandung
V003 | Sinta | Computer Science | Bandung
V005 | Nadia | Computer Science | Sumedang
```

Vector adalah implementasi List legacy yang method-nya synchronized. Untuk tugas ini perilakunya sama seperti ArrayList. Filter memakai instanceof sebelum cast karena department bertipe Object. Tanpa pengecekan, department Integer seperti 10 atau 20 dapat menyebabkan ClassCastException. Hanya Alya, Sinta, dan Nadia yang lolos filter.

# C. Task 2: List, Set, dan HashSet

## C.1 List vs Set

List adalah collection berurutan yang boleh berisi duplikat dan memiliki index. Set tidak menyimpan duplikat, tidak memiliki index, dan tidak menjamin urutan. Untuk mencatat frekuensi kombinasi dua dadu, Set tidak cocok karena duplikat dibuang; dibutuhkan struktur penghitung seperti Map.

## C.2 HashSet dan Duplikasi

```java
HashSet<String> countries = new HashSet<>();

countries.add("Indonesia");
countries.add("Japan");
countries.add("Malaysia");
countries.add("Singapore");
countries.add("Thailand");
countries.add("Indonesia"); // duplikat

System.out.println(countries);
System.out.println("Jumlah elemen = " + countries.size());
```

~ Kode HashSet dengan satu duplikat.

Output:

```text
[Singapore, Japan, Malaysia, Thailand, Indonesia]
Jumlah elemen = 5
```

Enam kali add menghasilkan size 5 karena "Indonesia" duplikat. Urutan output HashSet bukan kontrak program; urutan bergantung pada hashCode dan kapasitas. Operasi contains dan remove dijalankan dengan kode berikut:

```java
System.out.println(countries.contains("Indonesia"));
countries.remove("Japan");
System.out.println(countries);
System.out.println(countries.size());
```

~ Kode contains, remove, dan size pada HashSet.

Output:

```text
true
[Singapore, Malaysia, Thailand, Indonesia]
4
```

contains("Indonesia") bernilai true. Setelah remove("Japan"), size menjadi 4.

## C.3 Sorting Set dan List

```java
ArrayList<String> countriesList = new ArrayList<String>();
countriesList.add("Japan");
countriesList.add("Indonesia");
countriesList.add("Malaysia");

Collections.sort(countriesList);
System.out.println(countriesList);

// Collections.sort(countries); // compile error
```

~ Kode sorting ArrayList dan percobaan sorting Set.

Output sorting List:

```text
[Indonesia, Japan, Malaysia]
```

Error saat Collections.sort dipanggil pada HashSet:

```text
SortSetError.java:8: error: no suitable method found for sort(HashSet<String>)
        Collections.sort(countriesSet);
                   ^
    method Collections.<T#1>sort(List<T#1>) is not applicable
      (cannot infer type-variable(s) T#1
        (argument mismatch; HashSet<String> cannot be converted to List<T#1>))
    method Collections.<T#2>sort(List<T#2>,Comparator<? super T#2>) is not applicable
      (cannot infer type-variable(s) T#2
        (actual and formal argument lists differ in length))
  where T#1,T#2 are type-variables:
    T#1 extends Comparable<? super T#1> declared in method <T#1>sort(List<T#1>)
    T#2 extends Object declared in method <T#2>sort(List<T#2>,Comparator<? super T#2>)
1 error
```

Collections.sort hanya menerima List. List memiliki urutan dan index sehingga elemen dapat ditukar. Set tidak memiliki index dan tidak menjamin urutan; HashSet bukan List. Untuk menampilkan data Set terurut, isi Set disalin ke ArrayList lalu diurutkan.

```java
ArrayList<String> sortedCountries =
    new ArrayList<>(countries);
Collections.sort(sortedCountries);
System.out.println(sortedCountries);
```

~ Kode menyalin Set ke ArrayList dan sorting.

Output:

```text
[Indonesia, Malaysia, Singapore, Thailand]
```

Set tetap Set. List hanya salinan untuk tampilan terurut.

# D. Task 3: Generic Stack dengan ArrayList

## D.1 ArrayStack<T>

```java
import java.util.ArrayList;

public class ArrayStack<T> {
    private final int maxsize;
    private final ArrayList<T> items;

    public ArrayStack(int maxsize) {
        if (maxsize <= 0)
            throw new ArrayStackException(
                "Stack size must be positive");
        this.maxsize = maxsize;
        this.items = new ArrayList<>();
    }

    public void push(T item) {
        if (items.size() == maxsize)
            throw new ArrayStackException("Overflow Error");
        items.add(item);
    }

    public T pop() {
        if (isEmpty())
            throw new ArrayStackException("Underflow Error");
        return items.remove(items.size() - 1);
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    public static class ArrayStackException
            extends RuntimeException {
        public ArrayStackException(String message) {
            super(message);
        }
    }
}
```

~ Kode ArrayStack<T> dengan ArrayList<T>.

ArrayStack<T> menyimpan elemen di ArrayList<T>. Field top tidak diperlukan karena posisi teratas adalah items.size() - 1. Kapasitas maksimum tetap dijaga melalui maxsize. Perilaku LIFO, push, pop, isEmpty, overflow, dan underflow tidak berubah.

## D.2 Uji Integer dan String

```java
ArrayStack<Integer> numbers = new ArrayStack<>(3);
numbers.push(1);
numbers.push(2);
numbers.push(3);
System.out.println(numbers.pop());
System.out.println(numbers.pop());
System.out.println(numbers.pop());

ArrayStack<String> texts = new ArrayStack<>(2);
texts.push("PBO");
texts.push("Collection");
System.out.println(texts.pop());
System.out.println(texts.pop());
```

~ Kode pengujian ArrayStack<Integer> dan ArrayStack<String>.

Output:

```text
3
2
1
Collection
PBO
```

LIFO terbukti: Integer keluar 3, 2, 1; String keluar Collection, PBO. Satu class generic dapat dipakai untuk berbagai tipe.

## D.3 Overflow dan Underflow

```java
ArrayStack<Integer> numbers = new ArrayStack<>(3);
numbers.push(1);
numbers.push(2);
numbers.push(3);

try {
    numbers.push(4);
} catch (ArrayStack.ArrayStackException e) {
    System.out.println("Overflow: " + e.getMessage());
}

System.out.println(numbers.pop());
System.out.println(numbers.pop());
System.out.println(numbers.pop());

try {
    numbers.pop();
} catch (ArrayStack.ArrayStackException e) {
    System.out.println("Underflow: " + e.getMessage());
}
```

~ Kode pengujian overflow dan underflow.

Output:

```text
Overflow: Overflow Error
3
2
1
Underflow: Underflow Error
```

Push keempat pada kapasitas 3 menghasilkan Overflow Error. Pop setelah stack kosong menghasilkan Underflow Error. Keduanya tidak mengubah isi stack. Compiler juga mencegah tipe yang salah, misalnya numbers.push("satu") pada ArrayStack<Integer>:

```text
error: incompatible types: String cannot be converted to Integer
```

# E. Sintesis Pemilihan Collection

| Kasus | Struktur | Kebutuhan utama |
|---|---|---|
| Student sorting | ArrayList<Student> | urutan, sorting, index |
| Student filtering | Vector<Student> | List object dan iterasi |
| Countries | HashSet<String> | data unik, urutan tidak penting |
| Generic stack | ArrayList<T> di dalam ArrayStack<T> | ukuran dinamis, LIFO, type safety |

Pemilihan collection mengikuti requirement. ArrayList dipakai saat urutan, index, dan sorting dibutuhkan. HashSet dipakai saat keunikan lebih penting daripada urutan. Vector dipakai karena tugas meminta, bukan karena selalu lebih unggul dari ArrayList.

# F. Jawaban Checkpoint

## F.1 Checkpoint 1

Field tetap private untuk menerapkan encapsulation. Collection tidak mengakses field secara langsung; sorting memakai getName(), filtering memakai getDepartment(), dan pencetakan memakai toString(). Dengan cara ini, data tetap dijaga oleh class Student.

## F.2 Checkpoint 2

Yang berubah hanya urutan reference di dalam ArrayList, bukan isi object. Bukti dari output: S001 Rina tetap Computer Science dan Bandung, hanya posisinya pindah dari index 0 ke index 4. S002 Andi tetap 10 dan Cimahi, hanya posisinya pindah dari index 1 ke index 0.

## F.3 Checkpoint 3

department bertipe Object, sehingga compiler hanya mengetahui method milik Object. Method equalsIgnoreCase adalah milik String. Pemeriksaan instanceof memastikan isi department benar-benar String sebelum dilakukan cast. Tanpa pemeriksaan itu, department bertipe Integer seperti 10 atau 20 dapat menyebabkan ClassCastException. Ini adalah konsekuensi langsung dari pemilihan Object.

## F.4 Checkpoint 4

Enam kali add menghasilkan size 5 karena "Indonesia" duplikat. Urutan output HashSet bukan kontrak program. Urutan tersebut bergantung pada hashCode, kapasitas internal, dan versi Java, sehingga tidak boleh dijadikan patokan.

## F.5 Checkpoint 5

Collections.sort hanya menerima List. List memiliki urutan dan index sehingga posisi elemen dapat ditukar. Set tidak memiliki index dan tidak menjamin urutan; HashSet juga bukan List. Pesan compiler adalah akibat dari perbedaan kontrak tersebut. Untuk menampilkan data Set terurut, data harus disalin ke ArrayList terlebih dahulu.

## F.6 Checkpoint 6

Behavior atau kontrak Stack adalah LIFO, push menambah elemen ke atas, pop menghapus dan mengembalikan elemen teratas, isEmpty, overflow saat penuh, dan underflow saat kosong. Detail implementasi array adalah int[] items, field top, dan items.length. Setelah array diganti ArrayList, seluruh behavior tersebut tidak boleh berubah.

## F.7 Checkpoint 7

ArrayStack<T> dapat menyimpan berbagai tipe reference dengan type safety saat compile. Compiler mencegah push dengan tipe yang salah, misalnya numbers.push("satu") pada ArrayStack<Integer>. Method pop() juga mengembalikan T tanpa cast manual, sehingga kesalahan tipe tidak muncul sebagai ClassCastException saat runtime.

# G. Refleksi 8.4

1. Perbedaan paling penting antara array dan ArrayList adalah ukuran. Array memiliki ukuran tetap sejak dibuat, sedangkan ArrayList dapat bertambah dan berkurang. ArrayList juga menyediakan add, remove, size, get, dan dapat disort dengan Collections.sort.

2. Sorting Student membutuhkan aturan pembanding berdasarkan name karena Student memiliki beberapa atribut dan tidak mengimplementasikan Comparable. Tanpa aturan, Java tidak mengetahui atribut mana yang dipakai untuk mengurutkan. Comparator.comparing(Student::getName) menetapkan name sebagai kunci sorting.

3. department bertipe Object membuat filtering tidak nyaman karena compiler hanya mengetahui method milik Object. Method String seperti equalsIgnoreCase tidak dapat dipanggil langsung. Diperlukan instanceof dan cast, serta ada risiko ClassCastException jika department berisi Integer.

4. Jika studentID tidak boleh duplikat, pilihan yang dipertimbangkan adalah Set<Student> dengan equals dan hashCode berdasarkan studentID, Map<String, Student> dengan studentID sebagai key, atau List<Student> yang dicek dengan contains sebelum add. Pilihan paling rapi adalah Map atau Set dengan equals dan hashCode yang benar.

5. Set tidak cocok untuk menghitung frekuensi karena duplikat dibuang. Jika angka 7 muncul lima kali, Set hanya menyimpan satu angka 7. Frekuensi membutuhkan struktur yang menyimpan nilai dan jumlah kemunculan, misalnya Map.

6. HashSet tidak boleh diasumsikan memiliki urutan tetap karena urutan iterasi bergantung pada hashCode, kapasitas internal, dan versi Java. Urutan dapat berubah setelah data ditambah atau dihapus. Untuk urutan yang stabil dapat dipakai LinkedHashSet, dan untuk urutan terurut dapat dipakai TreeSet.

7. Ketika Stack berpindah dari int[] ke ArrayList<T>, yang berubah adalah cara menyimpan dan mengelola data: int[] menjadi ArrayList<T>, top diganti items.size(), push dan pop memakai method ArrayList, dan tipe int menjadi T. Yang tetap adalah LIFO, push, pop, isEmpty, overflow, dan underflow.

8. Memilih collection bukan soal mana yang paling canggih, tetapi soal mana yang paling sesuai dengan kebutuhan data: apakah urutan penting, apakah duplikat boleh, apakah perlu akses index, apakah perlu sorting, atau apakah hanya perlu keunikan dan pengecekan keanggotaan.
