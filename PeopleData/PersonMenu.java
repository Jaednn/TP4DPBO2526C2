import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class PersonMenu extends JFrame {
    // Komponen GUI (wajib sama persis dengan field name di .form)
    private JPanel mainPanel;
    private JLabel titleLabel;
    private JTextField idField;
    private JTextField namaField;
    private JTextField tahunLahirField;
    private JComboBox<String> kategoriComboBox;
    private JRadioButton tetapRadioButton;
    private JRadioButton tidakTetapRadioButton;
    private JButton addUpdateButton;
    private JButton cancelButton;
    private JButton deleteButton;
    private JTable personTable;

    // ButtonGroup agar hanya satu radio button yang bisa dipilih
    private ButtonGroup statusGroup;

    // Data holder
    private ArrayList<Person> listPerson;
    private int selectedIndex = -1;

    // 1. main()
    public static void main(String[] args) {
        PersonMenu menu = new PersonMenu();
        menu.setSize(750, 650);
        menu.setLocationRelativeTo(null);
        menu.setContentPane(menu.mainPanel);
        menu.getContentPane().setBackground(Color.WHITE);
        menu.setVisible(true);
        menu.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    // 2. PersonMenu() (Constructor)
    public PersonMenu() {
        // Kelompokkan RadioButton ke dalam ButtonGroup
        statusGroup = new ButtonGroup();
        statusGroup.add(tetapRadioButton);
        statusGroup.add(tidakTetapRadioButton);
        tetapRadioButton.setSelected(true); // Default pilihan pertama

        // Inisialisasi list & tabel
        listPerson = new ArrayList<>();
        populateList();
        personTable.setModel(setTable());

        // Styling Title
        if (titleLabel != null) {
            titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 20f));
        }

        // Setup ComboBox
        String[] kategoriData = { "???", "Staff", "Manager", "Intern", "Supervisor" };
        kategoriComboBox.setModel(new DefaultComboBoxModel<>(kategoriData));

        // Sembunyikan tombol delete secara default
        deleteButton.setVisible(false);

        // Listener: Add / Update
        addUpdateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedIndex == -1) {
                    insertData();
                } else {
                    updateData();
                }
            }
        });

        // Listener: Delete (dengan Confirmation Prompt)
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "Apakah Anda yakin ingin menghapus data ini?",
                        "Konfirmasi Hapus",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );

                if (confirm == JOptionPane.YES_OPTION) {
                    deleteData();
                }
            }
        });

        // Listener: Cancel
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                clearForm();
            }
        });

        // Listener: Klik Baris Tabel
        personTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                selectedIndex = personTable.getSelectedRow();

                // Ambil data dari baris tabel yang dipilih
                String curId = personTable.getModel().getValueAt(selectedIndex, 1).toString();
                String curNama = personTable.getModel().getValueAt(selectedIndex, 2).toString();
                String curTahunLahir = personTable.getModel().getValueAt(selectedIndex, 3).toString();
                String curKategori = personTable.getModel().getValueAt(selectedIndex, 4).toString();
                String curStatus = personTable.getModel().getValueAt(selectedIndex, 5).toString();

                // Isi data ke form input
                idField.setText(curId);
                namaField.setText(curNama);
                tahunLahirField.setText(curTahunLahir);
                kategoriComboBox.setSelectedItem(curKategori);

                // Atur Radio Button sesuai data
                if (curStatus.equalsIgnoreCase("Tetap")) {
                    tetapRadioButton.setSelected(true);
                } else {
                    tidakTetapRadioButton.setSelected(true);
                }

                // Ubah mode ke Update
                addUpdateButton.setText("Update");
                deleteButton.setVisible(true);
            }
        });
    }

    // Method Populate List Awal
    private void populateList() {
        listPerson.add(new Person("EMP001", "Farrel Zafh", 2002, "Staff", "Tetap"));
        listPerson.add(new Person("EMP002", "Farez Ghazali", 2001, "Staff", "Tetap"));
        listPerson.add(new Person("EMP003", "Devindra Dwi", 2003, "Intern", "Tidak Tetap"));
        listPerson.add(new Person("EMP004", "Jadzil Maliki", 2000, "Manager", "Tetap"));
        listPerson.add(new Person("EMP005", "Fadey Putra", 2004, "Intern", "Tidak Tetap"));
        listPerson.add(new Person("EMP006", "Juna Juniga", 2002, "Staff", "Tidak Tetap"));
        listPerson.add(new Person("EMP007", "Tenxi Ghifari", 2001, "Supervisor", "Tetap"));
        listPerson.add(new Person("EMP008", "Adrian Afif", 2003, "Intern", "Tidak Tetap"));
    }

    // 3. setTable()
    public final DefaultTableModel setTable() {
        Object[] cols = { "No", "ID", "Nama", "Tahun Lahir", "Kategori", "Status" };
        DefaultTableModel tmp = new DefaultTableModel(null, cols);

        for (int i = 0; i < listPerson.size(); i++) {
            Object[] row = {
                    i + 1,
                    listPerson.get(i).getId(),
                    listPerson.get(i).getNama(),
                    listPerson.get(i).getTahunLahir(),
                    listPerson.get(i).getKategori(),
                    listPerson.get(i).getStatus()
            };
            tmp.addRow(row);
        }
        return tmp;
    }

    // 4. insertData()
    public void insertData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String tahunLahirStr = tahunLahirField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String status = tetapRadioButton.isSelected() ? "Tetap" : "Tidak Tetap";

            if (id.isEmpty() || nama.isEmpty() || tahunLahirStr.isEmpty() || kategori.equals("???")) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int tahunLahir = Integer.parseInt(tahunLahirStr);

            listPerson.add(new Person(id, nama, tahunLahir, kategori, status));
            personTable.setModel(setTable());
            clearForm();

            JOptionPane.showMessageDialog(null, "Data berhasil ditambahkan!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 5. updateData()
    public void updateData() {
        try {
            String id = idField.getText().trim();
            String nama = namaField.getText().trim();
            String tahunLahirStr = tahunLahirField.getText().trim();
            String kategori = kategoriComboBox.getSelectedItem().toString();
            String status = tetapRadioButton.isSelected() ? "Tetap" : "Tidak Tetap";

            if (id.isEmpty() || nama.isEmpty() || tahunLahirStr.isEmpty() || kategori.equals("???")) {
                JOptionPane.showMessageDialog(null, "Semua field harus diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
                return;
            }

            int tahunLahir = Integer.parseInt(tahunLahirStr);

            listPerson.get(selectedIndex).setId(id);
            listPerson.get(selectedIndex).setNama(nama);
            listPerson.get(selectedIndex).setTahunLahir(tahunLahir);
            listPerson.get(selectedIndex).setKategori(kategori);
            listPerson.get(selectedIndex).setStatus(status);

            personTable.setModel(setTable());
            clearForm();

            JOptionPane.showMessageDialog(null, "Data berhasil diubah!");
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(null, "Tahun Lahir harus berupa angka!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // 6. deleteData()
    public void deleteData() {
        listPerson.remove(selectedIndex);
        personTable.setModel(setTable());
        clearForm();

        JOptionPane.showMessageDialog(null, "Data berhasil dihapus!");
    }

    // 7. clearForm()
    public void clearForm() {
        idField.setText("");
        namaField.setText("");
        tahunLahirField.setText("");
        kategoriComboBox.setSelectedIndex(0);
        tetapRadioButton.setSelected(true); // Reset ke default

        addUpdateButton.setText("Add");
        deleteButton.setVisible(false);
        selectedIndex = -1;
    }
}