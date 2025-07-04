package web.sekolah.service;

import web.sekolah.model.Buku;
import web.sekolah.repository.BukuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BukuService {

    @Autowired
    private BukuRepository bukuRepository;

    public List<Buku> getAllBuku() {
        return bukuRepository.findAll();
    }

    public Buku getById(Long id) {
        Optional<Buku> buku = bukuRepository.findById(id);
        return buku.orElse(null);
    }

    public void save(Buku buku) {
        bukuRepository.save(buku);
    }

    public void delete(Long id) {
        bukuRepository.deleteById(id);
    }

    public List<Buku> findByKategori(String kategori) {
        return bukuRepository.findByKategori(kategori);
    }

    public long count() {
        return bukuRepository.count();
    }

    // ✅ Tambahan: Total seluruh buku
    public long getJumlahBuku() {
        List<Buku> daftarBuku = bukuRepository.findAll();
        return daftarBuku.stream()
                .mapToLong(b -> b.getQty() != null ? b.getQty() : 0)
                .sum();
    }

    // ✅ Tambahan: Jumlah buku per kategori
    public Map<String, Long> getJumlahBukuPerKategori() {
        List<Buku> daftarBuku = bukuRepository.findAll();
        return daftarBuku.stream()
                .collect(Collectors.groupingBy(
                        Buku::getKategori,
                        Collectors.counting()
                ));
    }

    // ✅ Tambahan: Kurangi stok buku saat peminjaman
    public void kurangiStokBuku(Long bukuId, int jumlah) {
        Buku buku = bukuRepository.findById(bukuId).orElse(null);
        if (buku != null) {
            int stokSekarang = buku.getQty() != null ? buku.getQty() : 0;
            if (stokSekarang >= jumlah) {
                buku.setQty(stokSekarang - jumlah);
                bukuRepository.save(buku);
            } else {
                throw new IllegalArgumentException("Stok buku tidak mencukupi");
            }
        }
    }
}

