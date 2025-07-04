package web.sekolah.controller;

import web.sekolah.model.Buku;
import web.sekolah.service.BukuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Arrays;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin-perpustakaan")
public class BukuController {

    @Autowired
    private BukuService bukuService;

    private final String UPLOAD_DIR = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/buku";

    // Daftar kategori default
    private List<String> kategoriList() {
        return Arrays.asList("Novel", "Ensiklopedia", "Pelajaran", "Biografi", "Dongeng", "Lainnya");
    }

    @GetMapping("/data-buku")
    public String listBuku(@RequestParam(value = "kategori", required = false) String kategori, Model model) {
        List<Buku> listBuku;
        if (kategori != null && !kategori.equalsIgnoreCase("Semua")) {
            listBuku = bukuService.findByKategori(kategori);
        } else {
            listBuku = bukuService.getAllBuku();
        }

        int totalQty = listBuku.stream().mapToInt(Buku::getQty).sum();

        model.addAttribute("listBuku", listBuku);
        model.addAttribute("totalQty", totalQty);
        model.addAttribute("kategoriList", kategoriList());
        model.addAttribute("selectedKategori", kategori != null ? kategori : "Semua");

        return "admin-perpustakaan/data-buku";
    }

    @GetMapping("/tambah-buku")
    public String showFormTambah(Model model) {
        model.addAttribute("buku", new Buku());
        model.addAttribute("kategoriList", kategoriList());
        return "admin-perpustakaan/tambah-buku";
    }

    @PostMapping("/simpan-buku")
    public String simpanBuku(@ModelAttribute Buku buku,
                             @RequestParam("fotoFile") MultipartFile fotoFile,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(fotoFile.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(fotoFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            buku.setFoto(fileName);
        }

        bukuService.save(buku);
        redirectAttributes.addFlashAttribute("notif", "simpan");
        return "redirect:/admin-perpustakaan/data-buku?saved=true";
    }

    @GetMapping("/edit-buku/{id}")
    public String showFormEdit(@PathVariable Long id, Model model) {
        Buku buku = bukuService.getById(id);
        model.addAttribute("buku", buku);
        model.addAttribute("kategoriList", kategoriList());
        return "admin-perpustakaan/edit-buku";
    }

    @PostMapping("/update-buku")
    public String updateBuku(@ModelAttribute Buku buku,
                             @RequestParam("fotoFile") MultipartFile fotoFile,
                             RedirectAttributes redirectAttributes) throws IOException {

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + StringUtils.cleanPath(fotoFile.getOriginalFilename());
            Path uploadPath = Paths.get(UPLOAD_DIR);

            if (!Files.exists(uploadPath)) {
                Files.createDirectories(uploadPath);
            }

            Files.copy(fotoFile.getInputStream(), uploadPath.resolve(fileName), StandardCopyOption.REPLACE_EXISTING);
            buku.setFoto(fileName);
        } else {
            Buku existing = bukuService.getById(buku.getId());
            buku.setFoto(existing.getFoto());
        }

        bukuService.save(buku);
        redirectAttributes.addFlashAttribute("notif", "update");
        return "redirect:/admin-perpustakaan/data-buku?updated=true";
    }

    @GetMapping("/hapus-buku/{id}")
    public String hapusBuku(@PathVariable Long id, RedirectAttributes redirectAttributes) {
        bukuService.delete(id);
        redirectAttributes.addFlashAttribute("notif", "hapus");
        return "redirect:/admin-perpustakaan/data-buku";
    }
}
