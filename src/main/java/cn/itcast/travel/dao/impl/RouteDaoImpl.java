package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.RouteDao;
import cn.itcast.travel.dao.RouteImageDao;
import cn.itcast.travel.dao.SellerDao;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.RouteImg;
import cn.itcast.travel.domain.Seller;
import org.springframework.jdbc.core.BeanPropertyRowMapper;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName: RouteDaoImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/28
 * @Version: 1.0
 */
public class RouteDaoImpl extends BaseDaoImpl implements RouteDao, RouteImageDao, SellerDao {
    @Override
    public int searchTotalCount(int cid, String rname) {
        String sql = "SELECT COUNT(*) FROM tab_route WHERE 1=1 ";
        StringBuilder sb = new StringBuilder(sql);
        //条件
        List params = new ArrayList();
        if(cid!=0) {
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if(notNullStrChecker(rname)){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sql = sb.toString();
        return template.queryForObject(sql,Integer.class,params.toArray());
    }

    @Override
    public List<Route> searchByLimit(int cid, int start, int pageSize, String rname) {
        String sql = "SELECT * FROM tab_route WHERE 1=1";
        StringBuilder sb = new StringBuilder(sql);
        //条件
        List params = new ArrayList();
        if(cid!=0) {
            sb.append(" and cid = ?");
            params.add(cid);
        }
        if(notNullStrChecker(rname)){
            sb.append(" and rname like ?");
            params.add("%"+rname+"%");
        }
        sb.append(" limit ? , ?");
        params.add(start);
        params.add(pageSize);
        sql = sb.toString();
        return template.query(sql,new BeanPropertyRowMapper<>(Route.class)
        ,params.toArray());
    }

    @Override
    public Route findOne(int rid) {
        return template.queryForObject("SELECT * FROM tab_route WHERE rid = ?",new BeanPropertyRowMapper<>(Route.class),rid);
    }

    @Override
    public List<RouteImg> findImageByRid(int rid) {
        return template.query("SELECT * FROM tab_route_img WHERE rid = ?",new BeanPropertyRowMapper<>(RouteImg.class),rid);
    }

    @Override
    public Seller findSeller(int sid) {
        return template.queryForObject("SELECT * FROM tab_seller WHERE sid = ?",new BeanPropertyRowMapper<>(Seller.class),sid);
    }
}
