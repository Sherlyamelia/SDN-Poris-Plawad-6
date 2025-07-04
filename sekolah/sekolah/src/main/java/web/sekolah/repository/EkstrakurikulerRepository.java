package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.Ekstrakurikuler;

public interface EkstrakurikulerRepository extends JpaRepository<Ekstrakurikuler, Long> {
    long countByKategoriIgnoreCase(String kategori);
}
