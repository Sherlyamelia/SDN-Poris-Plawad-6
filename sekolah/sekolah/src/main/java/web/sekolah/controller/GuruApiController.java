package web.sekolah.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.sekolah.model.Guru;
import web.sekolah.service.GuruService;

@RestController
@RequestMapping("/admin/guru/api/guru")
public class GuruApiController {

    @Autowired
    private GuruService guruService;

    @GetMapping("/{id}")
    public ResponseEntity<Guru> getGuruById(@PathVariable Long id) {
        Guru guru = guruService.findById(id);
        if (guru != null) {
            return ResponseEntity.ok(guru);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}

