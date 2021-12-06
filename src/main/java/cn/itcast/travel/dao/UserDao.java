package cn.itcast.travel.dao;

import cn.itcast.travel.domain.User;

/**
 * @author Raven
 */
public interface UserDao {
    /**
     * 通过用户名来查询用户
     * @param username 用户名
     * @return 注册的用户个数
     */
    public User findByUserName(String username);

    /**
     * 注册用户到数据库中
     * @param user 用户对象
     * @return 操作成功的个数
     */
    public int register(User user);

    /**
     * 验证邮箱
     * @param code uuid代码
     * @return 当返回值是否大于0则进行验证成功
     */
    public int checkMail(String code);

    /**
     * 通过select来返回是否有用户，如果有则返回用户个数为1，如果没有则个数为0
     * @param username 用户名
     * @param password 密码
     * @return 用户个数
     */
    public User login(String username,String password);
}
