package web.sekolah.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "ekstrakurikuler")
public class Ekstrakurikuler {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Nama tidak boleh kosong")
    private String nama;

    @NotBlank(message = "Kategori tidak boleh kosong")
    private String kategori;

    @NotBlank(message = "Deskripsi tidak boleh kosong")
    @Column(columnDefinition = "TEXT")
    private String deskripsi;

    private String foto; // Akan menyimpan path file gambar

    // Constructor kosong
    public Ekstrakurikuler() {
    }

    // Constructor dengan parameter
    public Ekstrakurikuler(String nama, String kategori, String deskripsi, String foto) {
        this.nama = nama;
        this.kategori = kategori;
        this.deskripsi = deskripsi;
        this.foto = foto;
    }

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

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}
