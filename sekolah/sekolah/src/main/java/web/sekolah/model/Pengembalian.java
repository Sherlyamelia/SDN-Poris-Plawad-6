package web.sekolah.model;

import jakarta.persistence.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Entity
public class Pengembalian {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaPeminjam;
    private String namaBuku;

    // Field tambahan untuk menghubungkan ke Buku
    private Long bukuId;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tglPinjam;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tglKembali;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate tglPengembalian;

    private Integer denda;

    // --- GETTER & SETTER ---
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

    public String getNamaBuku() {
        return namaBuku;
    }

    public void setNamaBuku(String namaBuku) {
        this.namaBuku = namaBuku;
    }

    public Long getBukuId() {
        return bukuId;
    }

    public void setBukuId(Long bukuId) {
        this.bukuId = bukuId;
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

    public LocalDate getTglPengembalian() {
        return tglPengembalian;
    }

    public void setTglPengembalian(LocalDate tglPengembalian) {
        this.tglPengembalian = tglPengembalian;
    }

    public Integer getDenda() {
        return denda;
    }

    public void setDenda(Integer denda) {
        this.denda = denda;
    }
}
