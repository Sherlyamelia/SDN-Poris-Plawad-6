package web.sekolah.repository;

import web.sekolah.model.Laporan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LaporanRepository extends JpaRepository<Laporan, Long> {
}
