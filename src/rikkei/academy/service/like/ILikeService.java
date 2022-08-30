package rikkei.academy.service.like;

import rikkei.academy.model.Like;
import rikkei.academy.service.IGenericService;

public interface ILikeService extends IGenericService<Like> {
    int getLikeNumberByFoodId(int id);
    boolean checkLike(int id);
}
