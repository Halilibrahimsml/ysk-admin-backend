package com.example.ogrenciSistem.Controller;

import com.example.ogrenciSistem.Entity.Kullanici;
import com.example.ogrenciSistem.Repository.KullaniciRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@CrossOrigin(origins = "http://localhost:4200") // Angular'dan gelen isteklere izin veriyoruz
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final KullaniciRepository kullaniciRepository;

    public AuthController(KullaniciRepository kullaniciRepository) {
        this.kullaniciRepository = kullaniciRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Kullanici loginIstegi) {
        // Gelen kullanıcı adı ve şifreyi veritabanında arıyoruz
        Optional<Kullanici> kullaniciOpt = kullaniciRepository.findByKullaniciAdiAndSifre(
                loginIstegi.getKullaniciAdi(),
                loginIstegi.getSifre()
        );

        // Eğer veritabanında böyle biri varsa
        if (kullaniciOpt.isPresent()) {
            Kullanici mevcutKullanici = kullaniciOpt.get();
            Map<String, String> response = new HashMap<>();
            response.put("mesaj", "Giriş başarılı");
            response.put("rol", mevcutKullanici.getRol());

            return ResponseEntity.ok(response);
        } else {
            // Bulamazsa 401 Unauthorized (Yetkisiz) hatası dönüyoruz
            return ResponseEntity.status(401).body("Hata: Kullanıcı adı veya şifre yanlış");
        }
    }

    // =========================================================

    @PostMapping("/sifre-sifirla")
    public ResponseEntity<?> sifreSifirla(@RequestBody Map<String, String> request) {
        String kullaniciAdi = request.get("kullaniciAdi");
        String yeniSifre = request.get("yeniSifre");

        Kullanici kullanici = kullaniciRepository.findByKullaniciAdi(kullaniciAdi);

        if (kullanici != null) {
            // YENİ: Eski şifre ile yeni şifre aynı mı kontrolü
            if (kullanici.getSifre().equals(yeniSifre)) {
                return ResponseEntity.badRequest().body("Yeni şifreniz eski şifrenizle aynı olamaz!");
            }

            kullanici.setSifre(yeniSifre);
            kullaniciRepository.save(kullanici);
            return ResponseEntity.ok().body("Şifre başarıyla güncellendi.");
        } else {
            return ResponseEntity.badRequest().body("Girdiğiniz öğrenci numarası sistemde bulunamadı!");
        }
    }

}