package com.samtuga.rmufilemanagement.controller;

import com.samtuga.rmufilemanagement.entity.AppUser;
import com.samtuga.rmufilemanagement.service.AppUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/")
public class AppUserController {
    @Autowired
    private AppUserService appUserService;
    @PostMapping("adduser")
    public ResponseEntity<?> addUser(@RequestBody AppUser appUser){
        return appUserService.addUser(appUser);
    }
    @GetMapping("view-users")
    public ResponseEntity<List<AppUser>> view(){
        return ResponseEntity.ok().body(appUserService.viewAll());
    }

    @DeleteMapping("remove/{id}")
    public ResponseEntity<?>remove(@PathVariable("id") long id){
        return ResponseEntity.ok().body(appUserService.deleteUser(id));
    }

    @PatchMapping("update-user/{email}")
    public ResponseEntity<?>updateUser(@PathVariable("email") String email, @RequestBody AppUser appUser){
        return ResponseEntity.ok().body(appUserService.updateUser(appUser,email));
    }
}
