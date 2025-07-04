package web.sekolah.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/adiwiyata")
public class AdiwiyataController {

    @GetMapping("/ipmlh")
    public String Ipmlh() {
        return "adiwiyata/ipmlh";
    }

    @GetMapping("/visi-misi-adiwiyata")
    public String VisiMisiAdiwiyata() {
        return "adiwiyata/visi-misi-adiwiyata";
    }

    @GetMapping("/struktur-adiwiyata")
    public String StrukturAdiwiyata() {
        return "adiwiyata/struktur-adiwiyata";
    }

    @GetMapping("/program-adiwiyata")
    public String ProgramAdiwiyata() {
        return "adiwiyata/program-adiwiyata";
    }

    @GetMapping("/dokumentasi-adiwiyata")
    public String DokumentasiAdiwiyata() {
        return "adiwiyata/dokumentasi-adiwiyata";
    }
}
