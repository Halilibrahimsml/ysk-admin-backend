package com.example.ogrenciSistem.Repository;

import com.example.ogrenciSistem.Entity.Ogrenci;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OgrenciRepository extends JpaRepository<Ogrenci,Long> {
    // Sadece aktif = true olan öğrencileri listeler
    List<Ogrenci> findByAktifTrue();
    // Sadece silinmiş (aktif = false) olanları getir
    List<Ogrenci> findByAktifFalse();
    Ogrenci findByOgrenciNo(String ogrenciNo);
}
