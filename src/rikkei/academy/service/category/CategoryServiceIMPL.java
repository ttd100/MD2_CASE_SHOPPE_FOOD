package rikkei.academy.service.category;

import rikkei.academy.config.Config;
import rikkei.academy.model.Category;
import rikkei.academy.model.Food;
import rikkei.academy.service.food.FoodServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class CategoryServiceIMPL implements ICategoryService{
    public static String PATH_CATEGORY = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\category.txt";
    public static List<Category> categoryList = new Config<Category>().readFile(PATH_CATEGORY);
    @Override
    public List<Category> findAll() {
        new Config<Category>().writeFile(PATH_CATEGORY,categoryList);
        return categoryList;
    }

    @Override
    public void save(Category category) {
        categoryList.add(category);
    }

    @Override
    public Category findById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()){
                return categoryList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (id == categoryList.get(i).getId()) {
                categoryList.remove(categoryList.get(i));
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
    public List<Food> findByNameCategory(String name) {
        List<Food> foodList = new ArrayList<>();
        for (int i = 0; i < new FoodServiceIMPL().findAll().size(); i++) {
            if (name.equals(new FoodServiceIMPL().findAll().get(i).getCategory().getName())) {
                foodList.add(new FoodServiceIMPL().findAll().get(i));
            }
        }
        return foodList;
    }

    @Override
    public void editById(Category category) {
        for (int i = 0; i < categoryList.size(); i++) {
            if (category.getId()==categoryList.get(i).getId()) {
                categoryList.get(i).setName(category.getName());
            }
        }
    }
}
