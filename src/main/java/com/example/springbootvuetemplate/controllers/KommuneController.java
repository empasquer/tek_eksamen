package com.example.springbootvuetemplate.controllers;

import com.example.springbootvuetemplate.models.Kommune;
import com.example.springbootvuetemplate.repositories.KommuneRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KommuneController {
    private KommuneRepository kommuneRepository;

    public  KommuneController(KommuneRepository kommuneRepository) {
        this.kommuneRepository = kommuneRepository;
    }

    @GetMapping("/kommuner")
    public List<Kommune> getByRegionskode(@RequestParam String regionskode) {
        return kommuneRepository.findByRegion_Kode(regionskode);
    }
}
