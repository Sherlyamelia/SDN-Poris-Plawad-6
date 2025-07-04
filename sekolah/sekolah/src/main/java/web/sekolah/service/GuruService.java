package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import web.sekolah.model.Guru;
import web.sekolah.repository.GuruRepository;

import java.util.List;
import java.util.Optional;

@Service
public class GuruService {

    @Autowired
    private GuruRepository guruRepository;


    public void save(Guru guru) {
        guruRepository.save(guru);
    }

    public List<Guru> getAllGuru() {
        return guruRepository.findAll();
    }

    public Guru findById(Long id) {
        return guruRepository.findById(id).orElse(null);
    }

    public void deleteGuruById(Long id) {
        guruRepository.deleteById(id);
    }

    public long count() {
        return guruRepository.count();
    }

    public long countByJenisKelamin(String jenisKelamin) {
        return guruRepository.countByJenisKelamin(jenisKelamin);
    }

}
