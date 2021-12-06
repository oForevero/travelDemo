package cn.itcast.travel.service;

/**
 * @author Raven
 */
public interface FavoriteService {
    /**
     * 判断用户是否为收藏过该旅游线路
     * @param rid
     * @param uid
     * @return
     */
    public boolean isFavorite(int uid, String rid);

    /**
     * 获取收藏次数
     * @param rid
     * @return
     */
    public int favoriteTime(String rid);

    /**
     * 用于改变收藏状态
     * @param uid
     * @param rid
     * @param type
     * @return
     */
    public boolean modifyFavorite(int uid, String rid, boolean type);
}
