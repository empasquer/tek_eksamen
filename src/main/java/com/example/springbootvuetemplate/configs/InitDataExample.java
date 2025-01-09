package com.example.springbootvuetemplate.configs;

import com.example.springbootvuetemplate.models.Kommune;
import com.example.springbootvuetemplate.models.Region;
import com.example.springbootvuetemplate.repositories.KommuneRepository;
import com.example.springbootvuetemplate.repositories.RegionRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class InitDataExample implements CommandLineRunner {

    private RegionRepository regionRepository;
    private KommuneRepository kommuneRepository;
    private HttpClient httpClient;

    public InitDataExample(RegionRepository regionRepository, KommuneRepository kommuneRepository, HttpClient httpClient) {
        this.regionRepository = regionRepository;
        this.kommuneRepository = kommuneRepository;
        this.httpClient = httpClient;
    }

    @Override
    public void run(String... args) throws Exception {
        List<Region> regions = fetchRegions();
        for (Region region : regions) {
            regionRepository.save(region);
            System.out.println(region);
        }

        List<Kommune> kommuner = fetchKommuner(regions);
        for (Kommune kommune : kommuner) {
            kommuneRepository.save(kommune);
            System.out.println(kommune);
        }

        System.out.println("Hentet " + regions.size() + " regioner og " + kommuner.size() +" kommuner.");
    }

    public List<Region> fetchRegions() throws Exception {
        // 1. TODO: fetch https://api.dataforsyningen.dk/regioner
        // 2. TODO: parse JSON to List<Region>
        JsonNode root = getJsonFrom(URI.create("https://api.dataforsyningen.dk/regioner"));

        List<Region> regions = new ArrayList<>();

        for (JsonNode node : root) {
            String kode = node.get("kode").asText();
            String navn = node.get("navn").asText();
            String href = node.get("href").asText();

            regions.add(new Region(kode, navn, href));
        }

        return regions;

    }


    public List<Kommune> fetchKommuner(List<Region> regions) throws Exception {
        // 1. TODO: fetch https://api.dataforsyningen.dk/kommuner
        // 2. TODO: parse JSON to List<Kommune>
        JsonNode root = getJsonFrom(URI.create("https://api.dataforsyningen.dk/kommuner"));

        List<Kommune> kommuner = new ArrayList<>();
        for (JsonNode node : root) {
            String kode = node.get("kode").asText();
            String navn = node.get("navn").asText();
            String href = node.get("href").asText();
            String regionKode = node.get("regionskode").asText();

            Optional<Region> region = regions
                    .stream()
                    .filter(
                    (Region thisRegion) -> thisRegion.getKode().equals(regionKode))
                    .findFirst();

            Kommune kommune = new Kommune(kode, navn, href, region.get());

            kommuner.add(kommune);
        }

        for(Kommune kom : kommuner) {
            System.out.println(kom.getNavn());

        }
        return kommuner;
    }

    private JsonNode getJsonFrom(URI endpoint) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(endpoint)
                .GET()
                .build();

        HttpResponse<String> response = this.httpClient.send(request, HttpResponse.BodyHandlers.ofString());

        ObjectMapper mapper = new ObjectMapper();

        return mapper.readTree(response.body());
    }
}
