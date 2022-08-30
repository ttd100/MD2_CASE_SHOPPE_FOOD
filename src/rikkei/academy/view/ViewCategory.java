package rikkei.academy.view;

import rikkei.academy.config.Config;
import rikkei.academy.controller.CategoryController;
import rikkei.academy.controller.FoodController;
import rikkei.academy.model.Category;
import rikkei.academy.model.Food;

import java.util.List;
//import rikkei.academy.model.Category;
//
//import java.util.List;

public class ViewCategory {
    protected ViewCategory(){
        System.out.println("1.create food Category");
        System.out.println("2.show list food category");
        System.out.println("3.Update food category");
        System.out.println("4.Delete food category");
        System.out.println("5.back Menu");
        int choose= Config.scanner().nextInt();
        switch (choose) {
            case 1:
                formCreateFoodCategory();
                break;
            case 2:
                formShowListFoodCategory();
                break;
            case 3:
                formUpdateCategory();
                break;
            case 4:
                formDeleteFoodCategory();
                break;
            case 5:
                new ViewAdmin().profile();
                break;
        }
    }

    private void formDeleteFoodCategory() {
        System.out.println("Enter id to delete category");
        int idCategory = Config.scanner().nextInt();
        if (categoryController.detailFoodCategory(idCategory)== null) {
            System.out.println("id not found");
        }else {
            System.out.println("Enter 1 to delete category, Enter 2 no delete category");
            int chooseDelete = Config.scanner().nextInt();
            switch (chooseDelete) {
                case 1:
                    categoryController.deleteById(idCategory);
                    formShowListFoodCategory();
                    categoryController.showListFoodCategory();
                    break;
                case 2:
                    new ViewAdmin().profile();
                    break;
            }
        }
    }

    static CategoryController categoryController = new CategoryController();
    FoodController foodController = new FoodController();
    List<Food> foodList = foodController.showListFood();
    static List<Category> categoryList = CategoryController.showListFoodCategory();
    public void formCreateFoodCategory(){
        while (true){
            int id;
            if (categoryList.size()==0){
                id =1;
            }else {
                id =categoryList.get(categoryList.size()-1).getId()+1;
            }
            System.out.println("Enter the name Category");
            String name= Config.scanner().nextLine();
            Category category = new Category(id,name);
            categoryController.createFoodCategory(category);
            System.out.println(categoryController.showListFoodCategory());
            System.out.println("Enter random for continue and enter quit for out");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAdmin().profile();
            }
        }
    }
    public void formUpdateCategory() {
        while (true) {
            System.out.println(categoryController.showListFoodCategory());
            System.out.println("Enter ID to edit Category");
            int id =Config.scanner().nextInt();
            System.out.println("Enter the name ProductCategory: ");
            String name =Config.scanner().nextLine();
            Category category = new Category(id,name);
            categoryController.editById(category);
            System.out.println(categoryController.showListFoodCategory());
            System.out.println("Enter random for continue and enter quit for out");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAdmin().profile();
            }

        }
    }
    public void formShowListFoodCategory(){
        System.out.printf("%-10s%-10s%n","id","name");
        for (int i = 0; i < categoryList.size(); i++){
            System.out.printf("%-10d%-10s%n",categoryList.get(i).getId(),categoryList.get(i).getName());
        }
            System.out.println("Enter random for continue and enter quit for out");
            String backMenu = Config.scanner().nextLine();
            if(backMenu.equalsIgnoreCase("quit")){
                new ViewAdmin().profile();
            }
        }

}
