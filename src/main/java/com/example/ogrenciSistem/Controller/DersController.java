package com.example.ogrenciSistem.Controller;

import com.example.ogrenciSistem.Entity.Ders;
import com.example.ogrenciSistem.Repository.DersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dersler")
@CrossOrigin(origins = "http://localhost:4200")
public class DersController {

    @Autowired
    private DersRepository dersRepository;

    // Angular bu adrese istek atıp tüm ders havuzunu çekecek
    @GetMapping("/tum-dersler")
    public List getTumDersler() {
        return dersRepository.findAll();
    }
    @PostMapping("/ekle")
    public Ders dersEkle(@RequestBody Ders yeniDers) {
        return dersRepository.save(yeniDers);
    }
}
