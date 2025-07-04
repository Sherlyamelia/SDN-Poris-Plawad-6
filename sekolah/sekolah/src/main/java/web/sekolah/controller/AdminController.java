package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.model.Berita;
import web.sekolah.model.Ekstrakurikuler;
import web.sekolah.repository.BeritaRepository;
import web.sekolah.service.*;

import java.util.Map;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


import java.security.Principal;
import java.util.*;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final GuruService guruService;
    private final PrestasiGuruService prestasiGuruService;
    private final PrestasiSiswaService prestasiSiswaService;
    private final PrestasiSekolahService prestasiSekolahService;
    private final BeritaRepository beritaRepository;
    private final EkstrakurikulerService ekstrakurikulerService;

    @Autowired
    public AdminController(
            GuruService guruService,
            PrestasiGuruService prestasiGuruService,
            PrestasiSiswaService prestasiSiswaService,
            PrestasiSekolahService prestasiSekolahService,
            BeritaRepository beritaRepository,
            EkstrakurikulerService ekstrakurikulerService) {
        this.guruService = guruService;
        this.prestasiGuruService = prestasiGuruService;
        this.prestasiSiswaService = prestasiSiswaService;
        this.prestasiSekolahService = prestasiSekolahService;
        this.beritaRepository = beritaRepository;
        this.ekstrakurikulerService = ekstrakurikulerService;
    }

    @GetMapping("/admin-panel")
    public String AdminPanel(Model model, Principal principal) {
        model.addAttribute("username", principal.getName());

        // Ambil data prestasi per tahun
        Map<String, Long> guruMapString = prestasiGuruService.countPerTahun();
        Map<String, Long> siswaMapString = prestasiSiswaService.countPerTahun();
        Map<String, Long> sekolahMapString = prestasiSekolahService.countPerTahun();

        Set<String> tahunSet = new TreeSet<>();
        tahunSet.addAll(guruMapString.keySet());
        tahunSet.addAll(siswaMapString.keySet());
        tahunSet.addAll(sekolahMapString.keySet());

        List<String> tahunLabels = tahunSet.stream()
                .map(String::valueOf)
                .collect(Collectors.toList());

        List<Long> dataGuru = tahunSet.stream()
                .map(tahun -> guruMapString.getOrDefault(tahun, 0L))
                .collect(Collectors.toList());

        List<Long> dataSiswa = tahunSet.stream()
                .map(tahun -> siswaMapString.getOrDefault(tahun, 0L))
                .collect(Collectors.toList());

        List<Long> dataSekolah = tahunSet.stream()
                .map(tahun -> sekolahMapString.getOrDefault(tahun, 0L))
                .collect(Collectors.toList());

        model.addAttribute("tahunLabels", tahunLabels);
        model.addAttribute("dataGuru", dataGuru);
        model.addAttribute("dataSiswa", dataSiswa);
        model.addAttribute("dataSekolah", dataSekolah);

        // Jumlah entitas
        model.addAttribute("totalGuru", guruService.count());
        model.addAttribute("totalGuruLaki", guruService.countByJenisKelamin("Laki-laki"));
        model.addAttribute("totalGuruPerempuan", guruService.countByJenisKelamin("Perempuan"));

        // berita terbanyak dibaca
        List<Berita> beritaPopuler = beritaRepository.findTop5ByOrderByViewsDesc();
        model.addAttribute("beritaPopuler", beritaPopuler);

        //daftar ekstrakurikuler
        long Wajib = ekstrakurikulerService.countByKategori("Wajib");
        long Pilihan = ekstrakurikulerService.countByKategori("Pilihan");

        model.addAttribute("totalWajib", Wajib);
        model.addAttribute("totalPilihan", Pilihan);
        model.addAttribute("totalEkstrakurikuler", ekstrakurikulerService.count());

        return "admin/admin-panel";
    }

}
