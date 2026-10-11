# Tugas Praktikum 4 - Desain Pemrograman Berorientasi Objek (DPBO)

## 1. Janji
Saya Afzaal Zaidan Febryanto dengan NIM 2508692 mengerjakan Tugas Praktikum 4 dalam mata kuliah Desain dan Pemrograman Berorientasi Objek untuk keberkahan-Nya maka saya tidak melakukan kecurangan seperti yang telah dispesifikasikan. Aamiin.

---

## 2. Desain dan Fungsi Program
Program ini merupakan aplikasi pengelolaan data karyawan berbasis desktop yang dibangun menggunakan **Java Swing GUI** pada IntelliJ IDEA. Program mengimplementasikan arsitektur berorientasi objek yang memisahkan antara kelas model data (`Person`) dan antarmuka interaktif (`PersonMenu`). Program mendukung operasi **CRUD** (Create, Read, Update, Delete) secara visual dan interaktif.

### a. Class Person (Model Data)
Class ini berfungsi sebagai model/entitas data murni yang merepresentasikan data seorang karyawan.
- **Atribut**:
  - `id` (String): ID identitas karyawan.
  - `nama` (String): Nama lengkap karyawan.
  - `tahunLahir` (int): Tahun kelahiran karyawan.
  - `kategori` (String): Jabatan atau divisi pekerjaan (Staff, Manager, Supervisor, Intern).
  - `status` (String): Status kepegawaian (Tetap / Tidak Tetap).
- **Method**:
  - Constructor tanpa parameter dan constructor berparameter lengkap.
  - Setter dan Getter untuk masing-masing atribut.

### b. Class PersonMenu extends JFrame (GUI & Controller)
Class ini berisi Graphical User Interface (GUI) dari program, berfungsi untuk mengatur GUI, menampung event listener, serta menjalankan method untuk manipulasi data (insert, update, delete).

<img width="743" height="641" alt="gui" src="https://github.com/user-attachments/assets/4ca3f855-ab5a-4705-a044-be6c2ea42dd6" />
<br>

Class ini mengatur tampilan dan logika aplikasi berbasis GUI. Berfungsi untuk menampilkan form input, menampung event handler tombol dan tabel, serta mengatur daftar data karyawan dalam bentuk tabel interaktif.

**Komponen Utama GUI:**
* **`JTextField`** $\rightarrow$ untuk input ID (`idField`), Nama (`namaField`), dan Tahun Lahir (`tahunLahirField`).
* **`JComboBox`** $\rightarrow$ untuk memilih Kategori pekerjaan (`kategoriComboBox`).
* **`JRadioButton`** $\rightarrow$ untuk menentukan Status kepegawaian Tetap / Tidak Tetap (`tetapRadioButton`, `tidakTetapRadioButton`) yang dikelompokkan ke dalam `ButtonGroup` (Atribut tambahan bonus +20).
* **`JTable`** $\rightarrow$ untuk menampilkan seluruh daftar data karyawan di dalam `JScrollPane` (`personTable`).
* **Tombol Add/Update, Delete, dan Cancel** $\rightarrow$ untuk mengeksekusi operasi data dan mengatur mode form input (`addUpdateButton`, `deleteButton`, `cancelButton`).

**Fungsi utama dalam class ini:**
* `insertData()` – Memvalidasi input dan menambahkan data karyawan baru ke dalam list.
* `updateData()` – Memperbarui data karyawan pada baris tabel yang sedang dipilih.
* `deleteData()` – Menghapus data karyawan dari list setelah memperoleh konfirmasi pengguna.
* `clearForm()` – Mengosongkan isian textfield, mereset combo box dan radio button, serta mengembalikan tombol ke state awal.
* `setTable()` – Memetakan isi `listPerson` ke dalam `DefaultTableModel` agar tampil pada tabel.
* `populateList()` – Mengisi data awal berupa 8 record karyawan secara default saat aplikasi dibuka.

---

