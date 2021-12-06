package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.impl.CategoryDaoImpl;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.util.JedisUtil;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.Tuple;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;

/**
 * @ClassName: CategoryServiceImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/26
 * @Version: 1.0
 */
public class CategoryServiceImpl implements CategoryService {
    private final CategoryDaoImpl categoryDao = new CategoryDaoImpl();
    private final Jedis jedis = JedisUtil.getJedis();
    @Override
    public List<Category> getAllCategory() {
        List<Category> categoryList = new ArrayList<>();
        /*通过如下方法封装的set并不会进行分数排序，故使用下面的withScores(排名)方法
        Set<String> strSet = jedis.zrange("category",0,-1);*/
        if(jedis!=null) {
            Set<Tuple> tupleSet = jedis.zrangeWithScores("category", 0, -1);
            if (tupleSet == null || tupleSet.size() == 0) {
                categoryList = categoryDao.getAllCategory();
                //用于排序，重写了compare方法 当值为1时表示大于，-1表示小于，0为等于
                categoryList.sort((o1, o2) -> o1.getCid() > o2.getCid() ? 1 : -1);
                for (Category category : categoryList) {
                    jedis.zadd("category", category.getCid(), category.getCname());
                }
            } else {
                for (Tuple t : tupleSet) {
                    Category category = new Category((int) t.getScore(), t.getElement());
                    categoryList.add(category);
                }
            }
        }else {
            categoryList = categoryDao.getAllCategory();
            categoryList.sort((o1,o2)-> o1.getCid() > o2.getCid() ? 1 : -1);
        }
        return categoryList;
    }
}
