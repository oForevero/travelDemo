package cn.itcast.travel.dao.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.util.JDBCUtils;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * @ClassName: UserDaoImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/24
 * @Version: 1.0
 */
public class UserDaoImpl extends BaseDaoImpl implements UserDao {
    @Override
    public User findByUserName(String username) {
        try {
            return template.queryForObject("SELECT * FROM tab_user WHERE username = ?"
                    ,new BeanPropertyRowMapper<>(User.class),username);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int register(User user) {
        return template.update("INSERT INTO tab_user(username,password,name,birthday,sex,telephone,email,status,code) VALUE (?,?,?,?,?,?,?,?,?)"
                ,user.getUsername()
                ,user.getPassword()
                ,user.getName()
                ,user.getBirthday()
                ,user.getSex()
                ,user.getTelephone()
                ,user.getEmail()
                ,user.getStatus()
                ,user.getCode()
        );
    }

    @Override
    public int checkMail(String code) {
        return template.update("UPDATE tab_user SET status = 'Y' WHERE code = ?",code);
    }

    @Override
    public User login(String username,String password) {
        try {
            return template.queryForObject("SELECT * FROM tab_user WHERE username = ? AND password = ?"
                    ,new BeanPropertyRowMapper<>(User.class),username,password);
        } catch (DataAccessException e) {
            return null;
        }
    }
}
