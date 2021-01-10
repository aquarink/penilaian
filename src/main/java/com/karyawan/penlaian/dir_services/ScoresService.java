package com.karyawan.penlaian.dir_services;


import com.karyawan.penlaian.dir_repositories.ScoresRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ScoresService {
    
    @Autowired
    ScoresRepository scoresRepository;

    public void savePenilaian(
        String from,
        String to,
        String pimpin,
        String kerja,
        String hadir,
        String skill,
        String kontrib,
        String creates
    ) {

        scoresRepository.insertScoresUser(from, to, pimpin, kerja, hadir, skill, kontrib, creates);

    }
}
