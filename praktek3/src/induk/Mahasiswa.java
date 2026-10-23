package induk;

import java.util.ArrayList;

public class Mahasiswa {
    private String namaApp = "Aplikasi CRUD Mahasiswa";      
    protected ArrayList<String> daftarNama = new ArrayList<>();      
    public String getNamaApp() {         
        return namaApp;     
    }      
    protected void tambahData(String nama) {         
        daftarNama.add(nama);         
        System.out.println("Data '" + nama + "' berhasil ditambahkan.");     
    }      protected void tampilData() {         
        if (daftarNama.isEmpty()) {             
            System.out.println("Data masih kosong.");             
            return;         }         
            System.out.println("---- Daftar Mahasiswa ----");         
            for (int i = 0; i < daftarNama.size(); i++) {             
                System.out.println((i + 1) + ". " + daftarNama.get(i));         
            }     
        }      
        protected boolean ubahData(int nomor, String namaBaru) {         
            if (!nomorValid(nomor)) {             
                return false;         
            }         
            daftarNama.set(nomor - 1, namaBaru);         
            return true;     
        }      
        protected boolean hapusData(int nomor) {         
            if (!nomorValid(nomor)) {             
                return false;         
            }         
            daftarNama.remove(nomor - 1);         
            return true;     
        }      
        protected boolean nomorValid(int nomor) {         
            return nomor >= 1 && nomor <= daftarNama.size();     
        } 

}
