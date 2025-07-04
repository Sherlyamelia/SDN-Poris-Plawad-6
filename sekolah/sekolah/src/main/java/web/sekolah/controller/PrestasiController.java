package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.model.PrestasiKelas;
import web.sekolah.model.PrestasiSekolah;
import web.sekolah.model.PrestasiSiswa;
import web.sekolah.service.PrestasiGuruService;
import web.sekolah.service.PrestasiSekolahService;
import web.sekolah.service.PrestasiSiswaService;
import web.sekolah.service.PrestasiKelasService;

import java.util.List;

@Controller
@RequestMapping("/prestasi")
public class PrestasiController {

    private final PrestasiSiswaService prestasiSiswaService;
    private final PrestasiSekolahService prestasiSekolahService;
    private final PrestasiKelasService prestasiKelasService;

    @Autowired
    private PrestasiGuruService prestasiGuruService;

    public PrestasiController(PrestasiSiswaService prestasiSiswaService,
                              PrestasiSekolahService prestasiSekolahService,
                              PrestasiKelasService prestasiKelasService) {
        this.prestasiSiswaService = prestasiSiswaService;
        this.prestasiSekolahService = prestasiSekolahService;
        this.prestasiKelasService = prestasiKelasService;
    }

    @GetMapping("/prestasi-sekolah")
    public String PrestasiSekolah(Model model) {
        List<PrestasiSekolah> daftarPrestasi = prestasiSekolahService.getAllPrestasiSekolah();
        model.addAttribute("daftarPrestasi", daftarPrestasi);
        return "prestasi/prestasi-sekolah";
    }

    @GetMapping("/prestasi-guru")
    public String lihatPrestasiGuru(Model model) {
        model.addAttribute("daftarPrestasi", prestasiGuruService.getAllPrestasiGuru());
        return "prestasi/prestasi-guru"; // HTML-nya simpan di templates/user/prestasi-guru.html
    }

    @GetMapping("/prestasi-murid")
    public String PrestasiMurid(Model model) {
        List<PrestasiSiswa> daftarPrestasi = prestasiSiswaService.getAllPrestasiSiswa();
        model.addAttribute("daftarPrestasi", daftarPrestasi);
        return "prestasi/prestasi-murid";
    }

    @GetMapping("/prestasi-kelas")
    public String PrestasiKelas(Model model) {
        List<PrestasiKelas> daftarPrestasi = prestasiKelasService.getAllPrestasiKelas();
        model.addAttribute("daftarPrestasi", daftarPrestasi);
        return "prestasi/prestasi-kelas";
    }
}
