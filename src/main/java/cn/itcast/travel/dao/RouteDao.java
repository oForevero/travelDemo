package cn.itcast.travel.dao;

import cn.itcast.travel.domain.Route;

import java.util.List;

/**
 * @author Raven
 */
public interface RouteDao {

    /**
     * 获取所有的条目数
     * @param cid 页码id
     * @param rname
     * @return 条目数
     */
    public int searchTotalCount(int cid, String rname);

    /**
     * 通过cid,和每页的承载量和当前页数来进行约束查询
     * @param cid 分页id
     * @param start 开始条目
     * @param pageSize 每页的词条数
     * @param rname
     * @return
     */
    public List<Route> searchByLimit(int cid, int start, int pageSize, String rname);

    /**
     * 进行单行查询
     * @param rid
     * @return
     */
    public Route findOne(int rid);
}
