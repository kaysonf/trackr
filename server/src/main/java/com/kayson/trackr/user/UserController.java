package com.kayson.trackr.user;

import com.kayson.trackr.user.dto.CreateUserDTO;
import com.kayson.trackr.user.dto.GetUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public void createNewUser(@RequestBody CreateUserDTO createUserDTO) {
        userService.createNewUser(createUserDTO);
    }

    @GetMapping
    public Optional<User> getUser(@RequestBody GetUserDto getUserDto) {
        return userService.getUser(getUserDto);
    }
}
