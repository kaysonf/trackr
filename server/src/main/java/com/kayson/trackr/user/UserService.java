package com.kayson.trackr.user;

import com.kayson.trackr.exceptions.AlreadyExistsException;
import com.kayson.trackr.exceptions.NoSuchElementFoundException;
import com.kayson.trackr.user.dto.CreateUserDTO;
import com.kayson.trackr.user.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User getUserByHandle(String userHandle) {
        return userRepository.findByHandle(userHandle)
                .orElseThrow(() -> new NoSuchElementFoundException(String.format("user %s does not exist", userHandle)));
    }

    @Transactional(rollbackFor = Exception.class) // TODO: check if service is best place to put transactional
    public User createNewUser(CreateUserDTO createUserDTO) {
        String userHandle = createUserDTO.getHandle();

        if (userRepository.findByHandle(userHandle).isPresent()) {
            throw new AlreadyExistsException(String.format("user %s already exists", userHandle));
        }

        String userEmail = createUserDTO.getEmail();
        if (userRepository.findByEmail(userEmail).isPresent()) {
            throw new AlreadyExistsException(String.format("email %s is already registered", userEmail));
        }

        String encodedPassword = passwordEncoder.encode(createUserDTO.getPassword());
        User newUser = new User(createUserDTO.getHandle(), createUserDTO.getEmail(), encodedPassword);

        return userRepository.save(newUser);
    }

    public User updateUser(User userToUpdate, UpdateUserDto updateUserDto) {

        String userHandle = updateUserDto.getHandle();
        userRepository.findByHandle(userHandle).ifPresent(userByHandle -> {
            if (userByHandle.getId() != userToUpdate.getId())
                throw new AlreadyExistsException(String.format("handle %s is already registered", userHandle));
        });

        String userEmail = updateUserDto.getEmail();
        userRepository.findByEmail(userEmail).ifPresent(userByEmail -> {
            if (userByEmail.getId() != userToUpdate.getId())
                throw new AlreadyExistsException(String.format("email %s is already registered", userEmail));
        });

        userToUpdate.setHandle(updateUserDto.getHandle());
        userToUpdate.setEmail(updateUserDto.getEmail());

        return userRepository.save(userToUpdate);
    }
}
