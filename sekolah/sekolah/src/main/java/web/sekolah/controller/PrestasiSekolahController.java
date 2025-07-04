package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.PrestasiSekolah;
import web.sekolah.service.PrestasiSekolahService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/admin/prestasi/sekolah")
public class PrestasiSekolahController {

    @Autowired
    private PrestasiSekolahService prestasiSekolahService;

    // Menampilkan semua data
    @GetMapping("/dapres-sekolah")
    public String listPrestasiSekolah(Model model) {
        model.addAttribute("daftarPrestasi", prestasiSekolahService.getAllPrestasiSekolah());
        return "admin/prestasi/sekolah/dapres-sekolah";
    }

    // Form tambah data
    @GetMapping("/create-sepres")
    public String showFormTambah(Model model) {
        model.addAttribute("prestasiSekolah", new PrestasiSekolah());
        return "admin/prestasi/sekolah/create-sepres";
    }

    @PostMapping("/simpan")
    public String simpanPrestasi(@ModelAttribute PrestasiSekolah prestasiSekolah,
                                 @RequestParam("file") MultipartFile file,
                                 RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            try {
                String namaFile = file.getOriginalFilename();
                String pathUpload = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/prestasi-sekolah";
                File folder = new File(pathUpload);
                if (!folder.exists()) {
                    folder.mkdirs(); // Membuat folder jika belum ada
                }
                file.transferTo(new File(pathUpload + "/" + namaFile));
                prestasiSekolah.setFoto(namaFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Ambil data lama jika foto tidak diganti
            if (prestasiSekolah.getId() != null) {
                PrestasiSekolah dataLama = prestasiSekolahService.getById(prestasiSekolah.getId());
                prestasiSekolah.setFoto(dataLama.getFoto());
            }
        }

        prestasiSekolahService.savePrestasiSekolah(prestasiSekolah);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/prestasi/sekolah/dapres-sekolah";
    }

    @GetMapping("/edit-sepres/{id}")
    public String editForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        PrestasiSekolah prestasiSekolah = prestasiSekolahService.getById(id);
        model.addAttribute("prestasiSekolah", prestasiSekolah);
        redirectAttributes.addAttribute("updated", "true");
        return "admin/prestasi/sekolah/edit-sepres";
    }


    // Hapus data
    @GetMapping("/hapus/{id}")
    public String hapusPrestasi(@PathVariable Long id) {
        prestasiSekolahService.deletePrestasiSekolah(id);
        return "redirect:/admin/prestasi/sekolah/dapres-sekolah";
    }
}
