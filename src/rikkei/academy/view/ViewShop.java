package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.model.User;

public class ViewShop {
    public ViewShop() {
        System.out.println("1.Create list Food      4.Update Food");
        System.out.println("2.Show list Food        5.Delete Food");
        System.out.println("3.Detail Food           6.Sort Food");
        System.out.println("           7.Log out");
        int chooseFood = Config.scanner().nextInt();
        switch (chooseFood) {
            case 1:
                new ViewFood().formCreateFood();
                break;
            case 2:
                new ViewFood().formShowListFood();
                break;
            case 3:
                new ViewFood().formDetailFood();
                break;
            case 4:
                new ViewFood().formEditFood();
                break;
            case 5:
                new ViewFood().formDeleteFood();
                break;
            case 6:
                new ViewFood().formShowListFoodAfterSort();
                break;
            case 7:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                new Main();
                break;
        }
    }
}
