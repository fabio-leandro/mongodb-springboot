package com.fabio.springmongodb.services;

import com.fabio.springmongodb.dtos.UserDto;
import com.fabio.springmongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<UserDto> findAll(){
        return userRepository.findAll().stream()
                .map(UserDto::new).collect(Collectors.toList());
    }
}
