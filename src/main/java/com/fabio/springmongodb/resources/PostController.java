package com.fabio.springmongodb.resources;

import com.fabio.springmongodb.domain.Post;
import com.fabio.springmongodb.services.PostService;
import com.fabio.springmongodb.utils.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    @GetMapping("/{id}")
    public ResponseEntity<Post> findById(@PathVariable String id){
        return ResponseEntity.ok(postService.findById(id));
    }

    @GetMapping("/titleSearch")
    public ResponseEntity<List<Post>> findByTitle(@RequestParam(value = "text") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findByTitles(text);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/title")
    public ResponseEntity<List<Post>> findTitle(@RequestParam(value = "text") String text){
        text = URL.decodeParam(text);
        List<Post> list = postService.findTitles(text);
        return ResponseEntity.ok(list);
    }

    @GetMapping("/fullSearch")
    public ResponseEntity<List<Post>> fullSearch(
        @RequestParam(value = "text", defaultValue = "") String text,
        @RequestParam(value = "minDate", defaultValue = "") String minDate,
        @RequestParam(value = "maxDate", defaultValue = "") String maxDate){

        text = URL.decodeParam(text);
        Date min = URL.convertDate(minDate, new Date(0L));
        Date max = URL.convertDate(maxDate, new Date(0L));
        List<Post> list = postService.fullSearch(text,min,max);
        return ResponseEntity.ok(list);
    }
















}
