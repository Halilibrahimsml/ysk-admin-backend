package com.example.ogrenciSistem.Repository;

import com.example.ogrenciSistem.Entity.Ders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
// Sihirli kısım burası: JpaRepository'nin yanına <Ders, Long> eklendi!
public interface DersRepository extends JpaRepository<Ders, Long> {
}
