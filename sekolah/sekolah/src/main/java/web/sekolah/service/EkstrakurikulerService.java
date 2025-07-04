package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import web.sekolah.model.Ekstrakurikuler;
import web.sekolah.repository.EkstrakurikulerRepository;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class EkstrakurikulerService {

    @Autowired
    private EkstrakurikulerRepository repository;

    private final String uploadDir = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/ekstrakurikuler/";

    public void save(Ekstrakurikuler eskul, MultipartFile fotoFile) {
        try {
            if (!fotoFile.isEmpty()) {
                String fileName = fotoFile.getOriginalFilename();
                Path filePath = Paths.get(uploadDir, fileName);
                Files.createDirectories(filePath.getParent());
                Files.copy(fotoFile.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                eskul.setFoto(fileName);
            }
            repository.save(eskul);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<Ekstrakurikuler> getAll() {
        return repository.findAll();
    }

    public List<Ekstrakurikuler> findAll() {
        return repository.findAll();
    }

    public Ekstrakurikuler getById(Long id) {
        return repository.findById(id).orElse(null);
    }

    public void update(Long id, Ekstrakurikuler updatedEskul, MultipartFile fotoFile) {
        Ekstrakurikuler existingEskul = getById(id);
        if (existingEskul != null) {
            existingEskul.setNama(updatedEskul.getNama());
            existingEskul.setDeskripsi(updatedEskul.getDeskripsi());
            existingEskul.setKategori(updatedEskul.getKategori());

            if (!fotoFile.isEmpty()) {
                String fileName = fotoFile.getOriginalFilename();
                try {
                    fotoFile.transferTo(new File(uploadDir + fileName));
                    existingEskul.setFoto(fileName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            repository.save(existingEskul);
        }
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public long count() {
        return repository.count();
    }

    public long countByKategori(String kategori) {
        return repository.countByKategoriIgnoreCase(kategori);
    }

}
