package web.sekolah.service;

import web.sekolah.model.Pengunjung;
import web.sekolah.repository.PengunjungRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PengunjungService {

    private final PengunjungRepository repository;

    public PengunjungService(PengunjungRepository repository) {
        this.repository = repository;
    }

    // Ambil semua data pengunjung
    public List<Pengunjung> getAll() {
        return repository.findAll();
    }

    // Simpan data pengunjung (hari akan otomatis ter-set dari tanggal)
    public void save(Pengunjung pengunjung) {
        if (pengunjung.getTanggal() != null) {
            // Set tanggal akan otomatis mengatur hari lewat setter di model
            pengunjung.setTanggal(pengunjung.getTanggal());
        }
        repository.save(pengunjung);
    }
}
