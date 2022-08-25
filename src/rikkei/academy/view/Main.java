package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.User;
import rikkei.academy.service.role.RoleServiceIMPL;


public class Main {
    public Main() {
        UserController userController = new UserController();
        User user = userController.getCurrentUser();
        new RoleServiceIMPL().findAll();
        System.out.println(new RoleServiceIMPL().findAll());
        if (user==null) {
            System.out.println("1.Register");
            System.out.println("2.Login");

        }else {
            System.out.println("3.Show list user");
            System.out.println("4.My profile");
        }

        int chooseMenu = Config.scanner().nextInt();
        switch (chooseMenu) {
            case 1:
                new ViewMainMenu().formRegister();
                break;
            case 2:
                new ViewMainMenu().fromLogin();
                break;
            case 3:
                new ViewAdmin().showListUser();
                break;
            case 4:
                new ViewAdmin().profile();
                break;
        }

    }
    public static void main(String[] args) {

        new Main();
    }
}