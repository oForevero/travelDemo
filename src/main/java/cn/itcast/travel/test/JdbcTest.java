package cn.itcast.travel.test;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName: JdbcTest
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/26
 * @Version: 1.0
 */
public class JdbcTest {
    @Test
    public void test1(){
        List<Category> categoryList = new CategoryServiceImpl().getAllCategory();
        System.out.println(categoryList);
    }
}
