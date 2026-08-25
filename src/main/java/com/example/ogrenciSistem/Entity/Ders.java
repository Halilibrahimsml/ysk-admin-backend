package com.example.ogrenciSistem.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "dersler")
@Data // Bu sayede Getter/Setter yazmamıza gerek kalmıyor
public class Ders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String dersKodu; // Örn: BIL201

    @Column(nullable = false)
    private String dersAdi; // Örn: Web Programlama

    private int kredi; // Örn: 4
}
