package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.model.PrestasiSiswa;

public interface PrestasiKelasRepository extends JpaRepository<PrestasiKelas, Long> {
}
