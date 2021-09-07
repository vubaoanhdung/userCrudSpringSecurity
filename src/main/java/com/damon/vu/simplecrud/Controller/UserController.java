package com.damon.vu.simplecrud.Controller;

import com.damon.vu.simplecrud.Entity.UserDto;
import com.damon.vu.simplecrud.Entity.UserEntity;
import com.damon.vu.simplecrud.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register/{roleAssigned}")
    public UserEntity saveUser(@RequestBody UserDto userDto, @PathVariable String roleAssigned) {
        return this.userService.save(userDto, roleAssigned);
    }

    @PostMapping("/register")
    public UserEntity saveUserWithoutRoleAssigned(@RequestBody UserDto userDto) {
        return this.userService.save(userDto, null);
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userping")
    public String userPing() {
        return "Any User Can Read This";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminping")
    public String adminPing() {
        return "Only Admin Can Read This";
    }

}
