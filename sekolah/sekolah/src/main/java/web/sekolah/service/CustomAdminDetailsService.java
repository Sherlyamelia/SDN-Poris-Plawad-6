package web.sekolah.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;
import web.sekolah.model.Admin;
import web.sekolah.repository.AdminRepository;

import java.util.Collections;

@Service
public class CustomAdminDetailsService implements UserDetailsService {

    @Autowired
    private AdminRepository adminRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Admin not found"));

        System.out.println("Found admin: " + admin.getUsername() + ", Role: " + admin.getRole());

        // Menggunakan SimpleGrantedAuthority untuk authority
        return org.springframework.security.core.userdetails.User.builder()
                .username(admin.getUsername())
                .password(admin.getPassword())
                .authorities("ROLE_" + admin.getRole())  // Hanya satu ROLE_ di sini
                .build();
    }
}
