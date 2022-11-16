package com.samtuga.rmufilemanagement.service;

import com.samtuga.rmufilemanagement.entity.AppUser;
import com.samtuga.rmufilemanagement.entity.UserRole;
import com.samtuga.rmufilemanagement.repository.RoleRepository;
import com.samtuga.rmufilemanagement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class AppUserService {
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    public final String USER_ALREADY_EXISTS = "user with username %s already exist";
    public final String USER_UPDATE = "User with Username %s 's Role has been updated";
    public final String USER_NOT_FOUND = "User with Username %s 's Not found";

    public ResponseEntity<?> addUser(AppUser appUser){
        if(userRepository.findByEmail(appUser.getEmail()).isPresent()){
            return ResponseEntity.ok().body(String.format(USER_ALREADY_EXISTS,appUser.getEmail()));
        } else{
            Set<UserRole> userRole = new HashSet<>();
            appUser.getRoles().stream()
                    .forEach(role->{
                        UserRole user_role = roleRepository.findRoleByName(role.getName()).orElse(null);
                        if(user_role == null) {
                            user_role = new UserRole();
                        }
                        user_role.setName(role.getName());
                        userRole.add(user_role);
                    });
            appUser.setRoles(userRole);
            appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            userRepository.save(appUser);
            return ResponseEntity.ok().body(String.format("User added successfully"));
        }
    }

    public List<AppUser> viewAll(){
        return userRepository.findAll();
    }

    public ResponseEntity<String> deleteUser(long id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().body(String.format("user with id %s has been removed",id));
    }


    public ResponseEntity<?> updateUser(AppUser appUser, String email){
        AppUser app = userRepository.findByEmail(email).orElse(null);
        if(app == null){
            return ResponseEntity.ok().body(String.format(USER_NOT_FOUND,email));
        } else{
            Set<UserRole> userRole = new HashSet<>();
            appUser.getRoles().stream()
                    .forEach(role->{
                        UserRole user_role = roleRepository.findRoleByName(role.getName()).orElse(null);
                        if(user_role == null) {
                            user_role = new UserRole();
                        }
                        user_role.setName(role.getName());
                        userRole.add(user_role);
                    });
            app.setRoles(userRole);
//            app.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
            appUser.setFirstName(appUser.getFirstName());
            appUser.setEmail(appUser.getEmail());
            userRepository.save(app);
            return ResponseEntity.ok().body(String.format(USER_UPDATE,email));

        }
    }
}
