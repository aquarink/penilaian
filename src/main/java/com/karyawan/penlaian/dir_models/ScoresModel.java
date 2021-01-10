package com.karyawan.penlaian.dir_models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "scores")
public class ScoresModel {
    
    @Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "score_from")
    private String scoreFrom;
    
    @Column(name = "score_to")
    private String scoreTo;

    @Column(name = "kepemimpinan_val")
    private String kepemimpinanVal;
    
    @Column(name = "kerja_tim_val")
    private String kerjaTimVal;

    @Column(name = "kehadiran_val")
    private String kehadiranVal;
    
    @Column(name = "kemampuan_val")
    private String kemampuanVal;
    
    @Column(name = "kontribusi_val")
    private String kontribusiVal;

    @Column(name = "created_at")
    private Date createdAt;


    public ScoresModel() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getScoreFrom() {
        return this.scoreFrom;
    }

    public void setScoreFrom(String scoreFrom) {
        this.scoreFrom = scoreFrom;
    }

    public String getScoreTo() {
        return this.scoreTo;
    }

    public void setScoreTo(String scoreTo) {
        this.scoreTo = scoreTo;
    }

    public String getKepemimpinanVal() {
        return this.kepemimpinanVal;
    }

    public void setKepemimpinanVal(String kepemimpinanVal) {
        this.kepemimpinanVal = kepemimpinanVal;
    }

    public String getKerjaTimVal() {
        return this.kerjaTimVal;
    }

    public void setKerjaTimVal(String kerjaTimVal) {
        this.kerjaTimVal = kerjaTimVal;
    }

    public String getKehadiranVal() {
        return this.kehadiranVal;
    }

    public void setKehadiranVal(String kehadiranVal) {
        this.kehadiranVal = kehadiranVal;
    }

    public String getKemampuanVal() {
        return this.kemampuanVal;
    }

    public void setKemampuanVal(String kemampuanVal) {
        this.kemampuanVal = kemampuanVal;
    }

    public String getKontribusiVal() {
        return this.kontribusiVal;
    }

    public void setKontribusiVal(String kontribusiVal) {
        this.kontribusiVal = kontribusiVal;
    }

    public Date getCreatedAt() {
        return this.createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
