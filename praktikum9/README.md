# Praktikum 9 - Unit Testing dengan JUnit 5

## Struktur

```text
praktikum9/
├── evidence/
│   ├── screenshots/
│   └── *.txt
└── source/
    ├── pom.xml
    └── src/
        ├── main/java/id/ac/polban/pbo/
        │   ├── BankAccount.java
        │   ├── InsufficientBalanceException.java
        │   └── SavingsAccount.java
        └── test/java/id/ac/polban/pbo/
            ├── BankAccountTest.java
            └── SavingsAccountTest.java
```

## Cara menjalankan

Masuk ke folder `source`:

```powershell
cd praktikum9/source
mvn test
```

Untuk build bersih dari nol:

```powershell
mvn clean test
```

Menjalankan satu class test:

```powershell
mvn "-Dtest=BankAccountTest" test
```

Menjalankan satu method test:

```powershell
mvn "-Dtest=BankAccountTest#deposit_menambahSaldo" test
```

## Cakupan test

`BankAccountTest` berisi 15 test:

- constructor menyimpan data dan menolak saldo negatif
- deposit valid dan invalid
- withdraw valid, melebihi saldo, dan amount tidak valid
- parameterized test untuk deposit dan withdraw
- transfer valid dan target null

`SavingsAccountTest` berisi 4 test:

- aturan saldo minimum berlaku lewat reference `BankAccount`
- withdraw tepat di batas minimum
- perhitungan bunga
- bunga negatif ditolak

## Bukti

Output teks test ada di `evidence/`. Screenshot ada di `evidence/screenshots/`.

Hasil akhir:

```text
Tests run: 19, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```
