package rikkei.academy.controller;

import rikkei.academy.model.Like;
import rikkei.academy.service.like.ILikeService;
import rikkei.academy.service.like.LikeServiceIMPL;

import java.util.List;

public class LikeController {
    ILikeService likeService = new LikeServiceIMPL();
    public int getLikeNumberById(int id) {
        return likeService.getLikeNumberByFoodId(id);
    }
    public List<Like> getListLike() {
        return likeService.findAll();
    }
    public void createLike(Like like) {
        likeService.save(like);
    }

    public void deleteLike(int id) {
        likeService.remove(id);
    }

    public boolean checkLike(int id) {
        return likeService.checkLike(id);
    }

}
