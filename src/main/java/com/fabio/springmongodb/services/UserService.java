package com.fabio.springmongodb.services;

import com.fabio.springmongodb.domain.User;
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

    public UserDto findById(String id) {
        return userRepository.findById(id).map(UserDto::new)
                .orElseThrow(() -> new ObjectNotFoundException("User not found with id -> "+id));
    }

    public User findPostById(String id){
        return userRepository.findById(id)
                .orElseThrow(()->new ObjectNotFoundException("User not found with id -> "+id));
    }

    public User insert(User user){
       return userRepository.save(user);
    }

    public void deleteById(String id){
        findById(id);
        userRepository.deleteById(id);
    }

    public UserDto updateById(String id, UserDto userDto){
        findById(id);
        User user = fromDto(userDto);
        user.setId(id);
        user = userRepository.save(user);
        return new UserDto(user);
    }


    public User fromDto(UserDto userDto){
        return new User(userDto.getId(),userDto.getName(), userDto.getEmail());
    }
}
