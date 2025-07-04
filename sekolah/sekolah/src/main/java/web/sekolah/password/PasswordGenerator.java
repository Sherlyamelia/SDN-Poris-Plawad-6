package web.sekolah.password;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String adminPassword = "adminporis";
        String perpustakaanPassword = "perpuspp6";

        String hashedAdmin = encoder.encode(adminPassword);
        String hashedPerpus = encoder.encode(perpustakaanPassword);

        System.out.println("Hashed password for adminporis      : " + hashedAdmin);
        System.out.println("Hashed password for perpuspp6: " + hashedPerpus);
    }
}
