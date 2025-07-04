package web.sekolah.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import web.sekolah.model.Berita;

import java.util.List;

public interface BeritaRepository extends JpaRepository<Berita, Long> {
    List<Berita> findTop5ByOrderByViewsDesc();
}

