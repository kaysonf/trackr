package com.kayson.trackr.user;

import com.kayson.trackr.exceptions.NoSuchElementFoundException;
import com.kayson.trackr.user.dto.CreateUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUserByHandle(String userHandle) {

        return userRepository.findByHandle(userHandle)
                .orElseThrow(() -> new NoSuchElementFoundException(String.format("user %s does not exist", userHandle)));
    }

    public void createNewUser(CreateUserDTO createUserDTO) {
        String userHandle = createUserDTO.getHandle();

        if (userRepository.findByHandle(userHandle).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("user %s already exists", userHandle));
        }

        String userEmail = createUserDTO.getEmail();
        if (userRepository.findByEmail(userEmail).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("email %s is already registered", userEmail));
        }

        User newUser = new User(createUserDTO.getHandle(), createUserDTO.getEmail());

        userRepository.save(newUser);
    }
}
