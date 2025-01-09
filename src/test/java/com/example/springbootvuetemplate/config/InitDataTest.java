package com.example.springbootvuetemplate.config;

import com.example.springbootvuetemplate.configs.InitDataExample;
import com.example.springbootvuetemplate.models.Kommune;
import com.example.springbootvuetemplate.models.Region;
import com.example.springbootvuetemplate.repositories.KommuneRepository;
import com.example.springbootvuetemplate.repositories.RegionRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import static org.mockito.Mockito.*;

public class InitDataTest {
    @Mock private RegionRepository regionRepository;
    @Mock private KommuneRepository kommuneRepository;
    @Mock private HttpClient httpClient;

    public InitDataTest() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    private List<Region> fetchRegionsMockSetup() throws Exception {
        Path regionsPath = Path.of("data/regioner.json");
        String regionsData = Files.readString(regionsPath);

        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.body()).thenReturn(regionsData);
        when(mockResponse.statusCode()).thenReturn(200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        InitDataExample initData = new InitDataExample(regionRepository, kommuneRepository, httpClient);
        return initData.fetchRegions();
    }

    @Test
    public void testFetchRegions() throws Exception {
        List<Region> regions = fetchRegionsMockSetup();
        Assertions.assertEquals(5, regions.size());
    }

    @Test
    public void testFetchKommuner() throws Exception {
        List<Region> regions = fetchRegionsMockSetup();

        Path kommunesPath = Path.of("data/kommuner.json");
        String kommunesData = Files.readString(kommunesPath);

        HttpResponse<String> mockResponseKommune = mock(HttpResponse.class);
        when(mockResponseKommune.body()).thenReturn(kommunesData);
        when(mockResponseKommune.statusCode()).thenReturn(200);

        when(httpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponseKommune);

        InitDataExample initData = new InitDataExample(regionRepository, kommuneRepository, httpClient);
        List<Kommune> kommuner = initData.fetchKommuner(regions);

        Assertions.assertEquals(99, kommuner.size());
    }
}