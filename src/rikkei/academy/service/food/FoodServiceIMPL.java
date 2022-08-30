package rikkei.academy.service.food;

import rikkei.academy.config.Config;
import rikkei.academy.model.Food;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FoodServiceIMPL implements IFoodService {
    public static final String PATH_FOOD = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\food.txt";
    public static List<Food> foodList = new Config<Food>().readFile(PATH_FOOD);
    @Override
    public List<Food> findAll() {
        new Config<Food>().writeFile(PATH_FOOD,foodList);
        return foodList;
    }

    @Override
    public void save(Food food) {
        foodList.add(food);
    }

    @Override
    public Food findById(int id) {
        for (int i = 0; i < foodList.size(); i++) {
            if (id==foodList.get(i).getId()){
                return foodList.get(i);
            }
        }return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < foodList.size(); i++) {
            if (id == foodList.get(i).getId()) {
                foodList.remove(i);
            }
        }
    }

    @Override
    public void updateData() {

    }

    @Override
    public void remove(int id) {

    }

    @Override
    public List<Food> sortByNameAndByPrice() {
        List<Food> listSort = new ArrayList<>();
        for (int i = 0; i < foodList.size(); i++) {
            listSort.add(foodList.get(i));
        }
        Collections.sort(listSort);
        return listSort;
    }

    @Override
    public Food findFoodByName(String name) {
        for (int i = 0; i < foodList.size(); i++) {
            if (name.equalsIgnoreCase(foodList.get(i).getName())) {
                return foodList.get(i);
            }
        }
        return null;
    }

    @Override
    public void editById(Food food) {
        for (int i = 0; i < foodList.size(); i++) {
            if (foodList.get(i).getId() == food.getId()) {
                foodList.get(i).setName(food.getName());
                foodList.get(i).setPrice(food.getPrice());
                foodList.get(i).setCategory(food.getCategory());
            }
        }
        new Config<Food>().writeFile(PATH_FOOD,foodList);
    }
}
