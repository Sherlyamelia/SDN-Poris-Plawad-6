package web.sekolah.service;

import web.sekolah.model.Buku;
import web.sekolah.model.Pengembalian;
import web.sekolah.repository.BukuRepository;
import web.sekolah.repository.PengembalianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
public class PengembalianService {

    @Autowired
    private PengembalianRepository repository;

    @Autowired
    private BukuRepository bukuRepository;

    public List<Pengembalian> getAll() {
        return repository.findAll();
    }

    public int getJumlahPengembali() {
        return repository.findAll().size();
    }

    public Pengembalian simpan(Pengembalian pengembalian) {
        int denda = hitungDenda(pengembalian);
        pengembalian.setDenda(denda);
        return repository.save(pengembalian);
    }

    public Pengembalian simpanDanUpdateStok(Pengembalian pengembalian) {
        int denda = hitungDenda(pengembalian);
        pengembalian.setDenda(denda);

        // ✅ Tambah stok buku berdasarkan bukuId
        if (pengembalian.getBukuId() != null) {
            Optional<Buku> optionalBuku = bukuRepository.findById(pengembalian.getBukuId());
            if (optionalBuku.isPresent()) {
                Buku buku = optionalBuku.get();
                buku.setQty(buku.getQty() + 1); // Tambah stok
                bukuRepository.save(buku);
            } else {
                System.err.println("❌ Buku dengan ID " + pengembalian.getBukuId() + " tidak ditemukan.");
            }
        } else {
            System.err.println("❌ bukuId di data pengembalian kosong.");
        }

        return repository.save(pengembalian);
    }

    public void hapusSemua() {
        repository.deleteAll();
    }

    public int hitungDenda(Pengembalian pengembalian) {
        if (pengembalian.getTglPengembalian() != null && pengembalian.getTglKembali() != null) {
            long selisih = ChronoUnit.DAYS.between(pengembalian.getTglKembali(), pengembalian.getTglPengembalian());
            return (int) (selisih > 0 ? selisih * 1000 : 0); // denda Rp 1000/hari
        }
        return 0;
    }
}
