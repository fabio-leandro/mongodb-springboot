package com.fabio.springmongodb.services;

import com.fabio.springmongodb.domain.Post;
import com.fabio.springmongodb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        return postRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Post not foud with this id -> "+id));
    }

    public List<Post> findByTitles(String text){
        return postRepository.findByTitleContainingIgnoreCase(text);
    }

    public List<Post> findTitles(String text){
        return postRepository.searchTitle(text);
    }

    public List<Post> fullSearch(String text, Date minDate, Date maxDate){
        maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000);
        return postRepository.fullSearch(text,minDate,maxDate);
    }




















}
