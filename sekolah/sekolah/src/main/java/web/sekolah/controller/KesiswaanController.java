package web.sekolah.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.sekolah.service.EkstrakurikulerService;

@Controller
@RequestMapping("/kesiswaan")
public class KesiswaanController {

    @Autowired
    private EkstrakurikulerService eskulService;

    @GetMapping("/tata-tertib")
    public String tataTertib() {
        return "kesiswaan/tata-tertib";
    }

    @GetMapping("/ekstrakurikuler")
    public String showEskulToPublic(Model model) {
        model.addAttribute("listEskul", eskulService.getAll());
        return "kesiswaan/ekstrakurikuler"; // HTML publik
    }

}
