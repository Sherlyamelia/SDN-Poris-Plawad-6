package web.sekolah.repository;

import web.sekolah.model.Peminjaman;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface PeminjamanRepository extends JpaRepository<Peminjaman, Long> {

    @Query("SELECT COUNT(p) FROM Peminjaman p WHERE MONTH(p.tglPinjam) = MONTH(:today) AND YEAR(p.tglPinjam) = YEAR(:today)")
    long countPeminjamanInCurrentMonth(LocalDate today);

    // ✅ Tambahan: Jumlah peminjaman per buku di bulan ini (untuk chart)
    @Query("SELECT p.buku.namaBuku, COUNT(p) FROM Peminjaman p " +
            "WHERE MONTH(p.tglPinjam) = MONTH(CURRENT_DATE) AND YEAR(p.tglPinjam) = YEAR(CURRENT_DATE) " +
            "GROUP BY p.buku.namaBuku")
    List<Object[]> countPeminjamanPerBukuBulanIni();
}
