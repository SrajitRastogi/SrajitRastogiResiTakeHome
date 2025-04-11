package com.Srajit.exercise.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.stream.Collectors;

import com.Srajit.exercise.api.NasaApi;
import com.Srajit.exercise.models.NasaApiResponse;
import com.Srajit.exercise.models.PhotoResponse;
import com.Srajit.exercise.utils.ImageDownloader;

import java.util.*;

@Service
public class PhotoService {
    
    @Autowired
    private NasaApi napi;

    @Autowired
    private DateService ds;

    @Autowired
    ImageDownloader imgD;

    public PhotoService(NasaApi napi,DateService ds,ImageDownloader imgD){
        this.napi = napi;
        this.ds = ds;
        this.imgD = imgD;
    }

    public void fetchPhoto(){
        ds.getDates().forEach(date->{
            NasaApiResponse responseJson = napi.getPhotosJson(date);
            System.out.println("Photos for date " + date + ":");
            System.out.println(responseJson);
        });
    }

    public List<PhotoResponse> getAllPhotosForDates() {
        List<String> dates = ds.getDates();

        return dates.stream()
                .flatMap(date -> napi.getPhotosJson(date).getPhotos().stream().map(photo -> new PhotoResponse(date, photo.getImgSrc()))).collect(Collectors.toList());
    }

    public String downloadSingleImage(String imageUrl) {
        String[] parts = imageUrl.split("/");
        String fileName = parts[parts.length - 1].split("\\.")[0];;
        return imgD.downloadImage(imageUrl, fileName);
    }


}
