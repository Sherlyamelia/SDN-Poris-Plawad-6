package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.PrestasiSekolah;
import web.sekolah.model.PrestasiSiswa;
import web.sekolah.repository.PrestasiSiswaRepository;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestasiSiswaService {

    @Autowired
    private PrestasiSiswaRepository prestasiSiswaRepository;

    public List<PrestasiSiswa> getAllPrestasiSiswa() {
        return prestasiSiswaRepository.findAll();
    }

    public void savePrestasiSiswa(PrestasiSiswa prestasi) {
        prestasiSiswaRepository.save(prestasi);
    }

    public Optional<PrestasiSiswa> getPrestasiSiswaById(Long id) {
        return prestasiSiswaRepository.findById(id);
    }

    public void deletePrestasiSiswa(Long id) {
        prestasiSiswaRepository.deleteById(id);
    }

    public PrestasiSiswa getById(Long id) {
        return prestasiSiswaRepository.findById(id).orElse(null);
    }

    public long count() {
        return prestasiSiswaRepository.count();
    }

    public Map<String, Long> countPerTahun() {
        List<PrestasiSiswa> data = prestasiSiswaRepository.findAll();
        return data.stream()
                .collect(Collectors.groupingBy(
                        p -> String.valueOf(p.getTahun()), // konversi int tahun jadi String
                        TreeMap::new,
                        Collectors.counting()));
    }


}
