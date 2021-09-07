package com.damon.vu.simplecrud.Controller;

import com.damon.vu.simplecrud.Entity.UserDto;
import com.damon.vu.simplecrud.Entity.UserEntity;
import com.damon.vu.simplecrud.Service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@Validated
@RequestMapping("/api/user")
public class UserController {

    private UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }


    @PostMapping("/register/{roleAssigned}")
    public UserEntity saveUser(@Valid @RequestBody UserDto userDto, @PathVariable String roleAssigned) {
        return this.userService.save(userDto, roleAssigned);
    }

    @PostMapping("/register")
    public UserEntity saveUserWithoutRoleAssigned(@RequestBody UserDto userDto) {
        return this.userService.save(userDto, null);
    }

    // Route which only users (registered) can get in
    @PreAuthorize("hasRole('USER')")
    @GetMapping("/userping")
    public String userPing() {
        return "Any User Can Read This";
    }

    // Route which only admins can get in
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/adminping")
    public String adminPing() {
        return "Only Admin Can Read This";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/{email}")
    public void deleteByEmail(@PathVariable String email) {
        System.out.println(email);
        this.userService.deleteByEmail(email);
    }

    @GetMapping("/all")
    public List<UserEntity> findAll() {
        return this.userService.findAll();
    }

    @DeleteMapping("/all")
    public void deleteAll() {
        this.userService.deleteAll();
    }


}
