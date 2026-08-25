package com.example.ogrenciSistem.Repository;

import com.example.ogrenciSistem.Entity.Kullanici;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface KullaniciRepository extends JpaRepository<Kullanici, Long> {

    // Veritabanında bu kullanıcı adı ve şifreyle eşleşen biri var mı?
    Optional<Kullanici> findByKullaniciAdiAndSifre(String kullaniciAdi, String sifre);


    Kullanici findByKullaniciAdi(String kullaniciAdi);
}