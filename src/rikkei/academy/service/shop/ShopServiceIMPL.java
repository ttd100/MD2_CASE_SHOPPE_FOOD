package rikkei.academy.service.shop;

import rikkei.academy.config.Config;
import rikkei.academy.model.Food;
import rikkei.academy.model.Shop;

import java.util.List;

public class ShopServiceIMPL implements IShopService{
    public static final String PATH_SHOP = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\shop.txt";
    public static List<Shop> shopList = new Config<Shop>().readFile(PATH_SHOP);
    @Override
    public List<Shop> findAll() {
        new Config<Shop>().writeFile(PATH_SHOP,shopList);
        return shopList;
    }

    @Override
    public void save(Shop shop) {
        shopList.add(shop);
    }

    @Override
    public Shop findById(int id) {
        for (int i = 0; i < shopList.size(); i++) {
            if (id == shopList.get(i).getId()) {
                return shopList.get(i);
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {
        for (int i = 0; i < shopList.size(); i++) {
            if (id == shopList.get(i).getId()) {
                shopList.remove(i);
            }
        }
    }

    @Override
    public void updateData() {

    }

    @Override
    public void remove(int id) {

    }

}
