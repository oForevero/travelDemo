package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.CategoryDao;
import cn.itcast.travel.domain.Category;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.List;

/**
 * @ClassName: CategoryImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/26
 * @Version: 1.0
 */
public class CategoryDaoImpl extends BaseDaoImpl implements CategoryDao {
    @Override
    public List<Category> getAllCategory() {
        return template.query("SELECT * FROM tab_category", new BeanPropertyRowMapper<>(Category.class));
    }
}
