package rikkei.academy.view;

import com.sun.org.apache.bcel.internal.generic.BREAKPOINT;
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
            System.out.println("3.Category Fast Food");
            System.out.println("4.Search Fast Food");

        }else {
            System.out.println("5.Continue");


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
                new ViewMainMenu().formShowListFoodCategory();
                break;
            case 4:
                new ViewMainMenu().formDetailFood();
                break;
            case 5:
                new ViewAdmin().profile();
        }

    }
    public static void main(String[] args) {

        new Main();
    }
}