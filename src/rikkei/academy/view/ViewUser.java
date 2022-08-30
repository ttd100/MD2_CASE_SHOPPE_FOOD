package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.FoodController;
import rikkei.academy.controller.LikeController;
import rikkei.academy.controller.UserController;
import rikkei.academy.model.Food;
import rikkei.academy.model.Like;
import rikkei.academy.model.RoleName;
import rikkei.academy.model.User;

import java.util.ArrayList;
import java.util.List;

public class ViewUser {

    public ViewUser() {
        System.out.println("1.Show list Shop.");
        System.out.println("2.show list Food");
        System.out.println("3.like Detail food");
        System.out.println("4.Log out");
        int chooseUser = Config.scanner().nextInt();
        switch (chooseUser) {
            case 1:
                new ViewMainMenu().formShowListShop();
                break;
            case 2:
                new ViewMainMenu().formShowListFood();
                break;
            case 3:
                formLikeDetailFood();
                break;
            case 4:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                new Main();
                break;
        }
    }
    FoodController foodController = new FoodController();
    public List<Food> foodList = foodController.showListFood();
    LikeController likeController = new LikeController();
    List<Like> likeList = likeController.getListLike();
    UserController userController = new UserController();
    User currentUser = userController.getCurrentUser();
    RoleName roleName = new ArrayList<>(currentUser.getRoles()).get(0).getRoleName();
    public void formLikeDetailFood() {
        System.out.println("Enter name to detail: ");
        String foodName = Config.scanner().nextLine();
        if (foodController.findFoodByName(foodName) == null) {
            System.out.println("name not found");
        } else {
            for (Food food : foodList) {
                System.out.println(food + "Like" + likeController.getLikeNumberById(food.getId()));
            }
            System.out.println("Enter food id to show detail");
            int idFood = Integer.parseInt(Config.scanner().nextLine());
            int likeNumber = likeController.getLikeNumberById(idFood);
            System.out.println(foodController.findById(idFood));
            System.out.println("like" + likeNumber);
            boolean checkLike = likeController.checkLike(idFood);
            System.out.println(checkLike? "Đã LIKE" : "LIKE");
            System.out.println("Enter 1 to like or else to back");
            int choice = Integer.parseInt(Config.scanner().nextLine());
            if (choice == 1) {
                if (checkLike) {
                    likeController.deleteLike(idFood);
                }else {
                    int idLike;
                    if (likeList.isEmpty()) {
                        idLike = 1;
                    }else {
                        idLike = likeList.get(likeList.size() - 1).getId()+1;
                    }
                    likeController.createLike(new Like(idLike, idFood, currentUser.getId()));
                }
                }
//            Food food = foodController.findFoodByName(foodName);
//            System.out.println(food);
        }
        System.out.println("Enter quit to back menu:");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewFood();
        }
    }
}
