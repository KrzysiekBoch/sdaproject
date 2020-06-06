package sda.spring.rest.service;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sda.spring.rest.model.dto.UserDto;
import sda.spring.rest.model.entity.User;
import sda.spring.rest.repository.UserRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    private ModelMapper mapper;
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserService(ModelMapper mapper, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.mapper = mapper;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void addUser(UserDto userDto) {
        User user = mapper.map(userDto, User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user = userRepository.save(user);
        System.out.println(user);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("User: name="+ authentication.getName() + " roles=" + authentication.getAuthorities());
    }

    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> mapper.map(u, UserDto.class))
                .collect(Collectors.toList());
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}