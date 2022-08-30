package rikkei.academy.service.like;

import rikkei.academy.config.Config;
import rikkei.academy.model.Like;
import rikkei.academy.model.User;
import rikkei.academy.service.user.IUserService;
import rikkei.academy.service.user.UserServiceIMPL;

import java.util.ArrayList;
import java.util.List;

public class LikeServiceIMPL implements ILikeService{
    IUserService userService = new UserServiceIMPL();
    static String PATH_LIKE = "D:\\MD2_CASE_SHOPPE_FOOD\\src\\rikkei\\academy\\database\\like.txt";
    static Config<Like> config = new Config<>();
    public static List<Like> likeList = config.readFile(PATH_LIKE);
    static {
        if (likeList == null) {
            likeList = new ArrayList<>();
        }
    }
    @Override
    public List<Like> findAll() {
        return likeList;
    }

    @Override
    public void save(Like like) {
        likeList.add(like);
        updateData();

    }

    @Override
    public Like findById(int id) {
        for (Like like : likeList) {
            if (like.getId() == id) {
                return like;
            }
        }
        return null;
    }

    @Override
    public void deleteById(int id) {

    }

    @Override
    public void updateData() {
        config.writeFile(PATH_LIKE,likeList);

    }

    @Override
    public void remove(int id) {
        Like likeDelete = null;

        for (Like like : likeList) {
            if (like.getIdFood() == id && like.getIdUser() == userService.getCurrentUser().getId()) {
                likeDelete = like;
            }
        }
        likeList.remove(likeDelete);

        updateData();
    }

    @Override
    public int getLikeNumberByFoodId(int id) {
        int count = 0;

        for (Like like : likeList) {
            if (like.getIdFood() == id) {
                count++;
            }
        }

        return count;
    }

    @Override
    public boolean checkLike(int id) {
        User current = userService.getCurrentUser();

        for (Like like : likeList) {
            if (like.getIdFood() == id && like.getIdUser() == current.getId()) {
                return true;
            }
        }
        return false;
    }
}
