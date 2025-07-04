package web.sekolah.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.model.Guru;
import web.sekolah.repository.GuruRepository;

import java.util.List;

@Controller
@RequestMapping("/profil")
public class ProfilController {

    @Autowired
    private GuruRepository guruRepository;

    @GetMapping("/visi-misi")
    public String visiMisi() {
        return "profil/visi-misi";
    }

    @GetMapping("/sarana-prasarana")
    public String saranaPrasarana() {
        return "profil/sarana-prasarana";
    }

    @GetMapping("/guru-tendik")
    public String tampilGuruTendik(Model model) {
        List<Guru> listGuru = guruRepository.findAll(); // Ambil semua guru dari database
        model.addAttribute("listGuru", listGuru);  // Kirim data ke template
        return "profil/guru-tendik";
    }

}
