package rikkei.academy.controller;

import rikkei.academy.model.Shop;
import rikkei.academy.service.shop.IShopService;
import rikkei.academy.service.shop.ShopServiceIMPL;

import java.util.List;

public class ShopController {
    IShopService shopService = new ShopServiceIMPL();
    public List<Shop> showListShop(){
        return shopService.findAll();
    }
    public void createShop(Shop shop){
        shopService.save(shop);
    }
    public Shop detailShop(int id){
        return shopService.findById(id);
    }
    public void updateShop(int id,Shop newShop){
        Shop shop1 = shopService.findById(id);
        shop1.setName(newShop.getName());
        shop1.setUserName(newShop.getUserName());
        shop1.setEmail(newShop.getEmail());
        shop1.setPassword(newShop.getPassword());
        shop1.setAddress(newShop.getAddress());
        shop1.setPhoneNumber(newShop.getPhoneNumber());
    }
    public void deleteShop(int id){shopService.deleteById(id);}
}
