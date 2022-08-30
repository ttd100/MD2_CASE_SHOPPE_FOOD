package rikkei.academy.controller;

import rikkei.academy.model.Food;
import rikkei.academy.service.food.FoodServiceIMPL;
import rikkei.academy.service.food.IFoodService;

import java.util.List;

public class FoodController {
    IFoodService foodService = new FoodServiceIMPL();
    public List<Food> showListFood(){
        return foodService.findAll();
    }
    public void createFood(Food food){
        foodService.save(food);
    }
    public Food detailFood(int id){
        return foodService.findById(id);
    }
    public void updateFood(int id,Food newFood){
        Food food1 = foodService.findById(id);
        food1.setName(newFood.getName());
        food1.setPrice(newFood.getPrice());

    }
    public List<Food> sortByNameAndByPrice() {
        return foodService.sortByNameAndByPrice();
    }
    public Food findFoodByName(String name) {
        return foodService.findFoodByName(name);
    }
    public Food findById(int id) {
        return foodService.findById(id);
    }
    public void deleteFood(int id) {
        foodService.deleteById(id);
    }
}
