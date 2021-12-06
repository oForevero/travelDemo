package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Seller;

/**
 * @author Raven
 */
public interface SellerDao {
    /**
     * 用于查询seller
     * @param sid
     * @return
     */
    public Seller findSeller(int sid);
}
