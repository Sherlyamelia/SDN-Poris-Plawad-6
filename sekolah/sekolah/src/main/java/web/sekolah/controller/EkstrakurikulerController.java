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
import web.sekolah.model.Ekstrakurikuler;
import web.sekolah.service.EkstrakurikulerService;

import java.util.List;

@Controller
@RequestMapping("/admin/eskul")
public class EkstrakurikulerController {

    @Autowired
    private EkstrakurikulerService eskulService;

    @GetMapping("/data-eskul")
    public String showAllEskul(Model model) {
        List<Ekstrakurikuler> listEskul = eskulService.getAll();
        model.addAttribute("listEskul", listEskul);
        return "admin/eskul/data-eskul";
    }

    @GetMapping("/create-eskul")
    public String showCreateForm(Model model) {
        model.addAttribute("ekstrakurikuler", new Ekstrakurikuler());
        return "admin/eskul/create-eskul";
    }

    @PostMapping("/save")
    public String saveEskul(@Valid @ModelAttribute("ekstrakurikuler") Ekstrakurikuler eskul,
                            BindingResult result,
                            @RequestParam("foto") MultipartFile fotoFile,
                            Model model,
                            RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            return "admin/eskul/create-eskul";
        }

        eskulService.save(eskul, fotoFile);
        redirectAttributes.addAttribute("saved", "true");
        return "redirect:/admin/eskul/data-eskul";
    }

    // Tampilkan form edit
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
        Ekstrakurikuler eskul = eskulService.getById(id);
        model.addAttribute("ekstrakurikuler", eskul);
        return "admin/eskul/edit-eskul"; // pastikan file ini ada
    }

    // Proses update data
    @PostMapping("/edit/{id}")
    public String updateEskul(@PathVariable("id") Long id,
                              @ModelAttribute Ekstrakurikuler eskul,
                              @RequestParam("foto") MultipartFile fotoFile,
                              RedirectAttributes redirectAttributes) {
        eskulService.update(id, eskul, fotoFile);
        redirectAttributes.addAttribute("updated", "true");
        return "redirect:/admin/eskul/data-eskul";
    }

    // Hapus data
    @GetMapping("/delete/{id}")
    public String deleteEskul(@PathVariable("id") Long id) {
        eskulService.delete(id);
        return "redirect:/admin/eskul/data-eskul";
    }


    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setDisallowedFields("foto"); // Mencegah binding MultipartFile ke field 'foto' di model
    }

}
