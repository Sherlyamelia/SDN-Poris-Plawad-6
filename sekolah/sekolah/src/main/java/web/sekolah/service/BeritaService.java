package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.Berita;
import web.sekolah.repository.BeritaRepository;

import java.util.List;

@Service
public class BeritaService {

    private final String uploadDir = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/berita/";

    @Autowired
    private BeritaRepository beritaRepository;

    // Ambil semua berita
    public List<Berita> getAll() {
        return beritaRepository.findAll();
    }

    // Simpan atau update berita
    public void save(Berita berita) {
        beritaRepository.save(berita);
    }

    // Ambil berita berdasarkan ID
    public Berita getById(Long id) {
        return beritaRepository.findById(id).orElse(null);
    }

    // Hapus berita berdasarkan ID
    public void delete(Long id) {
        beritaRepository.deleteById(id);
    }

    public Berita findById(Long id) {
        return beritaRepository.findById(id).orElse(null);
    }

    public long count() {
        return beritaRepository.count();
    }
}
