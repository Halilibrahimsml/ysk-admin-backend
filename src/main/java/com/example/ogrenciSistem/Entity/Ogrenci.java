package com.example.ogrenciSistem.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data //getter setter metodları yazmamıza gerek kalmaz.
public class Ogrenci {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)


    private Long id;
    private String isim;
    private String soyisim;
    @Column(name = "ogrenci_no", unique = true)
    private String ogrenciNo;
    // Yeni eklenen alan: Öğrenci ilk eklendiğinde varsayılan olarak "aktif" (true) olacak.
    @Column(name = "aktif")
    private boolean aktif = true;
}




