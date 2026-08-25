package com.example.ogrenciSistem.Repository;

import com.example.ogrenciSistem.Entity.DersNotu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository

public interface DersNotuRepository extends JpaRepository<DersNotu, Long> {

    List<DersNotu> findByOgrenci_OgrenciNo(String ogrenciNo);

}