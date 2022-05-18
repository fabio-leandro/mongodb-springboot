package com.fabio.springmongodb.services;

import com.fabio.springmongodb.domain.Post;
import com.fabio.springmongodb.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    public Post findById(String id){
        return postRepository.findById(id)
                .orElseThrow(()-> new ObjectNotFoundException("Post not foud with this id -> "+id));
    }

}
