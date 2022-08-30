package rikkei.academy.controller;


import rikkei.academy.model.Category;
import rikkei.academy.model.Food;
import rikkei.academy.service.category.CategoryServiceIMPL;
import rikkei.academy.service.category.ICategoryService;

import java.util.List;

public class CategoryController {
    static ICategoryService categoryService = new CategoryServiceIMPL();
    public static List<Category> showListFoodCategory(){
        return categoryService.findAll();
    }
    public static List<Food> findFoodByName(String name) {
        return categoryService.findByNameCategory(name);
    }
    public void createFoodCategory(Category category){
        this.categoryService.save(category);
    }
    public Category findById(int id) {
        return categoryService.findById(id);
    }
    public Category detailFoodCategory(int id) {return categoryService.findById(id);}
    public void deleteById(int id){categoryService.deleteById(id);}
    public void editById(Category category){categoryService.editById(category);}

}
