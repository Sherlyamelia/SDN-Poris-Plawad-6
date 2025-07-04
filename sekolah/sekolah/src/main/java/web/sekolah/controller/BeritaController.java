package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Berita;
import web.sekolah.service.BeritaService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping("/admin/berita")
public class BeritaController {

    @Autowired
    private BeritaService beritaService;
    private static final String UPLOAD_DIR = "src/main/resources/static/img/berita/";

    @GetMapping("/data-berita")
    public String showAll(Model model) {
        List<Berita> listBerita = beritaService.getAll();
        model.addAttribute("listBerita", listBerita);
        return "admin/berita/data-berita"; // tampilkan daftar berita
    }

    @GetMapping("/create-berita")
    public String showCreateForm(Model model) {
        model.addAttribute("berita", new Berita());
        return "admin/berita/create-berita";
    }

    @PostMapping("/save")
    public String saveBerita(@RequestParam(value = "id", required = false) Long id,
                             @RequestParam("judul") String judul,
                             @RequestParam("subjudul") String subjudul,
                             @RequestParam("deskripsi") String deskripsi,
                             @RequestParam("tanggal") String tanggal,
                             @RequestParam("penulis") String penulis,
                             @RequestParam("foto") MultipartFile fotoFile,
                             RedirectAttributes redirectAttributes) throws IOException {

        LocalDate localDate = LocalDate.parse(tanggal);

        Berita berita;

        if (id != null) {
            // Update data lama
            berita = beritaService.findById(id);
            if (berita == null) {
                throw new IllegalArgumentException("Berita dengan ID " + id + " tidak ditemukan");
            }
        } else {
            // Buat berita baru
            berita = new Berita();
        }

        berita.setJudul(judul);
        berita.setSubjudul(subjudul);
        berita.setDeskripsi(deskripsi);
        berita.setTanggal(localDate);
        berita.setPenulis(penulis);

        // Jika ada foto baru yang diupload
        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + fotoFile.getOriginalFilename();
            Path path = Paths.get("C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/berita/" + fileName);
            Files.write(path, fotoFile.getBytes());
            berita.setFoto(fileName);
        } else {
            // Jika tidak ada foto baru, gunakan foto lama jika ada
            if (berita.getId() != null) {
                Berita existing = beritaService.findById(berita.getId());
                if (existing != null) {
                    berita.setFoto(existing.getFoto());  // Gunakan foto lama
                }
            }
        }

        // Simpan berita
        beritaService.save(berita);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/berita/data-berita";  // Redirect ke halaman data berita
    }

    @GetMapping("/edit/{id}")
    public String editBerita(@PathVariable("id") Long id, Model model) {
        Berita berita = beritaService.findById(id);
        if (berita == null) {
            return "redirect:/admin/berita/data-berita"; // kalau data tidak ditemukan
        }
        model.addAttribute("berita", berita);
        return "admin/berita/edit-berita"; // pastikan file HTML-nya ada
    }

    @PostMapping("/edit/{id}")
    public String updateBerita(@PathVariable("id") Long id,
                               @ModelAttribute Berita berita,
                               @RequestParam("file") MultipartFile file,
                               @RequestParam("fotoLama") String fotoLama,
                               RedirectAttributes redirectAttributes) {

        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            try {
                String uploadDir = "C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/berita/";
                Path uploadPath = Paths.get(uploadDir);
                if (!Files.exists(uploadPath)) {
                    Files.createDirectories(uploadPath);
                }
                InputStream inputStream = file.getInputStream();
                Path filePath = uploadPath.resolve(fileName);
                Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
                berita.setFoto(fileName); // Foto baru
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            berita.setFoto(fotoLama); // Pertahankan foto lama
        }

        beritaService.save(berita);
        redirectAttributes.addAttribute("updated", "true");
        return "redirect:/admin/berita/data-berita"; // Redirect sesuai kebutuhan
    }

    @PostMapping("/update")
    public String updateBerita(@RequestParam("id") Long id,
                               @RequestParam("judul") String judul,
                               @RequestParam("subjudul") String subjudul,
                               @RequestParam("deskripsi") String deskripsi,
                               @RequestParam("tanggal") String tanggal,
                               @RequestParam("penulis") String penulis,
                               @RequestParam(value = "foto", required = false) MultipartFile foto,
                               RedirectAttributes redirectAttributes) {

        // Ambil berita berdasarkan ID
        Berita berita = beritaService.findById(id);
        if (berita == null) {
            return "redirect:/admin/berita/data-berita"; // Jika tidak ditemukan
        }

        // Set data lainnya (judul, deskripsi, dll)
        berita.setJudul(judul);
        berita.setSubjudul(subjudul);
        berita.setDeskripsi(deskripsi);
        berita.setTanggal(LocalDate.parse(tanggal));
        berita.setPenulis(penulis);

        // Jika ada foto baru yang diupload, proses foto baru
        if (foto != null && !foto.isEmpty()) {
            String fotoPath = System.currentTimeMillis() + "_" + foto.getOriginalFilename(); // Ganti nama file untuk menghindari duplikasi
            File destinationFile = new File("C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/berita/" + fotoPath);

            try {
                // Pastikan direktori ada
                destinationFile.getParentFile().mkdirs();

                // Transfer file ke lokasi tujuan
                foto.transferTo(destinationFile);

                // Set path relatif untuk foto di database (gunakan path relatif)
                berita.setFoto("img/berita/" + fotoPath);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            // Jika tidak ada foto baru, gunakan foto lama
            if (berita.getFoto() != null) {
                // Foto lama tetap dipertahankan
                berita.setFoto(berita.getFoto());
            }
        }

        // Simpan berita yang telah diperbarui
        beritaService.save(berita);
        redirectAttributes.addAttribute("updated", "true");
        return "redirect:/admin/berita/data-berita"; // Redirect ke halaman data berita
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable Long id) {
        beritaService.delete(id);
        return "redirect:/admin/berita/data-berita";
    }
}

