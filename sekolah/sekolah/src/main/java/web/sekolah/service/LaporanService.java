package web.sekolah.service;

import web.sekolah.model.Laporan;
import web.sekolah.repository.LaporanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LaporanService {

    @Autowired
    private LaporanRepository laporanRepository;

    public List<Laporan> getAllLaporan() {
        return laporanRepository.findAll();
    }

    public Laporan getById(Long id) {
        return laporanRepository.findById(id).orElse(null);
    }

    public void save(Laporan laporan) {
        laporanRepository.save(laporan);
    }

    public void delete(Long id) {
        laporanRepository.deleteById(id);
    }

    public int totalQty() {
        return laporanRepository.findAll().stream().mapToInt(Laporan::getQty).sum();
    }
}
