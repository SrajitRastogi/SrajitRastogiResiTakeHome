package com.Srajit.exercise.controllers;

import java.util.*;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Srajit.exercise.models.PhotoResponse;

import com.Srajit.exercise.services.PhotoService;


@RestController
@RequestMapping("/mars")
public class ImageController{

    private final PhotoService ps;


    public ImageController(PhotoService ps){
        this.ps = ps;
    }

    @GetMapping("/photos/all")
    public ResponseEntity<List<PhotoResponse>> getAllPhoto(){
        List<PhotoResponse> photos = ps.getAllPhotosForDates();
        return ResponseEntity.ok(photos);
    }

    @PostMapping("/photos/download")
    public ResponseEntity<String> downloadSelectedPhoto(@RequestParam String imageUrl) {
        String responseMessage = ps.downloadSingleImage(imageUrl);
        return ResponseEntity.ok(responseMessage);
    }

}