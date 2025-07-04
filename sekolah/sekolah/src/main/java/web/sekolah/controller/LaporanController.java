package web.sekolah.controller;

import web.sekolah.model.Laporan;
import web.sekolah.service.LaporanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Controller
@RequestMapping("/admin-perpustakaan")
public class LaporanController {

    @Autowired
    private LaporanService laporanService;

    // Sesuaikan folder upload untuk foto buku
    private final String UPLOAD_DIR = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/laporanbuku";

    @GetMapping("/laporan")
    public String listLaporan(Model model) {
        List<Laporan> laporans = laporanService.getAllLaporan();
        int totalQty = laporans.stream().mapToInt(Laporan::getQty).sum();

        model.addAttribute("laporanList", laporans);
        model.addAttribute("totalQty", totalQty);
        return "admin-perpustakaan/laporan";
    }

    @GetMapping("/tambah-laporan")
    public String showFormTambah(Model model) {
        model.addAttribute("laporan", new Laporan());
        model.addAttribute("keteranganList", List.of("Rusak", "Hilang"));
        return "admin-perpustakaan/tambah-laporan";
    }

    @PostMapping("/simpan-laporan")
    public String simpanLaporan(@ModelAttribute Laporan laporan,
                                @RequestParam("fotoFile") MultipartFile fotoFile) throws IOException {

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(fotoFile.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(fotoFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            laporan.setFoto(fileName);
        }

        laporanService.save(laporan);
        return "redirect:/admin-perpustakaan/laporan";
    }

    @GetMapping("/laporan/edit/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Laporan laporan = laporanService.getById(id);
        model.addAttribute("laporan", laporan);
        model.addAttribute("keteranganList", List.of("Rusak", "Hilang"));
        return "admin-perpustakaan/edit-laporan";
    }

    @PostMapping("/update-laporan")
    public String updateLaporan(@ModelAttribute Laporan laporan,
                                @RequestParam("fotoFile") MultipartFile fotoFile) throws IOException {

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(fotoFile.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(fotoFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            laporan.setFoto(fileName);
        } else {
            // Jika foto tidak diupload, ambil foto lama dari database
            Laporan existing = laporanService.getById(laporan.getId());
            laporan.setFoto(existing.getFoto());
        }

        laporanService.save(laporan);
        return "redirect:/admin-perpustakaan/laporan";
    }

    @GetMapping("/laporan/delete/{id}")
    public String deleteLaporan(@PathVariable Long id) {
        laporanService.delete(id);
        return "redirect:/admin-perpustakaan/laporan";
    }
}
