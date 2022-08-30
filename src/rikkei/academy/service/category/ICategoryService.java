package rikkei.academy.service.category;

import rikkei.academy.model.Category;
import rikkei.academy.model.Food;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface ICategoryService extends IGenericService<Category> {
    List<Food> findByNameCategory(String name);
    void editById(Category category);
}
