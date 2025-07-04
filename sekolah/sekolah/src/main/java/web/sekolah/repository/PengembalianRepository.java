package web.sekolah.repository;

import web.sekolah.model.Pengembalian;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PengembalianRepository extends JpaRepository<Pengembalian, Long> {
    List<Pengembalian> findByNamaPeminjamContainingIgnoreCase(String nama);
    List<Pengembalian> findByTglPengembalianBetween(java.time.LocalDate start, java.time.LocalDate end);
}

