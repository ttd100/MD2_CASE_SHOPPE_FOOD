package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.controller.FoodController;
import rikkei.academy.controller.UserController;
import rikkei.academy.dto.request.SignInDTO;
import rikkei.academy.dto.request.SignUpDTO;
import rikkei.academy.dto.response.ResponseMessenger;
import rikkei.academy.model.*;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static rikkei.academy.view.ViewCategory.categoryController;

public class ViewMainMenu {


    UserController userController = new UserController();
    List<User> userList = userController.showListUsers();
    FoodController foodController = new FoodController();
    public List<Food> foodList = foodController.showListFood();
    static List<Category> categoryList = CategoryController.showListFoodCategory();
    public void formShowListShop() {
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
        System.out.println("check -->" + roleUser.equals("SHOP"));
        System.out.println(userList);
        System.out.printf("%-10s%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n","id","name","username","email","password","address","phoneNumber","role");
        for (int i = 0; i < userList.size(); i++) {
            if (userList.get(i).getRoles().stream().collect(Collectors.toList()).get(0).getName() == RoleName.SHOP) {
                System.out.printf("%-10d%-10s%-10s%-20s%-15s%-15s%-15s%-15s%n", userList.get(i).getId(), userList.get(i).getName(), userList.get(i).getUserName(), userList.get(i).getEmail(), userList.get(i).getPassword(), userList.get(i).getAddress(), userList.get(i).getPhoneNumber(), userList.get(i).getRoles());
            }
        }
    }
    public void formShowListFoodCategory(){
        System.out.println("=========id========name========");
        for (int i = 0; i < categoryList.size(); i++){
            System.out.println("========="+categoryList.get(i).getId()+"========="+categoryList.get(i).getName()+"========");
        }
        System.out.println("Enter random for continue and enter quit for out");
        String backMenu = Config.scanner().nextLine();
        if(backMenu.equalsIgnoreCase("quit")){
            new ViewAdmin().profile();
        }
    }
    public void formDetailFood() {
        System.out.println("Enter name to detail: ");
        String foodName = Config.scanner().nextLine();
        if (foodController.findFoodByName(foodName) == null) {
            System.out.println("name not found");
        } else {
            Food food = foodController.findFoodByName(foodName);
            System.out.println(food);
        }
        System.out.println("Enter quit to back menu:");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new Main();
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
    public void formShowListFood() {
        System.out.println("***********SHOW LIST FOOD***********");
        System.out.printf("%-10s%-10s%-10s%n","id","Food","price");
        for (int i = 0; i < foodList.size(); i++) {
            int j = i + 1;
            System.out.printf( "%-10d%-10s%-10d%n", j, foodList.get(i).getName(),foodList.get(i).getPrice());
        }
//        System.out.println(foodController.showListFood());
        System.out.println("Enter quit to back menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewFood();
        }
    }



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
        SignInDTO signInDTO = new SignInDTO(userName,password);

        ResponseMessenger responseMessenger = userController.login(signInDTO);
        switch (responseMessenger.getMessage()) {
            case "blocked":
                System.err.println("This user is blocked");
                break;
            case "login_success":
                System.out.println("Login successful!");
                new ViewAdmin().profile();
                break;
            case "login_failure":
                System.out.println("Username or password is incorrect!");
        }
    }
}
