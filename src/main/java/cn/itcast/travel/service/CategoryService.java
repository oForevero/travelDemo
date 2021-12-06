package cn.itcast.travel.service;

import cn.itcast.travel.domain.Category;

import java.util.List;

/**
 * @ClassName: CategoryService
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/26
 * @Version: 1.0
 */
public interface CategoryService {
    /**
     * 获取所有Cate对象，用于加载到网站上
     * @return Category 列表
     */
    public List<Category> getAllCategory();
}
