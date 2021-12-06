package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.FavoriteDao;
import cn.itcast.travel.domain.Favorite;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

/**
 * @ClassName: FavoriteDaoImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/12/2
 * @Version: 1.0
 */
public class FavoriteDaoImpl extends BaseDaoImpl implements FavoriteDao {
    @Override
    public Favorite findByUidAndRid(int uid, int rid) {
        try {
            return template.queryForObject("SELECT * FROM tab_favorite WHERE uid = ? and rid = ?"
                    ,new BeanPropertyRowMapper<>(Favorite.class),uid,rid);
        } catch (DataAccessException e) {
            return null;
        }
    }

    @Override
    public int findFavoriteTime(int rid) {
        return template.queryForObject("SELECT count(*) FROM tab_favorite WHERE rid = ?",Integer.class,rid);
    }

    @Override
    public int modifyFavorite(int uid, int rid, boolean type) {
        String sql = null;
        if(type){
            sql = "INSERT INTO tab_favorite value(rid,date,uid)";
        }else {

        }
        return template.update(sql,uid,rid);
    }
}
