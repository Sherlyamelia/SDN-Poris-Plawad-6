package web.sekolah.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import web.sekolah.service.CustomAdminDetailsService;
import org.springframework.beans.factory.annotation.Autowired;

@Configuration
public class SecurityConfig {

    @Autowired
    private CustomAuthenticationSuccessHandler customAuthenticationSuccessHandler;

    @Autowired
    private CustomAdminDetailsService customAdminDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // Halaman publik
                        .requestMatchers("/", "/login", "/index", "/confirm-logout", "/profil/visi-misi", "profil/sarana-prasarana", "profil/guru-tendik",
                                "/prestasi/prestasi-guru", "/prestasi/prestasi-kelas", "/prestasi/prestasi-murid", "/prestasi/prestasi-sekolah",
                                "/adiwiyata/dokumentasi-adiwiyata", "/adiwiyata/ipmlh", "/adiwiyata/program-adiwiyata", "/adiwiyata/struktur-adiwiyata", "/adiwiyata/visi-misi-adiwiyata",
                                "/informasi/berita", "/informasi/perpustakaan", "/informasi/ppdb", "/informasi/sub-berita",
                                "/kesiswaan/ekstrakurikuler", "/kesiswaan/tata-tertib").permitAll()

                        // Static resources
                        .requestMatchers("/static/**", "/css/**", "/js/**", "/img/**").permitAll()

                        .requestMatchers("/guru-tendik.css", "/admin-panel.css", "/berita.css", "/dokumentasi-adiwiyata.css",
                                "/ekstrakurikuler.css", "/index.css", "/ipmlh.css", "/prestasi-guru.css", "/prestasi-sekolah.css",
                                "/prestasi-siswa.css", "/program-adiwiyata.css", "/sarana-prasarana.css", "/struktur-adiwiyata.css",
                                "/sub-berita.css", "/tata-tertib.css", "/visi-misi.css", "/visi-misi-adiwiyata.css").permitAll()

                        // Login dan API publik
                        .requestMatchers("/admin/guru/api/guru/**", "/informasi/sub-berita/**").permitAll()

                        // Admin
                        .requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN_SEKOLAH")
                        .requestMatchers("/admin-perpustakaan/**").hasAuthority("ROLE_ADMIN_PERPUSTAKAAN")

                        // Lainnya
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .successHandler(customAuthenticationSuccessHandler)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout=true")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return customAdminDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);
        builder.userDetailsService(customAdminDetailsService).passwordEncoder(passwordEncoder());
        return builder.build();
    }
}
