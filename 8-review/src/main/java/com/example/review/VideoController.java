package com.example.review;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class VideoController {
    private VideoRepository videoRepository;


    @GetMapping("/videos")
    public ResponseEntity<List<Video>> getVideos(@RequestParam(required = false) String title) {
        if (title == null) {
            return ResponseEntity.ok(videoRepository.findAll());
        }
        return ResponseEntity.ok(videoRepository.find(title));
        return ResponseEntity.ok(foundVideos);
    }
}


