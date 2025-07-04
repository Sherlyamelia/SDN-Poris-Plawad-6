package web.sekolah.repository;

import web.sekolah.model.Guru;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GuruRepository extends JpaRepository<Guru, Long> {
    long countByJenisKelamin(String jenisKelamin);
}

