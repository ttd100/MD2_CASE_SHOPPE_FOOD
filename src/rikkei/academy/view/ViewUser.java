package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;

public class ViewUser {
    public ViewUser() {
        System.out.println("1.Show list Shop.");
        System.out.println("2.show list Food");
        System.out.println("3.Log out");
        int chooseUser = Config.scanner().nextInt();
        switch (chooseUser) {
            case 1:
                new ViewShopAfterAdmin().formShowListShop();
                break;
            case 2:
                new ViewFoodAfterAdmin().formShowListFood();
                break;
            case 3:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                new Main();
                break;
        }
    }
}
