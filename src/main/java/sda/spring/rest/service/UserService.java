package com.sda.spring.mvc.service;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import sda.spring.rest.model.dto.UserDto;
import sda.spring.rest.model.entity.User;
import sda.spring.rest.repository.UserRepository;

@Service
public class UserService {

    private ModelMapper mapper;
    private UserRepository userRepository;

    public UserService(ModelMapper mapper, UserRepository userRepository){
        this.mapper = mapper;
        this.userRepository = userRepository;
    }
    public void addUser(UserDto userDto){
        User user = mapper.map(userDto, User.class);
        user = userRepository.save(user);
        System.out.println(user);

    }
}
