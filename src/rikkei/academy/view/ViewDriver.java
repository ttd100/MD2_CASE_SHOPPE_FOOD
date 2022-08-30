package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;

public class ViewDriver {
    public ViewDriver(){
        System.out.println("1.Show list Shop.");
        System.out.println("2.Show List User.");
        System.out.println("3.Log out.");
        int chooseDriver = Config.scanner().nextInt();
        switch (chooseDriver) {
            case 1:
                new ViewMainMenu().formShowListShop();
                break;
            case 2:
                new ViewMainMenu().showListUser();
                break;
            case 3:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                new Main();
                break;
        }
    }
}
