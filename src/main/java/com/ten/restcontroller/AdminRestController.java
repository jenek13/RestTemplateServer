package com.ten.restcontroller;
import com.ten.dto.UserDTO;
import com.ten.model.Role;
import com.ten.model.User;
import com.ten.service.RoleService;
import com.ten.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.HashSet;
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

    @GetMapping("/getLoggedUser")
    public User getLoggedUser(String login) {
        return userService.getUserByLogin(login);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }





//    @PostMapping("/admin/create")
//    public void postAdd(@RequestBody UserDTO userDTO) {
//        User newUser = new User();
//        newUser.setLogin(userDTO.getLogin());
//        newUser.setPassword(userDTO.getPassword());
//        newUser.setRoles(getRolesbyID(userDTO.getRole()));
//        userService.insertUser(newUser);
//    }
//
//    private Set<Role> getRolesbyID(Long id) {
//        return roleService.getRolesbyID(id);
//
//    }
//
//    @PostMapping(value = {"/doUpdate"})
//    ResponseEntity<Void> updateUser(@RequestBody UserDTO userDTO) {
//        Long id = userDTO.getRole();
//        User updatedUser = userService.selectUser(userDTO.getId());//не работаеть юзер гет айди продебжаить в зхроме почему гет айди идет по другим юзерам тоже
//        updatedUser.setId(userDTO.getId());
//        updatedUser.setLogin(userDTO.getLogin());
//        updatedUser.setPassword(userDTO.getPassword());
//        //Long[] role = user.getRoles();
//        updatedUser.setRoles((getRolesbyID(id)));//тут приходит роль налл
//        userService.insertUser(updatedUser);
//        return new ResponseEntity<Void>(HttpStatus.OK);
//        //return "redirect:/admin";
//    }
//
//    @GetMapping(value = {"/admin/edit/{id}"})
//    public ResponseEntity<User> editUser(@PathVariable("id") Long id) {
//        return ResponseEntity.ok(userService.selectUser(id));
//    }
//

//
//    @GetMapping(value = {"/user"})
//    public ModelAndView userPage() {
//        Authentication user = SecurityContextHolder.getContext().getAuthentication();
//        String username = user.getName();
//        ModelAndView model = new ModelAndView("user");
//        model.addObject("user", user);
//        return model;
//    }
//
//    private Set<Role> getRolesg(String role) {
//        Set<Role> roles = new HashSet<>();
//        switch (role) {
//            case "admin":
//                roles.add(roleService.getRoleById(1L));
//                break;
//            case "user":
//                roles.add(roleService.getRoleById(2L));//вместо рольсервис будет юезер
//                break;
//            default:
//                roles.add(roleService.getRoleById(2L));
//                break;
//        }
//        return roles;
//    }
//
//    @GetMapping(value = "/error")
//    public String accessDenied() {
//        return "error";
//    }


}


