package com.example.ogrenciSistem.Service;

import com.example.ogrenciSistem.Entity.Kullanici;
import com.example.ogrenciSistem.Entity.Ogrenci;
import com.example.ogrenciSistem.Repository.KullaniciRepository;
import com.example.ogrenciSistem.Repository.OgrenciRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OgrenciService {
    @Autowired
    private KullaniciRepository kullaniciRepository;

    @Autowired
    private OgrenciRepository ogrenciRepository;

    // 1. Create işlemi (Artık eklenen öğrenciye otomatik giriş hesabı açıyor)
    public Ogrenci ogrenciEkle(Ogrenci yeniOgrenci) {
        Ogrenci kaydedilenOgrenci = ogrenciRepository.save(yeniOgrenci);

        // Yeni öğrenci için otomatik kullanıcı hesabı oluşturma
        Kullanici yeniHesap = new Kullanici();
        yeniHesap.setKullaniciAdi(kaydedilenOgrenci.getOgrenciNo());
        yeniHesap.setSifre("1234"); // Varsayılan ilk şifre
        yeniHesap.setRol("OGRENCI");

        kullaniciRepository.save(yeniHesap);

        return kaydedilenOgrenci;
    }

    // 2. Read işlemi
    public List<Ogrenci> tumOgrencileriGetir() {
        return ogrenciRepository.findByAktifTrue();
    }

    // 3. Update işlemi
    public Ogrenci ogrenciGuncelle(Long id, Ogrenci guncelBilgiler) {
        Ogrenci mevcutOgrenci = ogrenciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        mevcutOgrenci.setIsim(guncelBilgiler.getIsim());
        mevcutOgrenci.setSoyisim(guncelBilgiler.getSoyisim());
        mevcutOgrenci.setOgrenciNo(guncelBilgiler.getOgrenciNo());

        return ogrenciRepository.save(mevcutOgrenci);
    }

    // 4. Listeleme metodu (Sadece aktifler)
    public List<Ogrenci> getOgrenciler() {
        return ogrenciRepository.findByAktifTrue();
    }

    // Silinenleri Listeleme Metodu
    public List<Ogrenci> pasifOgrencileriGetir() {
        return ogrenciRepository.findByAktifFalse();
    }

    // Geri Getirme (Aktif Etme) Metodu
    public void ogrenciAktifEt(Long id) {
        Ogrenci ogrenci = ogrenciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        ogrenci.setAktif(true);
        ogrenciRepository.save(ogrenci);
    }

    // Soft Delete (Pasife Çekme) Metodu
    public void ogrenciSil(Long id) {
        Ogrenci ogrenci = ogrenciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Öğrenci bulunamadı"));

        ogrenci.setAktif(false);
        ogrenciRepository.save(ogrenci);
    }
    public Ogrenci ogrenciGetirByNo(String ogrenciNo) {
        return ogrenciRepository.findByOgrenciNo(ogrenciNo);
    }
}
