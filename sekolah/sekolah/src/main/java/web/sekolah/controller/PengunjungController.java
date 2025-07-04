package web.sekolah.controller;

import web.sekolah.model.Pengunjung;
import web.sekolah.service.PengunjungService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin-perpustakaan")
public class PengunjungController {

    private final PengunjungService service;

    public PengunjungController(PengunjungService service) {
        this.service = service;
    }

    // Tampilkan form tambah pengunjung
    @GetMapping("/tambah-pengunjung")
    public String showForm(Model model) {
        model.addAttribute("pengunjung", new Pengunjung());
        return "admin-perpustakaan/tambah-pengunjung";
    }

    // Simpan data pengunjung (hari otomatis di-set dari tanggal)
    @PostMapping("/save-pengunjung")
    public String save(@ModelAttribute Pengunjung pengunjung) {
        service.save(pengunjung);
        return "redirect:/admin-perpustakaan/data-pengunjung?sukses=1";
    }

    // Tampilkan semua pengunjung
    @GetMapping("/data-pengunjung")
    public String showAll(Model model) {
        model.addAttribute("pengunjungList", service.getAll());
        return "admin-perpustakaan/data-pengunjung";
    }
}
