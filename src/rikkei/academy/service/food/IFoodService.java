package rikkei.academy.service.food;

import rikkei.academy.model.Food;
import rikkei.academy.service.IGenericService;

import java.util.List;

public interface IFoodService extends IGenericService<Food> {
    List<Food> sortByNameAndByPrice();
    Food findFoodByName(String name);
    void editById(Food food);
}
