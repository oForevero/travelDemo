package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.dao.impl.FavoriteDaoImpl;
import cn.itcast.travel.domain.Favorite;
import cn.itcast.travel.service.FavoriteService;

/**
 * @ClassName: FavoriteServiceImp
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/12/2
 * @Version: 1.0
 */
public class FavoriteServiceImpl implements FavoriteService {
    private final FavoriteDao dao = new FavoriteDaoImpl();
    @Override
    public boolean isFavorite(int uid, String rid) {
        Favorite favorite = null;
        if(rid!=null){
            favorite = dao.findByUidAndRid(uid,Integer.parseInt(rid));
        }
        return favorite != null;
    }

    @Override
    public int favoriteTime(String rid) {
        return dao.findFavoriteTime(Integer.parseInt(rid));
    }

    @Override
    public boolean modifyFavorite(int uid, String rid, boolean type) {
        return dao.modifyFavorite(uid, Integer.parseInt(rid), type) > 0 ? true : false;
    }
}
