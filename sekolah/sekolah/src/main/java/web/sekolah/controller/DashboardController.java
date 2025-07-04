package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.model.Berita;
import web.sekolah.service.BeritaService;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/")
public class DashboardController {

    @Autowired
    private BeritaService beritaService;
    private static final String UPLOAD_DIR = "src/main/resources/static/img/berita/";

    @GetMapping("/")
    public String dashboard(Model model) {
        List<Berita> semuaBerita = beritaService.getAll(); // atau findAll() sesuai implementasimu

        // Sort by tanggal descending (jika belum otomatis)
        semuaBerita.sort(Comparator.comparing(Berita::getTanggal).reversed());

        // Ambil 5 berita terbaru
        List<Berita> beritaTerbaru = semuaBerita.stream()
                .limit(3)
                .collect(Collectors.toList());

        model.addAttribute("beritaTerbaru", beritaTerbaru);
        return "index";
        }
}
