package com.example.ogrenciSistem.Controller;

import com.example.ogrenciSistem.Entity.Ogrenci;
import com.example.ogrenciSistem.Repository.OgrenciRepository;
import com.example.ogrenciSistem.Service.OgrenciService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController//Bu sınıf dış dünyadan istekleri karşılayan bir REST API garsonudur.
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/ogrenciler")

public class OgrenciController
{
    @Autowired
    private OgrenciService ogrenciService;
    //1.Kaydetme Kapısı(POSTMAPPİNG)
    // Adres: localhost:8080/api/ogrenciler/kaydet
    @PostMapping("/kaydet")
    public Ogrenci kaydet(@RequestBody Ogrenci yeniOgrenci)
    {return ogrenciService.ogrenciEkle(yeniOgrenci);}

    //2.Listeleme kapısı (GETMAPPİNG)
    //Adres: localhost:8080/api/ogrenciler/liste
    @GetMapping("/liste")
    public List<Ogrenci> listele()
    {
        return ogrenciService.tumOgrencileriGetir();
    }
    @GetMapping("/pasif-liste")
    public List<Ogrenci> pasifListele() {
        return ogrenciService.pasifOgrencileriGetir();
    }

    @PutMapping("/aktif-et/{id}")
    public void aktifEt(@PathVariable Long id) {
        ogrenciService.ogrenciAktifEt(id);
    }

    //3.Güncelleme kapısı(PUTMAPPİNNG)
    //Adres: localhost:8080/api/ogrenciler/guncelle/id
    @PutMapping("/guncelle/{id}")
    public Ogrenci guncelle(@PathVariable Long id,@RequestBody Ogrenci guncelBilgiler)
    {
        return ogrenciService.ogrenciGuncelle(id,guncelBilgiler);
    }

    //4.Silme Kapısı(DELETEMAPPİNG)
    //Adres: localhost:8080/api/ogrenciler/sil/id
    @DeleteMapping("/sil/{id}")
    public void sil(@PathVariable Long id)
    {
        ogrenciService.ogrenciSil(id);
    }

    @GetMapping("/numara/{ogrenciNo}")
    public Ogrenci ogrenciGetirByNo(@PathVariable String ogrenciNo) {
        return ogrenciService.ogrenciGetirByNo(ogrenciNo);
    }
}
