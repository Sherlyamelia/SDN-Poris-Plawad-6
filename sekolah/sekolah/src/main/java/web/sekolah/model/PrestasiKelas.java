package web.sekolah.model;

import jakarta.persistence.*;

@Entity
public class PrestasiKelas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String namaSiswa;
    private String Kelas;
    private int peringkat;
    private String periode;

    // Getter & Setter
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNamaSiswa() {
        return namaSiswa;
    }

    public void setNamaSiswa(String namaSiswa) {
        this.namaSiswa = namaSiswa;
    }

    public String getKelas() {
        return Kelas;
    }

    public void setKelas(String Kelas) {
        this.Kelas = Kelas;
    }

    public int getPeringkat() {
        return peringkat;
    }

    public void setPeringkat(int peringkat) {
        this.peringkat = peringkat;
    }

    public String getPeriode() {
        return periode;
    }

    public void setPeriode(String periode) {
        this.periode = periode;
    }
}

