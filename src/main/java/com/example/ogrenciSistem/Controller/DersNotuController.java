package com.example.ogrenciSistem.Controller;

import com.example.ogrenciSistem.Entity.DersNotu;
import com.example.ogrenciSistem.Repository.DersNotuRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/notlar")
@CrossOrigin(origins = "http://localhost:4200") // Angular'dan (4200) gelen isteklere izin ver (CORS)
public class DersNotuController {

    @Autowired
    private DersNotuRepository dersNotuRepository;

    // Angular bu adrese istek atacak: localhost:8080/api/notlar/ogrenci/230229035
    @GetMapping("/ogrenci/{ogrenciNo}")
    public List getOgrenciNotlari(@PathVariable String ogrenciNo) {
        return dersNotuRepository.findByOgrenci_OgrenciNo(ogrenciNo);
    }
    // Dışarıdan (Angular veya Postman) gelen yeni not verisini kaydetme uç noktası
    @PostMapping("/ekle")
    public DersNotu notEkle(@RequestBody DersNotu yeniNot) {
        // .save() komutu çalıştığı an, Java bizim 'notlariHesapla' metodunu tetikleyecek!
        return dersNotuRepository.save(yeniNot);
    }
}
