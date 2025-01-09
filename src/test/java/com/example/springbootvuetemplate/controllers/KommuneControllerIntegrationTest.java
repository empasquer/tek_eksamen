package com.example.springbootvuetemplate.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class KommuneControllerIntegrationTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetKommunerByRegion() throws Exception {
        // POSITVE tests
        String[] regionskoder = new String[] { "1081", "1082", "1083", "1084", "1085"};

        for (String regionskode : regionskoder) {
            mockMvc.perform(get("/kommuner").param("regionskode", regionskode))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].region.kode").value(regionskode))
                    .andExpect(jsonPath("$[1].region.kode").value(regionskode));
        }

        // Optimize for alle objetkterne hvis det var en lang liste

        // NEGATIVE tests
        mockMvc.perform(get("/kommuner").param("regionskode", "hitler"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isEmpty());
    }

    // TODO: lav et endpoint der hedder /regioner
    // Test at /regioner returnerer objekter med unikke regionskoder
    // Test at ALLE regioner har egenskaben fra "testGetKommunerByRegion"

}
