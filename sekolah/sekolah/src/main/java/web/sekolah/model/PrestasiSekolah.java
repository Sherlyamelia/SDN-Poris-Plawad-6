package web.sekolah.model;

import jakarta.persistence.*;

@Entity
public class PrestasiSekolah {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int peringkat;
    private String tahun;
    private String Prestasi;
    private String tingkat;

    @Column(length = 1000)
    private String deskripsi;

    private String foto;

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public int getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(int peringkat) {
        this.peringkat = peringkat;
    }

    public String getTahun() {
        return tahun;
    }

    public void setTahun(String tahun) {
        this.tahun = tahun;
    }

    public String getPrestasi() {
        return Prestasi;
    }

    public void setPrestasi(String Prestasi) {
        this.Prestasi = Prestasi;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTingkat() {
        return tingkat;
    }

    public void setTingkat(String tingkat) {
        this.tingkat = tingkat;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }
}

