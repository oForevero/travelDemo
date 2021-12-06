package cn.itcast.travel.service;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;

/**
 * @Author: Raven
 */
public interface RouteService {
    /**
     * 返回cid所有的条目数
     * @param cid
     * @param rname
     * @return
     */
    public int searchTotalCount(int cid,String rname);

    /**
     * 返回一个PageBean对象
     * @param cid
     * @param currentPage
     * @param pageSize
     * @return
     */
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname);

    /**
     * 进行单行查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