## 3. Penjelasan Atribut dan Method Setiap Kelas

### a. Kelas `Person`
- **`id` (String)**: Menyimpan nomor identitas karyawan.
- **`nama` (String)**: Menyimpan nama lengkap karyawan.
- **`tahunLahir` (int)**: Menyimpan angka tahun kelahiran karyawan.
- **`kategori` (String)**: Menyimpan tingkatan posisi/divisi kerja.
- **`status` (String)**: Menyimpan status kerja karyawan ("Tetap" atau "Tidak Tetap").
- **`Person(...)`**: Menginisialisasi nilai seluruh properti saat objek baru dibentuk.
- **Getter dan Setter**: Mengakses serta memodifikasi nilai setiap atribut secara terenkapsulasi.

### b. Kelas `PersonMenu`
- **`listPerson` (ArrayList<Person>)**: Struktur array dinamis penampung kumpulan objek karyawan di memori program.
- **`selectedIndex` (int)**: Variabel penanda indeks baris tabel yang aktif dipilih (bernilai -1 jika tidak ada baris yang dipilih).
- **`main(String[] args)`**: Titik awal eksekusi program untuk membuat instansi window, mengatur ukuran jendela (750x650), memposisikan window di tengah layar, dan menampilkannya.
- **`PersonMenu()`**: Konstruktor untuk menginisialisasi listener tombol, listener klik tabel, ButtonGroup radio button, serta memuat data awal ke tabel.
- **`populateList()`**: Memasukkan 8 entitas data awal (Farrel Zafh, Farez Ghazali, Devindra Dwi, Jadzil Maliki, Fadey Putra, Juna Juniga, Tenxi Ghifari, dan Adrian Afif) ke dalam `listPerson`.
- **`setTable()`**: Membuat model tabel baru (`DefaultTableModel`) yang berisi kolom No, ID, Nama, Tahun Lahir, Kategori, dan Status, lalu mengisi setiap barisnya berdasarkan data dari `listPerson`.
- **`insertData()`**: Mengambil data teks, angka, pilihan combobox, dan status radio button dari form. Menolak input jika ada isian yang kosong atau format angka tidak valid, kemudian membuat objek `Person` baru dan merefresh tampilan tabel.
- **`updateData()`**: Memvalidasi input dan memperbarui nilai atribut pada objek `Person` di indeks `selectedIndex`, lalu memperbarui tabel dan mereset form.
- **`deleteData()`**: Menghapus objek `Person` pada indeks `selectedIndex` dari `listPerson`, memperbarui tabel, dan membersihkan form.
- **`clearForm()`**: Mengosongkan seluruh kolom input teks, memilih opsi default pada combo box, mereset radio button ke "Tetap", mengembalikan teks tombol menjadi "Add", menyembunyikan tombol "Delete", serta mengatur `selectedIndex` kembali ke nilai -1.

---

## 4. Penjelasan Alur Program

Alur kerja program berjalan secara terstruktur sebagai berikut:

1. **Inisialisasi Program dan Menampilkan Data (Read)**:
   - Program dijalankan melalui method `main()`, yang membuat instansi dari kelas `PersonMenu`.
   - Konstruktor `PersonMenu()` langsung memanggil method `populateList()` untuk mengisi 8 data karyawan awal ke dalam memori `listPerson`.
   - Method `setTable()` dipanggil untuk memetakan seluruh data di dalam list ke dalam `DefaultTableModel`, lalu menerapkannya pada `personTable` sehingga data langsung tampil di layar.
   - Pada kondisi awal ini, form input dalam keadaan kosong, tombol aksi bertuliskan "Add", dan tombol "Delete" disembunyikan.

