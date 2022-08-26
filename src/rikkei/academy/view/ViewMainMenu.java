package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.User;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

public class ViewMainMenu {
    UserController userController = new UserController();
    List<User> userList = userController.showListUsers();


    public void formRegister(){
        System.out.println("==========FORM REGISTER ===============");
        int id ;
        if (userList.size() == 0){
            id = 1;
        }else {
            id = userList.get(userList.size() - 1).getId()+1;
        }

        //NAME
        String name;
        boolean validateName;
        while (true){
            System.out.println("Enter the name: ");
            name = Config.scanner().nextLine();
            validateName = Pattern.matches("[A-Z][a-zA-Z[\\s]]{1,10}",name);
            if(validateName){
                break;
            } else {
                System.err.println("The name failed! Please try again!");
            }
        }
        //USERNAME
        String username;
        boolean validateUsername;
        while (true){
            System.out.println("Enter the username: ");
            username = Config.scanner().nextLine();
            validateUsername = Pattern.matches("[a-zA-Z0-9]{1,40}",username);
            if (validateUsername){
                break;
            } else {
                System.out.println("the username failed! Please try again!");
            }
        }
        //EMAIL
        String email;
        boolean validateEmail;
        while (true){
            System.out.println("Enter the email: ");
            email = Config.scanner().nextLine();
            validateEmail = Pattern.matches("^(.+)@(.+)$",email);
            if(validateEmail){
                break;
            } else {
                System.err.println("The email failed! Please try again!");
            }
        }
        //PASSWORD
        String password;
        boolean validatePassword;
        while (true){
            System.out.println("Enter the password: ");
            password = Config.scanner().nextLine();
            validatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}",password);
            if(validatePassword){
                break;
            } else {
                System.err.println("The username failed! Please try again!");
            }
        }

        //PHONE_NUMBER
        String phoneNumber;
        boolean validatePhoneNumber;
        while (true) {
            System.out.println("Enter the phone number: ");
            phoneNumber = Config.scanner().nextLine();
            validatePhoneNumber = Pattern.matches("[0-9]{9,10}",phoneNumber);

            if (validatePhoneNumber) {
                break;
            }else {
                System.out.println("the phone number failed! Please try again!");
            }
        }
        //ADDRESS
        String address;
        boolean validateAddress;
        while (true) {
            System.out.println("Enter the address: ");
            address = Config.scanner().nextLine();
            validateAddress = Pattern.matches("[a-zA-Z0-9]{1,40}", address);
            if (validateAddress) {
                break;
            } else {
                System.out.println("the address failed! Please try again!");
            }
        }

        System.out.println("Nhập vào Role: ");
        String role = Config.scanner().nextLine();
        Set<String> strRoles = new HashSet<>();
        strRoles.add(role);
        SignUpDTO signUpDTO = new SignUpDTO(id, name, username,email,password,address,phoneNumber,strRoles);
        //Lấy đối tượng message từ Controller đổ về để check validate các trường hợp trùng lặp trong database
        ResponseMessenger check_existed = userController.register(signUpDTO);
        //IN RA MÀU CHO System.out -> màu vàng a e tìm hiểu thêm in màu khác nhé
        final String ANSI_RESET = "\u001B[0m";
        final String ANSI_YELLOW = "\u001B[33m";
        if(check_existed.getMessage().equals("username_existed")){
            System.err.println("The username is exited! Please try again!");
            formRegister();
        } else  if(check_existed.getMessage().equals("email_existed")){
            System.err.println("The email is exited! Please try again!");
            formRegister();
        } else if(check_existed.getMessage().equals("success")){
            System.out.println(ANSI_YELLOW+"CREATE USER SUCCESS!!!!!"+ANSI_RESET);
            System.out.println("CHECK LIST => ");
            System.out.printf("%-10s%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n","id","name","username","email","password","address","phoneNumber","role");
            for (int i = 0; i < userList.size(); i++){
                System.out.printf("%-10d%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n",userList.get(i).getId(),userList.get(i).getName(),userList.get(i).getUserName(),userList.get(i).getEmail(),userList.get(i).getPassword(),userList.get(i).getAddress(),userList.get(i).getPhoneNumber(),userList.get(i).getRoles());
            }
//            new Main();
        }
    }

    public void fromLogin() {
        //USERNAME
        String userName;
        boolean valiDateUserName;
        while (true) {
            System.out.println("Enter The UserName");
            userName = Config.scanner().nextLine();
            valiDateUserName = Pattern.matches("[a-zA-Z0-9]{1,40}", userName);
            if (valiDateUserName) {
                break;
            } else {
                System.err.println("The name failed! try again");
            }
        }
        //PASSWORD
        String password;
        boolean valiDatePassword;
        while (true) {
            System.out.println("Enter The Password");
            password = Config.scanner().nextLine();
            valiDatePassword = Pattern.matches("[a-zA-Z0-9]{1,40}", password);
            if (valiDatePassword) {
                break;
            } else {
                System.err.println("The name failed! try again");
            }
        }

        ResponseMessenger messenger = userController.login(new SignInDTO(userName,password));
        if (messenger.getMessage().equals("login_failed")){
            System.out.println("LOGIN_FAILED!please check username or password");
            fromLogin();
        }else {
            System.out.println("=====login success=====");
            new ViewAdmin().profile();
        }
    }



}
