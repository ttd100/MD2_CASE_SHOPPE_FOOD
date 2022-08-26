package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Role;
import rikkei.academy.model.User;

import java.util.Iterator;
import java.util.List;

public class ViewUserAfterAdmin {
    public ViewUserAfterAdmin() {
        System.out.println("1.Create User.");
        System.out.println("2.Show List User.");
        System.out.println("3.Edit User.");
        System.out.println("4.Delete User.");
        int chooseUser = Config.scanner().nextInt();
        switch (chooseUser) {
            case 1:
                new ViewMainMenu().formRegister();
                break;
            case 2:
                showListUser();
                break;
            case 3:
                formEditUser();
                break;
            case 4:
                formDeleteUser();
                break;
        }
    }
    UserController userController = new UserController();
    List<User> userList = userController.showListUsers();
    public void formEditUser(){
        System.out.println("Enter id to edit: ");
        int idUser = Config.scanner().nextInt();
        if (userController.detailUser(idUser) == null) {
            System.out.println("id user not found");
        }else {
            User user = userController.detailUser(idUser);
            System.out.println("old name: " + user.getName());
            System.out.println("old username: " + user.getUserName());
            System.out.println("old email: " + user.getEmail());
            System.out.println("old password: " + user.getPassword());
            System.out.println("old address: " + user.getAddress());
            System.out.println("old phone number: " + user.getPhoneNumber());
            System.out.println("Enter name to edit: ");
            String newName = Config.scanner().nextLine();
            if (newName.trim().equals("")) {
                newName = user.getName();
            }
            System.out.println("Enter username to edit: ");
            String newUsername = Config.scanner().nextLine();
            if (newUsername.trim().equals("")) {
                newUsername = user.getUserName();
            }
            System.out.println("Enter email to edit: ");
            String newEmail = Config.scanner().nextLine();
            if (newEmail.trim().equals("")) {
                newEmail = user.getEmail();
            }
            System.out.println("Enter password to edit: ");
            String newPassword = Config.scanner().nextLine();
            if (newPassword.trim().equals("")) {
                newPassword = user.getPassword();
            }
            System.out.println("Enter address to edit: ");
            String newAddress = Config.scanner().nextLine();
            if (newAddress.trim().equals("")) {
                newAddress = user.getAddress();
            }
            System.out.println("Enter phone number to edit: ");
            String newPhone = Config.scanner().nextLine();
            if (newPhone.trim().equals("")) {
                newPhone = user.getPhoneNumber();
            }
            User newUser = new User(newName,newUsername,newEmail,newPassword,newAddress,newPhone);
            userController.updateUser(idUser,newUser);
            System.out.println("Edit success");
            userController.showListUsers();
        }System.out.println("nhập vào quit để thoát");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new Main();
        }
    }
    public void formDeleteUser(){
        System.out.println("Enter id to delete");
        int idUser = Config.scanner().nextInt();
        if (userController.detailUser(idUser) == null){
            System.out.println("id user not found");
        }else {
            System.out.println("Enter 1 to delete,enter 2 no delete");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete) {
                case 1:
                    userController.deleteUser(idUser);
                    showListUser();
                    break;
                case 2:
                    new Main();
                    break;
            }
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
            if (roleUser.equals("USER")) {
                System.out.printf("%-10d%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getAddress(), userList.get(i).getPhoneNumber(), userList.get(i).getRoles());
            }
        }
    }

}