2. **Alur Menambahkan Data Baru (Create)**:
   - Pengguna mengetikkan data pada kolom ID, Nama, dan Tahun Lahir, memilih opsi pada dropdown Kategori, serta memilih salah satu status pada Radio Button ("Tetap" atau "Tidak Tetap").
   - Pengguna menekan tombol "Add".
   - Program mengecek apakah ada field yang belum diisi atau pilihan kategori masih tanda "???". Jika input belum lengkap, program menampilkan dialog peringatan.
   - Program memeriksa validitas input tahun lahir melalui blok `try-catch`; jika input bukan angka, akan muncul pesan error dialog `NumberFormatException`.
   - Jika validasi berhasil, objek `Person` baru dibuat dan ditambahkan ke dalam `listPerson`.
   - Program memanggil kembali `setTable()` untuk merefresh tabel, membersihkan form melalui `clearForm()`, serta menampilkan dialog bahwa data berhasil ditambahkan.

3. **Alur Memilih dan Mengubah Data (Update)**:
   - Pengguna mengklik salah satu baris karyawan pada tabel (`personTable`).
   - Event `mousePressed` menangkap baris yang diklik dan menyimpan indeksnya ke dalam variabel `selectedIndex`.
   - Nilai dari kolom ID, Nama, Tahun Lahir, Kategori, dan Status pada baris tersebut otomatis dipindahkan ke komponen form yang bersesuaian.
   - Teks pada tombol "Add" otomatis berubah menjadi "Update", dan tombol "Delete" dimunculkan di layar.
   - Pengguna mengedit isi data pada form lalu menekan tombol "Update".
   - Program memvalidasi format data baru; jika valid, data pada objek karyawan di indeks `selectedIndex` diperbarui.
   - Tabel direfresh, form dibersihkan, tombol dikembalikan ke status awal, dan dialog keberhasilan update ditampilkan.

4. **Alur Menghapus Data dengan Prompt Konfirmasi (Delete)**:
   - Pengguna memilih baris data yang akan dihapus dari tabel, lalu menekan tombol "Delete".
   - Program memunculkan dialog konfirmasi berupa prompt: *"Apakah Anda yakin ingin menghapus data ini?"* dengan pilihan tombol Yes/No.
   - Jika pengguna memilih **Yes**, method `deleteData()` dijalankan untuk menghapus elemen pada `selectedIndex` dari `listPerson`. Selanjutnya tabel direfresh dan form dibersihkan.
   - Jika pengguna memilih **No**, aksi penghapusan langsung dibatalkan dan data tetap tersimpan di tabel.

5. **Alur Pembatalan Operasi (Cancel)**:
   - Pengguna dapat menekan tombol "Cancel" kapan saja.
   - Method `clearForm()` akan dijalankan untuk mengosongkan seluruh form input, mengembalikan tombol ke "Add", menyembunyikan tombol "Delete", serta mengatur `selectedIndex` kembali ke nilai -1.

---

## 5. Dokumentasi Program

### a. Tampilan Awal Program (Read Data Default)
Tampilan program saat pertama kali dibuka, memuat 8 data karyawan awal ke dalam tabel.

<img width="926" height="800" alt="read" src="https://github.com/user-attachments/assets/d3da4cb5-6d9f-4ab6-85d7-49117327ebf7" />


### b. Operasi Tambah Data (Create)
Pengisian data baru pada form input lengkap dengan pemilihan kategori dan radio button status, diikuti konfirmasi penambahan data berhasil.


https://github.com/user-attachments/assets/6389ea88-c496-4d2c-9ce3-7f5c70857b6a


### c. Operasi Pemilihan Baris dan Ubah Data (Update)
Data pada baris tabel terpilih masuk ke form, diubah nilainya, dan berhasil diperbarui dengan menekan tombol "Update".


https://github.com/user-attachments/assets/4d8ea242-ea13-429a-b625-ca104cb24492



### d. Prompt Konfirmasi dan Operasi Hapus Data (Delete)
Munculnya jendela konfirmasi pop-up saat tombol "Delete" ditekan serta kondisi tabel setelah record berhasil dihapus.



https://github.com/user-attachments/assets/a7d55c4c-314e-4994-9ab7-7ee3280ed327

