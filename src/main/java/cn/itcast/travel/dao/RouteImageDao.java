package cn.itcast.travel.dao;

import cn.itcast.travel.domain.RouteImg;

import java.awt.*;
import java.util.List;

/**
 * @author Raven
 */
public interface RouteImageDao {
    /**
     * 返回一个RouteImg对象
     * @param rid
     * @return
     */
    public List findImageByRid(int rid);
}
