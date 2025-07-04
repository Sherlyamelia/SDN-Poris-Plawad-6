package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.repository.PrestasiKelasRepository;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PrestasiKelasService {

    @Autowired
    private PrestasiKelasRepository prestasiKelasRepository;

    public List<PrestasiKelas> getAllPrestasiKelas() {
        return prestasiKelasRepository.findAll();
    }

    public void savePrestasiKelas(PrestasiKelas prestasi) {
        prestasiKelasRepository.save(prestasi);
    }

    public Optional<PrestasiKelas> getPrestasiKelasById(Long id) {
        return prestasiKelasRepository.findById(id);
    }

    public void deletePrestasiKelas(Long id) {
        prestasiKelasRepository.deleteById(id);
    }

    public PrestasiKelas getById(Long id) {
        return prestasiKelasRepository.findById(id).orElse(null);
    }


}
