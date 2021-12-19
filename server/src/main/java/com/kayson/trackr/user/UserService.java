package com.kayson.trackr.user;

import com.kayson.trackr.exceptions.NoSuchElementFoundException;
import com.kayson.trackr.user.dto.CreateUserDTO;
import com.kayson.trackr.user.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.UUID;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getAuthUser() {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return getUserById(UUID.fromString(userDetails.getUsername()));
    }

    public User getUserById(UUID id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementFoundException("user does not exist"));
    }

    public User getUserByHandle(String userHandle) {
        return userRepository.findByHandle(userHandle)
                .orElseThrow(() -> new NoSuchElementFoundException(String.format("user %s does not exist", userHandle)));
    }

    @Transactional(rollbackFor = Exception.class) // TODO: check if service is best place to put transactional
    public User createNewUser(CreateUserDTO createUserDTO) {
        String userHandle = createUserDTO.getHandle();

        if (userRepository.findByHandle(userHandle).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("user %s already exists", userHandle));
        }

        String userEmail = createUserDTO.getEmail();
        if (userRepository.findByEmail(userEmail).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("email %s is already registered", userEmail));
        }

        String encodedPassword = passwordEncoder.encode(createUserDTO.getPassword());
        User newUser = new User(createUserDTO.getHandle(), createUserDTO.getEmail(), encodedPassword);

        return userRepository.save(newUser);
    }

    public User updateUser(UpdateUserDto updateUserDto) {

        User userToUpdate = getAuthUser();

        String userHandle = updateUserDto.getHandle();
        userRepository.findByHandle(userHandle).ifPresent(userByHandle -> {
            if (userByHandle.getId() != userToUpdate.getId())
                throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("handle %s is already registered", userHandle));
        });

        String userEmail = updateUserDto.getEmail();
        userRepository.findByEmail(userEmail).ifPresent(userByEmail -> {
            if (userByEmail.getId() != userToUpdate.getId())
                throw new ResponseStatusException(HttpStatus.CONFLICT, String.format("email %s is already registered", userEmail));
        });

        userToUpdate.setHandle(updateUserDto.getHandle());
        userToUpdate.setEmail(updateUserDto.getEmail());

        return userRepository.save(userToUpdate);
    }
}
