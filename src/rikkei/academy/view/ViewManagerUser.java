package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class ViewManagerUser {
    public ViewManagerUser() {
        System.out.println("***************USER MANAGER*************");
        System.out.println("1.Show List User");
        System.out.println("2.Create User");
        System.out.println("3.Change password");
        System.out.println("4.Change Role");
        System.out.println("5.Delete User");
        System.out.println("6.Block User");
        int chooseUser = Config.scanner().nextInt();
        switch (chooseUser) {
            case 1:
                showListUser();
                break;
            case 2:
                new ViewMainMenu().formRegister();
                break;
            case 3:
                formChangePassword();
                break;
            case 4:
                formChangeRole();
                break;
            case 5:
                formDeleteUser();
                break;
            case 6:
                formBlockUser();
                break;
        }
    }

    private void formBlockUser() {
        showListUser();
        System.out.println("Enter id user to block");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.blockUser(id);

        switch (messenger.getMessage()) {
            case "not_found":
                System.err.println("ID not found");
                break;
            case "blocked":
                System.out.println("You just blocked user id " + id);
                break;
            case "unblocked":
                System.out.println("You just unblocked user id " + id);
        }
    }

    private void formChangeRole() {
        System.out.printf("%-10s%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n","id","name","username","email","password","address","phoneNumber","role");
        for (int i = 0; i < userList.size(); i++){
            System.out.printf("%-10d%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n",userList.get(i).getId(),userList.get(i).getName(),userList.get(i).getUserName(),userList.get(i).getEmail(),userList.get(i).getPassword(),userList.get(i).getAddress(),userList.get(i).getPhoneNumber(),userList.get(i).getRoles());
        }
        System.out.println("Enter id of user to change role");
        int id = Config.getValidInteger();
        System.out.println("Enter role to change (shop / user)");
        String roleName = Config.scanner().nextLine();

        ResponseMessenger messenger = userController.changeRole(id, roleName);

        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Change role successfully!");
                break;
            case "invalid_role":
                System.err.println("Invalid role!");
                break;
            case "not_found":
                System.out.println("ID not found!");
        }

    }

    UserController userController = new UserController();
    List<User> userList = userController.showListUsers();

    private void formChangePassword(){

//  x
//        String oldPassword = Config.scanner().nextLine();
        String oldPassword;
        boolean validatePassword;
        while (true){
            System.out.println("Enter old password: ");
            oldPassword = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}",oldPassword);
            if(validatePassword){
                break;
            } else {
                System.err.println("The password failed! Please try again!");
            }
        }
        System.out.println("Enter new password: ");
        String newPassword = Config.scanner().nextLine();
        System.out.println("Repeat password: ");
        String repeatPassword = Config.scanner().nextLine();
        if (!newPassword.equals(repeatPassword)) {
            System.out.println("repeat Password incorrect");
        }else {
            ResponseMessenger messenger = userController.changePassword(oldPassword, newPassword);
            switch (messenger.getMessage()){
                case "not_match":
                    System.out.println("old password does not matches");
                    break;
                case "success":
                    System.out.println("Change password is successful");
                    new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                    new Main();
            }
        }
    }
    public void formDeleteUser() {
        showListUser();
        System.out.println("Enter id of user to delete");
        int id = Config.getValidInteger();
        ResponseMessenger messenger = userController.deleteUser(id);
        switch (messenger.getMessage()) {
            case "success":
                System.out.println("Delete user successfully");
                break;
            case "not_found":
                System.err.println("ID not found");
        }
    }

    public void showListUser(){
        User userLogin = userController.getCurrentUser();
        System.out.println("wellcome: " + userLogin.getName());

//        System.out.println("role: "+ userLogin.getRoles());
        String roleUser = null;
        Iterator<Role> iterator = userLogin.getRoles().iterator();
        while (iterator.hasNext()) {
//            System.out.println(iterator.next().getName());
            roleUser = String.valueOf(iterator.next().getName());
        }
        System.out.println("role--->" + roleUser);
        System.out.println("check -->" + roleUser.equals("USER"));
        System.out.printf("%-10s%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n","id","name","username","email","password","address","phoneNumber","role");
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRoles().stream().collect(Collectors.toList()).get(0).getName() == RoleName.USER) {
                System.out.printf("%-10d%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getAddress(), userList.get(i).getPhoneNumber(), userList.get(i).getRoles());
            }
        }
    }

}
