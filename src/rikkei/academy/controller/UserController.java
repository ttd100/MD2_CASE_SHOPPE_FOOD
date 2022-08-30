package rikkei.academy.controller;

import rikkei.academy.config.Config;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;
import rikkei.academy.service.role.IRoleService;
import rikkei.academy.service.role.RoleServiceIMPL;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import javax.swing.plaf.nimbus.AbstractRegionPainter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class UserController {
    IUserService userService = new UserServiceIMPL();
    User currentUser = userService.getCurrentUser();
    IRoleService roleService = new RoleServiceIMPL();

    public List<User> showListUsers() {
        return userService.findAll();
    }

    public void updateUser(int id, User newUser) {
        User user1 = userService.findById(id);
        user1.setName(newUser.getName());
        user1.setUserName(newUser.getUserName());
        user1.setEmail(newUser.getEmail());
        user1.setPassword(newUser.getPassword());
        user1.setAddress(newUser.getAddress());
        user1.setPhoneNumber(newUser.getPhoneNumber());

    }

    public User detailUser(int id) {
        return userService.findById(id);
    }

    public ResponseMessenger register(SignUpDTO signUpDTO) {
        if (userService.existedByUserName(signUpDTO.getUsername())) {
            return new ResponseMessenger("username_existed");
        }
        if (userService.existByEmail(signUpDTO.getEmail())) {
            return new ResponseMessenger("email_existed");
        }
        Set<String> strRoles = signUpDTO.getStrRoles();
        Set<Role> roles = new HashSet<>();
        strRoles.forEach(role -> {
            switch (role) {
                case "admin":
                    Role adminRole = roleService.findByName(RoleName.ADMIN);
                    roles.add(adminRole);
                    break;
                case "driver":
                    Role driverRole = roleService.findByName(RoleName.DRIVER);
                    roles.add(driverRole);
                    break;
                case "shop":
                    Role shopRole = roleService.findByName(RoleName.SHOP);
                    roles.add(shopRole);
                    break;
                case "user":
                    Role userRole = roleService.findByName(RoleName.USER);
                    roles.add(userRole);
                    break;
                default:
                    System.out.println("no right, please try again");
            }
        });
        User user = new User(
                signUpDTO.getId(),
                signUpDTO.getName(),
                signUpDTO.getUsername(),
                signUpDTO.getEmail(),
                signUpDTO.getPassword(),
                signUpDTO.getAddress(),
                signUpDTO.getPhoneNumber(),
                roles);
        userService.save(user);
        showListUsers();
        return new ResponseMessenger("success");
    }

    public ResponseMessenger login(SignInDTO signInDTO) {
        if (!userService.checkLogin(signInDTO.getUsername(), signInDTO.getPassword())) {
            return new ResponseMessenger("login_failure");
        }
        if (userService.findByUserName(signInDTO.getUsername()).isStatus()) {
            return new ResponseMessenger("blocked");
        }

        User userLogin = userService.findByUserName(signInDTO.getUsername());

        userService.saveCurrentUser(userLogin);

        return new ResponseMessenger("login_success");
    }

    public User getCurrentUser() {
        return userService.getCurrentUser();
    }

    public ResponseMessenger changePassword(String oldPassword, String newPassword) {
        if (!oldPassword.equals(currentUser.getPassword())) {
            return new ResponseMessenger("not_match");
        }
        userService.findById(currentUser.getId()).setPassword(newPassword);
        userService.updateData();
        return new ResponseMessenger("success");
    }

    public ResponseMessenger deleteUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.remove(id);
        return new ResponseMessenger("success");
    }

    public ResponseMessenger changeRole(int id, String roleName) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        if (!roleName.equals("user") && !roleName.equals("shop")) {
            return new ResponseMessenger("invalid_role");
        }
        Role role = roleName.equals("user") ? roleService.findByRoleName(RoleName.USER) : (roleService.findByRoleName(RoleName.SHOP));
        userService.changeRole(id, role);
        return new ResponseMessenger("success");

    }
    public ResponseMessenger blockUser(int id) {
        if (userService.findById(id) == null || id == 0) {
            return new ResponseMessenger("not_found");
        }
        userService.changeStatus(id);
        boolean check = userService.findById(id).isStatus();
        if (check) {
            return new ResponseMessenger("blocked");
        } else {
            return new ResponseMessenger("unblocked");
        }
    }


}
