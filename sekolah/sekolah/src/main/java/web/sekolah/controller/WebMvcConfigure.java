package web.sekolah.controller;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfigure implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // Konfigurasi untuk folder guru
        registry.addResourceHandler("/img/guru/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/guru/")
                .setCachePeriod(0); // Cache di-disable agar gambar langsung terupdate

        // Konfigurasi untuk folder ekstrakurikuler
        registry.addResourceHandler("/img/ekstrakurikuler/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/ekstrakurikuler/")
                .setCachePeriod(0); // Cache di-disable agar gambar langsung terupdate

        // Konfigurasi untuk folder berita
        registry.addResourceHandler("/img/berita/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/berita/")
                .setCachePeriod(0); // Cache di-disable agar gambar langsung terupdate

        // Konfigurasi untuk folder berita

        registry.addResourceHandler("/img/prestasi-guru/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/prestasi-guru/")
                .setCachePeriod(0); // Cache di-disable agar gambar langsung terupdate

        registry.addResourceHandler("/img/buku/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/buku/")
                .setCachePeriod(0);

        registry.addResourceHandler("/img/prestasi-siswa/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/prestasi-siswa/")
                .setCachePeriod(0);

        registry.addResourceHandler("/img/prestasi-sekolah/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/prestasi-sekolah/")
                .setCachePeriod(0);

        registry.addResourceHandler("/img/laporanbuku/**")
                .addResourceLocations("file:C:/Tugas Akhir/sekolah/sekolah/src/main/resources/static/img/laporanbuku/")
                .setCachePeriod(0);
    }
}

