package web.sekolah.controller;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.PrestasiGuru;
import web.sekolah.service.PrestasiGuruService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
@RequestMapping("/admin/prestasi/guru")
public class PrestasiGuruController {

    @Autowired
    private PrestasiGuruService prestasiGuruService;

    // Menampilkan semua data
    @GetMapping("/dapres-guru")
    public String listPrestasiGuru(Model model) {
        model.addAttribute("daftarPrestasi", prestasiGuruService.getAllPrestasiGuru());
        return "admin/prestasi/guru/dapres-guru";
    }

    // Form tambah data
    @GetMapping("/create-gupres")
    public String showFormTambah(Model model) {
        model.addAttribute("prestasiGuru", new PrestasiGuru());
        return "admin/prestasi/guru/create-gupres";
    }

    @PostMapping("/simpan")
    public String simpanPrestasi(@ModelAttribute PrestasiGuru prestasiGuru,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            try {
                String namaFile = file.getOriginalFilename();
                String pathUpload = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/prestasi-guru";
                File folder = new File(pathUpload);
                if (!folder.exists()) {
                    folder.mkdirs(); // Membuat folder jika belum ada
                }
                file.transferTo(new File(pathUpload + "/" + namaFile));
                prestasiGuru.setFoto(namaFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Ambil data lama jika foto tidak diganti
            if (prestasiGuru.getId() != null) {
                PrestasiGuru dataLama = prestasiGuruService.getById(prestasiGuru.getId());
                prestasiGuru.setFoto(dataLama.getFoto());
            }
        }

        prestasiGuruService.savePrestasiGuru(prestasiGuru);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/prestasi/guru/dapres-guru";
    }

    @GetMapping("/edit-gupres/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        PrestasiGuru prestasiGuru = prestasiGuruService.getById(id);
        model.addAttribute("prestasiGuru", prestasiGuru);
        redirectAttributes.addAttribute("updated", "true");
        return "admin/prestasi/guru/edit-gupres";
    }


    // Hapus data
    @GetMapping("/hapus/{id}")
    public String hapusPrestasi(@PathVariable Long id) {
        prestasiGuruService.deletePrestasiGuru(id);
        return "redirect:/admin/prestasi/guru/dapres-guru";
    }
}
