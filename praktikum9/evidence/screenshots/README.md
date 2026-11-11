# Screenshot Evidence PBO9

Screenshot disimpan di folder ini dengan urutan tahap.

| File | Isi | Status |
|---|---|---|
| `01-mvn-version.png` | Output `mvn -v` | Ada |
| `02-first-test-green.png` | Test pertama hijau, `Tests run: 2` | Ada |
| `03-expected-salah-red.png` | Expected sengaja salah, `160000` vs `150000` | Ada |
| `04-stage2-green.png` | Test withdraw dan exception hijau, `Tests run: 5` | Ada |
| `05-withdraw-valid.png` | Test `withdraw_valid_tidakMelempar` hijau | Ada |
| `06-state-integrity-red.png` | Sabotase urutan withdraw, saldo jadi `-100000` | Ada |
| `07-beforeeach.png` | Kode `@BeforeEach` | Ada |
| `08-parameterized.png` | `deposit_tidakValid`, 3 invocation hijau | Ada |
| `09-parameterized-all.png` | Semua test setelah parameterized, `Tests run: 13` | Ada |
| `10-savings-polymorphism.png` | Test `SavingsAccount`, total `Tests run: 17` | Ada |
| `11-tdd-red.png` | `transfer_targetNull_ditolak` merah, `NullPointerException` | Ada |
| `12-tdd-green.png` | Setelah perbaikan, `Tests run: 19` hijau | Ada |
| `13-all-tests-green.png` | `mvn clean test` terakhir, semua hijau | Belum, gunakan `12-tdd-green.png` dan `13-final-test.txt` |

## Cara screenshot

1. Buka terminal di folder `praktikum9/source`.
2. Jalankan perintah yang ingin dibuktikan.
3. Tekan `Win + Shift + S`.
4. Pilih area terminal yang memuat perintah dan hasilnya.
5. Simpan sebagai PNG di folder ini.
6. Pastikan teks terbaca. Jangan screenshot seluruh layar kalau terminal jadi terlalu kecil.

## Perintah agar output tidak berwarna

```powershell
mvn -Dstyle.color=never test
```

## Catatan

- Screenshot `07-beforeeach.png` berasal dari editor. Garis bawah pada `receiver` hanya warning field belum dipakai, bukan error.
- Screenshot `06-state-integrity-red.png` adalah bukti tambahan untuk menunjukkan pentingnya pengecekan saldo setelah exception.
- Simpan juga output teks di folder `praktikum9/evidence/` sebagai cadangan.
- Jangan mengedit isi screenshot. Kalau test merah, biarkan merah.
