package com.ten.restcontroller;

import com.ten.dto.UserDTO;
import com.ten.model.Role;
import com.ten.model.User;
import com.ten.service.RoleService;
import com.ten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/server")
public class AdminRestController {

    private final RoleService roleService;
    private final UserService userService;

    @Autowired
    public AdminRestController(RoleService roleService, UserService userService) {
        this.roleService = roleService;
        this.userService = userService;
    }

    @GetMapping("/getAllUsers")
    public ResponseEntity<List<User>> getRestUsers() {
        return ResponseEntity.ok(userService.listUser());
    }

//    @GetMapping("/getLoggedUser/{login}")
//    public User getLoggedUser(@PathVariable String login) {
//        return userService.getUserByLogin(login);
//    }

    @GetMapping("/getLoggedUser/{login}")
    public UserDTO getLoggedUser(@PathVariable String login) {
        UserDTO userDTO = new UserDTO();
        User user = userService.getUserByLogin(login);
        userDTO.setLogin(user.getLogin());
        userDTO.setPassword(user.getPassword());
        userDTO.setRole(user.getRole());
        return userDTO;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/create")
    public void postAdd(@RequestBody UserDTO userDTO) {
        User newUser = new User();
        newUser.setLogin(userDTO.getLogin());
        newUser.setPassword(userDTO.getPassword());
        newUser.setRoles(getRolesbyID(userDTO.getRole()));
        userService.insertUser(newUser);
    }

    @PostMapping("/doUpdate")
    public void doUpdate(@RequestBody UserDTO userDTO) {
        User updatedUser = userService.selectUser(userDTO.getId());//не работаеть юзер гет айди продебжаить в зхроме почему гет айди идет по другим юзерам тоже
        updatedUser.setId(userDTO.getId());
        updatedUser.setLogin(userDTO.getLogin());
        updatedUser.setPassword(userDTO.getPassword());
        updatedUser.setRoles(getRolesbyID(userDTO.getRole()));
        userService.updateUser(updatedUser);
    }

    private Set<Role> getRolesbyID(Long id) {
        return roleService.getRolesbyID(id);
    }

    @GetMapping("/getRoleById/{id}")
    public Role getRoleById(@PathVariable Long id) {
        return roleService.getRoleById(id);
    }

    @GetMapping("getUserById/{id}")
    public User getUserById(@PathVariable long id) {
        return userService.selectUser(id);
    }

}


