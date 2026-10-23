package gui; 
 
import induk.Mahasiswa; 
import javax.swing.*; 
import javax.swing.table.DefaultTableModel; 
import java.awt.event.KeyAdapter; 
import java.awt.event.KeyEvent; 
 
/* paraktek ini menggunakan Jframe */ 
 
public class MahasiswaGUI extends Mahasiswa { 
 
    private JFrame frame; 
    private JTextField txtNama; 
    private JTable tabel; 
    private DefaultTableModel modelTabel; 
 
    public MahasiswaGUI() { 
        buatTampilan(); 
        muatTabel(); 
    } 
 
    private void buatTampilan() { 
        frame = new JFrame(getNamaApp()); // ambil dari method parent 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setSize(500, 440); 
        frame.setLocationRelativeTo(null); 
        frame.setResizable(false); 
        frame.setLayout(null); // layout manual pakai setBounds 
 
        JLabel lblNama = new JLabel("Nama:"); 
        lblNama.setBounds(20, 20, 60, 25); 
        frame.add(lblNama); 
 
        txtNama = new JTextField(); 
        txtNama.setBounds(80, 20, 380, 25); 
        frame.add(txtNama); 
 
        // Tombol 
        JButton btnTambah = new JButton("Tambah"); 
        btnTambah.setBounds(80, 60, 100, 30); 
        frame.add(btnTambah); 
 
        JButton btnUbah = new JButton("Ubah"); 
        btnUbah.setBounds(190, 60, 100, 30); 
        frame.add(btnUbah); 
 
        JButton btnHapus = new JButton("Hapus"); 
        btnHapus.setBounds(300, 60, 100, 30); 
        frame.add(btnHapus); 
 
        // Grid dengan komponen JTable 
        // Kolom pada JTable harus dibentuk dahulu 
        modelTabel = new DefaultTableModel(new String[]{"No", "Nama Mahasiswa"}, 0) { 
            @Override 
            public boolean isCellEditable(int row, int column) { 
                return false; // sel tidak bisa diedit langsung 
            } 
        }; 
         
        tabel = new JTable(modelTabel); 
        tabel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); 
        tabel.setRowHeight(24); 
        tabel.getColumnModel().getColumn(0).setMaxWidth(50); 
 
        JScrollPane scroll = new JScrollPane(tabel); 
        scroll.setBounds(20, 110, 440, 270); 
        frame.add(scroll); 
 
        // Event 
        btnTambah.addActionListener(e -> aksiTambah()); 
        btnUbah.addActionListener(e -> aksiUbah()); 
        btnHapus.addActionListener(e -> aksiHapus()); 
 
        // klik baris -> nama tampil di textfield 
        tabel.getSelectionModel().addListSelectionListener(e -> { 
            int baris = tabel.getSelectedRow(); 
            if (!e.getValueIsAdjusting() && baris >= 0) { 
                txtNama.setText(modelTabel.getValueAt(baris, 1).toString()); 
            } 
        }); 
 
        // tekan tombol Delete di keyboard -> hapus baris terpilih 
        tabel.addKeyListener(new KeyAdapter() { 
            @Override 
            public void keyPressed(KeyEvent e) { 
                if (e.getKeyCode() == KeyEvent.VK_DELETE) { 
                    aksiHapus(); 
                } 
            } 
        }); 
    } 
 
    // baca isi grid dari daftarNama 
    private void muatTabel() { 
        modelTabel.setRowCount(0); 
        for (int i = 0; i < daftarNama.size(); i++) { 
            modelTabel.addRow(new Object[]{i + 1, daftarNama.get(i)}); 
        } 
    } 
 
    private void aksiTambah() { 
        String nama = txtNama.getText().trim(); 
        if (nama.isEmpty()) { 
            JOptionPane.showMessageDialog(frame, "Nama tidak boleh kosong."); 
            return; 
        } 
        tambahData(nama); // protected method parent 
        txtNama.setText(""); 
        muatTabel(); 
    } 
 
    private void aksiUbah() { 
        int baris = tabel.getSelectedRow(); 
        String namaBaru = txtNama.getText().trim(); 
        if (baris < 0) { 
            JOptionPane.showMessageDialog(frame, "Pilih data di tabel terlebih dahulu."); 
            return; 
        } 
        if (namaBaru.isEmpty()) { 
            JOptionPane.showMessageDialog(frame, "Nama tidak boleh kosong."); 
            return; 
        } 
        if (ubahData(baris + 1, namaBaru)) {  
            txtNama.setText(""); 
            muatTabel(); 
        } 
    } 
 
    private void aksiHapus() { 
        int baris = tabel.getSelectedRow(); 
        if (baris < 0) { 
            JOptionPane.showMessageDialog(frame, "Pilih data di tabel terlebih dahulu."); 
            return; 
        } 
        String nama = modelTabel.getValueAt(baris, 1).toString(); 
        int jawab = JOptionPane.showConfirmDialog(frame, 
                "Hapus data '" + nama + "'?", "Konfirmasi", 
                JOptionPane.YES_NO_OPTION); 
 
        if (jawab == JOptionPane.YES_OPTION) { 
            if (hapusData(baris + 1)) { // protected method parent 
                txtNama.setText(""); 
                muatTabel(); 
            } 
        } 
    } 
 
    public void tampilkan() { 
        frame.setVisible(true); 
    } 
 
    public static void main(String[] args) { 
        SwingUtilities.invokeLater(() -> new MahasiswaGUI().tampilkan()); 
    } 
} 
