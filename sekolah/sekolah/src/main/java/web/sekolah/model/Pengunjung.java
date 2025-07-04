package web.sekolah.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.format.TextStyle;
import java.util.Locale;

@Entity
public class Pengunjung {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nama;
    private int jumlah;
    private String hari;
    private LocalDate tanggal;
    private String keperluan;

    // Getter dan Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public int getJumlah() {
        return jumlah;
    }

    public void setJumlah(int jumlah) {
        this.jumlah = jumlah;
    }

    public String getHari() {
        return hari;
    }

    // Tidak perlu setter manual untuk hari
    public void setHari(String hari) {
        this.hari = hari;
    }

    public LocalDate getTanggal() {
        return tanggal;
    }

    public void setTanggal(LocalDate tanggal) {
        this.tanggal = tanggal;

        // Set otomatis nilai hari dari tanggal
        if (tanggal != null) {
            this.hari = tanggal
                    .getDayOfWeek()
                    .getDisplayName(TextStyle.FULL, new Locale("id", "ID")); // e.g., "Senin"
        }
    }

    public String getKeperluan() {
        return keperluan;
    }

    public void setKeperluan(String keperluan) {
        this.keperluan = keperluan;
    }
}
