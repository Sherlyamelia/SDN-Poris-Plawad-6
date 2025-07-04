package web.sekolah.controller;

import org.springframework.http.ResponseEntity;
import web.sekolah.model.Pengembalian;
import web.sekolah.service.PengembalianService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-perpustakaan")
public class PengembalianController {

    @Autowired
    private PengembalianService service;

    @GetMapping("/data-pengembalian")
    public String tampilPengembalian(Model model) {
        List<Pengembalian> daftar = service.getAll();
        model.addAttribute("daftarPengembalian", daftar);

        int totalDenda = daftar.stream()
                .mapToInt(p -> p.getDenda() != null ? p.getDenda() : 0)
                .sum();
        model.addAttribute("totalDenda", totalDenda);

        return "admin-perpustakaan/data-pengembalian";
    }

    @PostMapping("/hapus-semua-pengembalian")
    public String hapusSemua() {
        service.hapusSemua();
        return "redirect:/admin-perpustakaan/data-pengembalian";
    }

    @PostMapping("/simpan-pengembalian")
    @ResponseBody
    public ResponseEntity<String> simpanAjax(@ModelAttribute Pengembalian pengembalian) {
        System.out.println("✅ Pengembalian diterima:");
        System.out.println(" - Nama Peminjam: " + pengembalian.getNamaPeminjam());
        System.out.println(" - Nama Buku: " + pengembalian.getNamaBuku());
        System.out.println(" - Buku ID: " + pengembalian.getBukuId());
        System.out.println(" - Tgl Pinjam: " + pengembalian.getTglPinjam());
        System.out.println(" - Tgl Kembali: " + pengembalian.getTglKembali());
        System.out.println(" - Tgl Pengembalian: " + pengembalian.getTglPengembalian());

        try {
            service.simpanDanUpdateStok(pengembalian);
            return ResponseEntity.ok("OK");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("❌ Gagal menyimpan pengembalian: " + e.getMessage());
        }
    }
}
