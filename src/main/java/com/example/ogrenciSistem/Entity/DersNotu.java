package com.example.ogrenciSistem.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "ders_notlari")
@Data
public class DersNotu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 1. Bu not HANGİ ÖĞRENCİYE ait? (Bir öğrencinin birden çok notu olabilir)
    @ManyToOne
    @JoinColumn(name = "ogrenci_id", nullable = false)
    private Ogrenci ogrenci;

    // 2. Bu not HANGİ DERSE ait? (Bir dersi birden çok kişi alabilir)
    @ManyToOne
    @JoinColumn(name = "ders_id", nullable = false)
    private Ders ders;

    // 3. Not Bilgileri
    private Double vizeNotu;
    private Double finalNotu;

    // Bunları sonradan otomatik hesaplatacağız ama şimdilik tabloda bulunsun
    private Double ortalama;
    private String harfNotu; // Örn: AA, CB, FF
    private String durum;    // Örn: Geçti, Kaldı
    // ... (yukarıdaki id, ogrenci, ders tanımlamaları duruyor)

    // Bu metod veritabanına yeni kayıt eklenirken veya güncellenirken OTOMATİK çalışır!
    @PrePersist
    @PreUpdate
    public void notlariHesapla() {
        // Vize ve Final boş değilse hesaplama yap
        if (this.vizeNotu != null && this.finalNotu != null) {

            // Vizenin %40'ı, Finalin %60'ı (Kendi üniversitene göre değiştirebilirsin)
            this.ortalama = (this.vizeNotu * 0.4) + (this.finalNotu * 0.6);

            // Ortalama yuvarlanmış haliyle harf notunu belirleyelim
            if (this.ortalama >= 90) {
                this.harfNotu = "AA";
            } else if (this.ortalama >= 85) {
                this.harfNotu = "BA";
            } else if (this.ortalama >= 80) {
                this.harfNotu = "BB";
            } else if (this.ortalama >= 75) {
                this.harfNotu = "CB";
            } else if (this.ortalama >= 65) {
                this.harfNotu = "CC";
            } else if (this.ortalama >= 58) {
                this.harfNotu = "DC";
            } else if (this.ortalama >= 50) {
                this.harfNotu = "DD";
            } else {
                this.harfNotu = "FF";
            }

            // Durumu belirleyelim (Dersten geçme notu 50 kabul ettim)
            if (this.ortalama >= 50) {
                this.durum = "Geçti";
            } else {
                this.durum = "Kaldı";
            }
        }
    }
}
