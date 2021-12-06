package cn.itcast.travel.dao.impl;

import cn.itcast.travel.util.JDBCUtils;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: BaseDao
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/28
 * @Version: 1.0
 */
public class BaseDaoImpl {
    protected final JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    public boolean notNullStrChecker(String str){
        return (str != null) ? true : false;
    }
}
