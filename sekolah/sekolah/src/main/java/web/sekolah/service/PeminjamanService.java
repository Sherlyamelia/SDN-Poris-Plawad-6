package web.sekolah.service;

import web.sekolah.model.Buku;
import web.sekolah.model.Peminjaman;
import web.sekolah.repository.BukuRepository;
import web.sekolah.repository.PeminjamanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PeminjamanService {

    @Autowired
    private PeminjamanRepository peminjamanRepository;

    @Autowired
    private BukuRepository bukuRepository;

    public void simpanPeminjaman(Peminjaman peminjaman) {
        Long bukuId = peminjaman.getBukuId();

        Optional<Buku> optionalBuku = bukuRepository.findById(bukuId);

        if (optionalBuku.isPresent()) {
            Buku buku = optionalBuku.get();
            int qtyTersedia = buku.getQty();
            int qtyDiminta = peminjaman.getJumlahPinjam();

            if (qtyTersedia >= qtyDiminta) {
                buku.setQty(qtyTersedia - qtyDiminta);
                bukuRepository.save(buku);

                // SET DATA LENGKAP
                peminjaman.setBuku(buku);
                peminjaman.setStatus("Dipinjam");
                peminjaman.setTglPinjam(LocalDate.now());
                peminjaman.setTglKembali(LocalDate.now().plusDays(7)); // contoh: 7 hari

                peminjamanRepository.save(peminjaman);
            } else {
                throw new RuntimeException("Stok buku tidak mencukupi. Tersedia: " + qtyTersedia);
            }
        } else {
            throw new RuntimeException("Buku dengan ID " + bukuId + " tidak ditemukan.");
        }
    }

    public List<Peminjaman> getAllPeminjaman() {
        return peminjamanRepository.findAll();
    }

    public void hapusPeminjaman(Long id) {
        if (peminjamanRepository.existsById(id)) {
            peminjamanRepository.deleteById(id);
        }
    }

    public Optional<Peminjaman> getPeminjamanById(Long id) {
        return peminjamanRepository.findById(id);
    }

    public long getTotalBukuSedangDipinjam() {
        return peminjamanRepository.findAll().stream()
                .filter(p -> p.getStatus() == null || p.getStatus().equalsIgnoreCase("dipinjam"))
                .mapToLong(p -> p.getJumlahPinjam() != null ? p.getJumlahPinjam() : 0)
                .sum();
    }

    public long getJumlahPeminjamanBulanIni() {
        return peminjamanRepository.countPeminjamanInCurrentMonth(LocalDate.now());
    }

    // ✅ Tambahan: Jumlah peminjaman per buku di bulan ini
    public List<Object[]> getJumlahPeminjamanPerBukuBulanIni() {
        return peminjamanRepository.countPeminjamanPerBukuBulanIni();
    }
}
