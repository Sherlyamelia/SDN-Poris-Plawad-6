package web.sekolah.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Peminjaman {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPeminjam;

    @ManyToOne
    @JoinColumn(name = "buku_id")
    private Buku buku;

    private Integer jumlahPinjam;

    private LocalDate tglPinjam;
    private LocalDate tglKembali;

    private String status;

    @Transient
    private Long bukuId; // 🔑 Digunakan untuk binding ID buku dari <select>

    // ===== Getter & Setter =====

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaPeminjam() {
        return namaPeminjam;
    }

    public void setNamaPeminjam(String namaPeminjam) {
        this.namaPeminjam = namaPeminjam;
    }

    public Buku getBuku() {
        return buku;
    }

    public void setBuku(Buku buku) {
        this.buku = buku;
    }

    public Integer getJumlahPinjam() {
        return jumlahPinjam;
    }

    public void setJumlahPinjam(Integer jumlahPinjam) {
        this.jumlahPinjam = jumlahPinjam;
    }

    public LocalDate getTglPinjam() {
        return tglPinjam;
    }

    public void setTglPinjam(LocalDate tglPinjam) {
        this.tglPinjam = tglPinjam;
    }

    public LocalDate getTglKembali() {
        return tglKembali;
    }

    public void setTglKembali(LocalDate tglKembali) {
        this.tglKembali = tglKembali;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
    }
}
