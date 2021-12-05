package com.kayson.trackr.user;

import com.kayson.trackr.user.dto.CreateUserDTO;
import com.kayson.trackr.user.dto.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUser(GetUserDto getUserDto) {
        return userRepository.findByHandle(getUserDto.getHandle());
    }

    public void createNewUser(CreateUserDTO createUserDTO) {
        User newUser = new User(createUserDTO.getEmail(), createUserDTO.getHandle());
        userRepository.save(newUser);
    }
}
