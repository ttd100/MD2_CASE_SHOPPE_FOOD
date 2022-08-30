package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.controller.FoodController;
import rikkei.academy.model.Category;
import rikkei.academy.model.Food;
import rikkei.academy.model.User;

import java.util.List;

public class ViewFood {
    public FoodController foodController = new FoodController();
    public List<Food> foodList = foodController.showListFood();

    public ViewFood() {
        System.out.println("**********Food*******");
        System.out.println("1.Create list Food      4.Update Food");
        System.out.println("2.Show list Food        5.Delete Food");
        System.out.println("3.Detail Food           6.Sort Food");
        System.out.println("           7.Log out");
        int chooseFood = Config.scanner().nextInt();
        switch (chooseFood) {
            case 1:
                formCreateFood();
                break;
            case 2:
                formShowListFood();
                break;
            case 3:
                formDetailFood();
                break;
            case 4:
                formEditFood();
                break;
            case 5:
                formDeleteFood();
                break;
            case 6:
                formShowListFoodAfterSort();
                break;
            case 7:
                new Config<User>().writeFile(Config.PATH_USER_PRINCIPAL,null);
                new Main();
                break;

        }
    }
    public void formCreateFood() {
        System.out.println("***********CREATE FOOD***********");
        while (true) {
            int idfood;
            if (foodList.size() == 0) {
                idfood = 1;
            } else {
                idfood = foodList.get(foodList.size() - 1).getId() + 1;
            }
            System.out.println("Enter name food:  ");
            String nameFood = Config.scanner().nextLine();
            System.out.println("Enter price food : ");
            int price = Config.scanner().nextInt();
            System.out.println("Enter id food category : ");
            int idFoodCategory = Config.scanner().nextInt();
            Category categoryFood = new CategoryController().findById(idFoodCategory);
            foodController.createFood(new Food(idfood,nameFood,price,categoryFood));
//            Food food = new Food(idfood, nameFood, price);
//            foodController.createFood(food);
            System.out.println("create success");
            System.out.println(foodController.showListFood());

            System.out.println("Enter random quit to back menu : ");
            String backMenu = Config.scanner().nextLine();
            if (backMenu.equalsIgnoreCase("quit")) {
                new ViewFood();
            }
        }
    }

    public void formShowListFood() {
//        System.out.println("***********SHOW LIST FOOD***********");
//        System.out.printf("%-10s%-10s%-10s%n","id","Food","price");
//        for (int i = 0; i < foodList.size(); i++) {
//            int j = i + 1;
//            System.out.printf( "%-10d%-10s%-10d%n", j, foodList.get(i).getName(),foodList.get(i).getPrice());
//        }
        System.out.println(foodController.showListFood());
        System.out.println("Enter quit to back menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewFood();
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
            new ViewFood();
        }
    }

    public void formEditFood() {
        System.out.println("Enter id to edit: ");
        int idFood = Config.scanner().nextInt();
        if (foodController.detailFood(idFood) == null) {
            System.out.println("if not found");
        } else {
            Food food = foodController.detailFood(idFood);
            System.out.println("old name " + food.getName());
            System.out.println("old price " + food.getPrice());
            System.out.println("old category"+food.getCategory());
            System.out.println("ENter name to edit: ");
            String newName = Config.scanner().nextLine();
            if (newName.trim().equals("")) {
                newName = food.getName();
            }
            System.out.println("Enter price to edit: ");
            String newPrice = Config.scanner().nextLine();
            if (newPrice.trim().equals("")){
                newPrice = String.valueOf(food.getPrice());
            }
            System.out.println("Enter category to edit: ");
            String newCategory = Config.scanner().nextLine();
            Category category = new Category(newCategory);
            Food foodEdit = new Food(newName,newPrice,category);
            foodController.updateFood(idFood,foodEdit);
            System.out.println("Edit success");
            foodController.showListFood();
        } System.out.println("Enter quit to back menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewFood();
        }
    }
    public void formShowListFoodAfterSort(){
        System.out.printf("%-10s%-10s%-10s%n","STT","NAME","FOOD");
        List<Food> listSort = foodController.sortByNameAndByPrice();
        for (int i = 0; i < listSort.size(); i++) {
            int j = i+1;
            System.out.printf("%-10d%-10s%-10d%n",j,listSort.get(i).getName(),listSort.get(i).getPrice());
        }
        System.out.println("Enter quit to back menu: ");
        String backMenu = Config.scanner().nextLine();
        if (backMenu.equalsIgnoreCase("quit")) {
            new ViewFood();
        }

    }
    public void formDeleteFood(){
        System.out.println("Enter id to quit: ");
        int idFood = Config.scanner().nextInt();
        if (foodController.detailFood(idFood)==null){
            System.out.println("id not found");
        }else {
            System.out.println("Enter 1 to delete, Enter 2 no delete");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete){
                case 1:
                    foodController.detailFood(idFood);
                    System.out.println("delete success");
                    break;
                case 2:
                    new ViewFood();
                    break;
            }
        }
    }
}
