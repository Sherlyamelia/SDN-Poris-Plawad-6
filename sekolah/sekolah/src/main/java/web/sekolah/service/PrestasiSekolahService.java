package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.model.PrestasiSekolah;
import web.sekolah.repository.PrestasiSekolahRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestasiSekolahService {

    @Autowired
    private PrestasiSekolahRepository prestasiSekolahRepository;

    public List<PrestasiSekolah> getAllPrestasiSekolah() {
        return prestasiSekolahRepository.findAll();
    }

    public void savePrestasiSekolah(PrestasiSekolah prestasi) {
        prestasiSekolahRepository.save(prestasi);
    }

    public Optional<PrestasiSekolah> getPrestasiSekolahById(Long id) {
        return prestasiSekolahRepository.findById(id);
    }

    public void deletePrestasiSekolah(Long id) {
        prestasiSekolahRepository.deleteById(id);
    }

    public PrestasiSekolah getById(Long id) {
        return prestasiSekolahRepository.findById(id).orElse(null);
    }

    public long count() {
        return prestasiSekolahRepository.count();
    }

    public Map<String, Long> countPerTahun() {
        List<PrestasiSekolah> data = prestasiSekolahRepository.findAll();
        return data.stream()
                .collect(Collectors.groupingBy(
                        p -> String.valueOf(p.getTahun()), // konversi int tahun jadi String
                        TreeMap::new,
                        Collectors.counting()));
    }


}
