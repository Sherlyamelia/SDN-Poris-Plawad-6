package web.sekolah.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import web.sekolah.model.Guru;
import web.sekolah.repository.GuruRepository;
import web.sekolah.service.GuruService;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

@Controller
@RequestMapping("/admin/guru")
public class GuruController {

    private final GuruService guruService;

    // Constructor injection (disarankan)
    @Autowired
    private GuruRepository guruRepository;
    private static final String UPLOAD_DIR = "src/main/resources/static/img/guru/";

    public GuruController(GuruService guruService) {
        this.guruService = guruService;
    }

    @GetMapping("/data-guru")
    public String DataGuru(Model model) {
        List<Guru> guruList = guruService.getAllGuru();
        model.addAttribute("guruList", guruList);
        return "admin/guru/data-guru";
    }

    @GetMapping("/create-guru")
    public String createGuru(Model model) {
        model.addAttribute("guru", new Guru()); // kirim objek kosong biar gak null
        return "admin/guru/create-guru";
    }

    @GetMapping("/edit/{id}")
    public String editGuru(@PathVariable Long id, Model model, RedirectAttributes redirectAttributes) {
        Guru guru = guruService.findById(id);
        model.addAttribute("guru", guru);
        redirectAttributes.addAttribute("updated", "true");
        return "admin/guru/edit-guru";
    }

    @PostMapping("/save")
    public String saveGuru(@Valid @ModelAttribute Guru guru,
                           BindingResult result,
                           @RequestParam("foto") MultipartFile fotoFile,
                           RedirectAttributes redirectAttributes) throws IOException {
        if (result.hasErrors()) {
            // Jika ada error validasi, tampilkan kembali form dengan pesan error
            return "admin/guru/create-guru";  // Atau halaman edit-guru
        }

        if (!fotoFile.isEmpty()) {
            String fileName = System.currentTimeMillis() + "_" + fotoFile.getOriginalFilename();
            Path path = Paths.get("C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/guru/" + fileName);
            Files.write(path, fotoFile.getBytes());
            guru.setFoto(fileName);  // Set nama file (path relatif) ke field foto
        } else {
            if (guru.getId() != null) {
                Guru existing = guruRepository.findById(guru.getId()).orElse(null);
                if (existing != null) {
                    guru.setFoto(existing.getFoto());  // Gunakan foto lama
                }
            }
        }

        guruRepository.save(guru);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/guru/data-guru";  // Redirect ke halaman data guru
    }

    @GetMapping("/delete/{id}")
    public String deleteGuru(@PathVariable Long id) {
        guruService.deleteGuruById(id);
        return "redirect:/admin/guru/data-guru";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("foto"); // Mencegah binding MultipartFile ke field 'foto' di model
    }


}
