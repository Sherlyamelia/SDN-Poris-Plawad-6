package web.sekolah.repository;

import web.sekolah.model.Pengunjung;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PengunjungRepository extends JpaRepository<Pengunjung, Long> {
}

