package com.karyawan.penlaian.dir_repositories;

import javax.transaction.Transactional;

import com.karyawan.penlaian.dir_models.ScoresModel;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface ScoresRepository extends CrudRepository<ScoresModel, Long> {
    
    @Transactional
    @Modifying
    @Query(value = "INSERT INTO scores(score_from, score_to, kepemimpinan_val, kerja_tim_val, kehadiran_val, kemampuan_val, kontribusi_val, created_at) VALUES(:from, :to, :pimpin, :kerja, :hadir, :skill, :kontrib, :created)", nativeQuery = true)
    public void insertScoresUser(
        @Param("from") String scoreFromData,
        @Param("to") String scoreToData,
        @Param("pimpin") String scoreKepemimpinanData,
        @Param("kerja") String scoreKerjaTimData,
        @Param("hadir") String scoreKehadiranData,
        @Param("skill") String scoreKemampuanData,
        @Param("kontrib") String scoreKontribusoData,
        @Param("created") String scoreCreatedAt
    );
}
