package com.kayson.trackr.user;

import com.kayson.trackr.auth.AuthService;
import com.kayson.trackr.user.dto.CreateUserDTO;
import com.kayson.trackr.user.dto.UpdateUserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;
    private final AuthService authService;

    @Autowired
    public UserController(UserService userService, AuthService authService) {
        this.userService = userService;
        this.authService = authService;
    }

    @PostMapping
    public User createNewUser(@Valid @RequestBody CreateUserDTO createUserDTO) {
        return userService.createNewUser(createUserDTO);
    }

    @GetMapping("/{userHandle}")
    public User getUserByHandle(@PathVariable String userHandle) {
        return userService.getUserByHandle(userHandle);
    }


    @PatchMapping
    public User updateUser(@Valid @RequestBody UpdateUserDto updateUserDto) {
        User user = authService.getAuthUser();
        return userService.updateUser(user, updateUserDto);
    }

}
