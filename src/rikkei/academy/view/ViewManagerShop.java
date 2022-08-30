package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.ShopController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Role;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.Shop;
import rikkei.academy.model.User;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

public class ViewManagerShop {
    UserController userController = new UserController();
    List<User> userList = userController.showListUsers();

    public ShopController shopController = new ShopController();
    public List<Shop> shopList = shopController.showListShop();
    public ViewManagerShop() {
        System.out.println("***************SHOP MANAGER*************");
        System.out.println("1.Show List SHOP");
        System.out.println("2.Create SHOP");
        System.out.println("3.Delete SHOP");
        System.out.println("4.Change SHOP");
        System.out.println("5.Block SHOP");
        int chooseShop = Config.scanner().nextInt();
        switch (chooseShop) {
            case 1:
                new ViewMainMenu().formRegister();
                break;
            case 2:
                formShowListShop();
                break;
        }
    }

    public void formShowListShop() {
        User userLogin =    userController.getCurrentUser();
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

}
