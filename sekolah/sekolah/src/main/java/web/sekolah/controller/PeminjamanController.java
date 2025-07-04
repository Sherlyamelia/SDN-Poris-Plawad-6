package web.sekolah.controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Buku;
import web.sekolah.model.Peminjaman;
import web.sekolah.service.PeminjamanService;
import web.sekolah.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin-perpustakaan")
public class PeminjamanController {

    @Autowired
    private PeminjamanService peminjamanService;

    @Autowired
    private BukuService bukuService;

    @GetMapping("/data-peminjaman")
    public String tampilFormPeminjaman(Model model) {
        model.addAttribute("peminjaman", new Peminjaman());
        model.addAttribute("daftarBuku", bukuService.getAllBuku()); // <- Tambahan
        return "admin-perpustakaan/data-peminjaman";
    }

    @PostMapping("/simpan")
    public String simpanPeminjaman(@ModelAttribute("peminjaman") Peminjaman peminjaman,
                                   BindingResult result,
                                   RedirectAttributes redirectAttributes,
                                   Model model) {
        if (result.hasErrors()) {
            model.addAttribute("daftarBuku", bukuService.getAllBuku());
            return "admin-perpustakaan/data-peminjaman";
        }

        peminjamanService.simpanPeminjaman(peminjaman);

        // Tambahkan flash message
        redirectAttributes.addFlashAttribute("success", "Data peminjaman berhasil disimpan!");
        return "redirect:/admin-perpustakaan/perpus-panel";
    }

    @PostMapping("/hapus/{id}")
    public String hapusPeminjaman(@PathVariable Long id) {
        peminjamanService.hapusPeminjaman(id);
        return "redirect:/admin-perpustakaan/perpus-panel";
    }

    @GetMapping("/semua")
    @ResponseBody
    public List<Peminjaman> getDaftarPeminjaman() {
        return peminjamanService.getAllPeminjaman();
    }
}
