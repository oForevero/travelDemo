package cn.itcast.travel.service;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.domain.User;

/**
 * @author Raven
 */
public interface UserService {
    /**
     * 直接传出UserDaoImpl的相同方法即可
     * @param username
     * @return
     */
    public User findByUserName(String username);

    /**
     * 注册用户
     * @param user
     * @return
     */
    public boolean register(User user);

    /**
     * 进行邮箱验证
     * @param code 传入uuid
     * @return 成功 | 失败
     */
    public boolean checkEmail(String code);

    /**
     * 进行登录验证
     * @param user user对象
     * @return 是否登录成功
     */
    public User login(User user);
}
