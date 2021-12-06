package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Favorite;

/**
 * @author Raven
 */
public interface FavoriteDao {
    /**
     * 通过uid和rid来查询当前用户是否收藏该页面
     * @param uid
     * @param rid
     * @return
     */
    public Favorite findByUidAndRid(int uid, int rid);

    /**
     * 获取收藏的数量
     * @param rid
     * @return
     */
    public int findFavoriteTime(int rid);

    /**
     * 切换收藏状态
     * @param uid
     * @param rid
     * @param type 如果为真则进行删除操作，反之进行增加操作
     * @return
     */
    public int modifyFavorite(int uid, int rid, boolean type);
}
