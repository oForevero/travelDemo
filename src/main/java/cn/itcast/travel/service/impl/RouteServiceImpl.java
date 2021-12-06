package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.impl.RouteDaoImpl;
import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.service.RouteService;

import java.util.List;

/**
 * @ClassName: RouteServiceImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/28
 * @Version: 1.0
 */
public class RouteServiceImpl implements RouteService {
    private final RouteDaoImpl dao = new RouteDaoImpl();
    @Override
    public int searchTotalCount(int cid, String rname) {
        return dao.searchTotalCount(cid,rname);
    }

    @Override
    public PageBean<Route> pageQuery(int cid, int currentPage, int pageSize, String rname) {
        PageBean<Route> routePageBean = new PageBean<>();
        int totalCount = dao.searchTotalCount(cid,rname);
        //设置总个数，当前页数，当前页条目数，总页数和列表数据
        routePageBean.setTotalCount(totalCount);
        routePageBean.setCurrentPage(currentPage);
        routePageBean.setPageSize(pageSize);
        int start = (currentPage-1)*pageSize;
        int totalPage = totalCount/pageSize;
        routePageBean.setTotalPage(totalCount % pageSize == 0 ? totalPage : totalPage+1);
        List<Route> routeList = dao.searchByLimit(cid,start,pageSize,rname);
        routePageBean.setList(routeList);
        routePageBean.setListLength(routeList.size());
        return routePageBean;
    }

    @Override
    public Route findOne(int rid) {
        Route route = dao.findOne(rid);
        route.setRouteImgList(dao.findImageByRid(rid));
        route.setSeller(dao.findSeller(route.getSid()));
        return route;
    }
}
