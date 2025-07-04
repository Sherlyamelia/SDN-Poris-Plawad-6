package web.sekolah.model;

import jakarta.persistence.*;

@Entity
@Table(name = "guru")
public class Guru {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nama", nullable = false)
    private String nama;

    @Column(name = "nip", unique = true, nullable = false)
    private String nip;

    @Column(name = "pendidikan", nullable = false)
    private String pendidikan;

    @Column(name = "ttl", nullable = false)
    private String ttl;

    @Column(name = "jenis_kelamin", nullable = false)
    private String jenisKelamin;

    @Column(name = "agama", nullable = false)
    private String agama;

    @Column(name = "pangkat", nullable = false)
    private String pangkat;

    @Column(name = "posisi", nullable = false)
    private String posisi;

    @Column(name = "status", nullable = false)
    private String status;

    @Column(name = "foto")
    private String foto;

    // Constructor
    public Guru() {}

    // Getter & Setter
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNama() { return nama; }
    public void setNama(String nama) { this.nama = nama; }

    public String getNip() { return nip; }
    public void setNip(String nip) { this.nip = nip; }

    public String getPendidikan() { return pendidikan; }
    public void setPendidikan(String pendidikan) { this.pendidikan = pendidikan; }

    public String getTtl() { return ttl; }
    public void setTtl(String ttl) { this.ttl = ttl; }

    public String getJenisKelamin() { return jenisKelamin; }
    public void setJenisKelamin(String jenisKelamin) { this.jenisKelamin = jenisKelamin; }

    public String getAgama() { return agama; }
    public void setAgama(String agama) { this.agama = agama; }

    public String getPangkat() { return pangkat; }
    public void setPangkat(String pangkat) { this.pangkat = pangkat; }

    public String getPosisi() { return posisi; }
    public void setPosisi(String posisi) { this.posisi = posisi; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getFoto() { return foto; }
    public void setFoto(String foto) { this.foto = foto; }
}

