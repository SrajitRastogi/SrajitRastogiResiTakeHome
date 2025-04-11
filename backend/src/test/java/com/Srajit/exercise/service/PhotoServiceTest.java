package com.Srajit.exercise.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;

import com.Srajit.exercise.api.NasaApi;
import com.Srajit.exercise.models.NasaApiResponse;
import com.Srajit.exercise.models.Photo;
import com.Srajit.exercise.services.DateService;
import com.Srajit.exercise.services.PhotoService;
import com.Srajit.exercise.utils.ImageDownloader;

public class PhotoServiceTest {
    
    @Mock
    @Autowired
    private NasaApi npi;

    @Mock
    @Autowired
    private ImageDownloader imgD;

    @Mock
    private DateService ds;

    @InjectMocks
    private PhotoService ps;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPhotos(){
        Photo ph = new Photo();
        ph.setImgSrc("https://mars.nasa.gov/msl-raw-images/photo.jpg");
        ph.setEarthDate("2016-06-03");
        ph.setId(123);

        NasaApiResponse res = new NasaApiResponse();
        res.setPhotos(List.of(ph));

        when(ds.getDates()).thenReturn(List.of("2016-06-03"));
        when(npi.getPhotosJson(anyString())).thenReturn(res);

        // List<String> testDates = List.of("2016-06-03");
        // DateService dateSpy = spy(ds);
        // PhotoService photoSpy = spy(ps);
        // doReturn(testDates).when(dateSpy).getDates();

        var result = ps.getAllPhotosForDates();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals("https://mars.nasa.gov/msl-raw-images/photo.jpg", result.get(0).getImageUrl());
    }  
    
     @Test
    void testDownloadSingleImage_shouldCallImageDownloader() {
        // Arrange
        String imageUrl = "https://mars.nasa.gov/msl-raw-images/photo.jpg";
        String expectedMessage = "Image downloaded successfully at: /some/path/photo.jpg";

        // Mock behavior of image downloader
        when(imgD.downloadImage(anyString(), anyString())).thenReturn(expectedMessage);

        // Act
        String result = ps.downloadSingleImage(imageUrl);

        // Assert
        assertEquals(expectedMessage, result);
        verify(imgD, times(1)).downloadImage(anyString(), anyString());
    }
}

