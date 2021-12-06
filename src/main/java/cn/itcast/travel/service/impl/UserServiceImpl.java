package cn.itcast.travel.service.impl;

import cn.itcast.travel.dao.UserDao;
import cn.itcast.travel.dao.impl.UserDaoImpl;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.util.MailUtils;
import cn.itcast.travel.util.UuidUtil;

/**
 * @ClassName: UserServiceImpl
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/24
 * @Version: 1.0
 */
public class UserServiceImpl implements UserService {
    private UserDao userDao = new UserDaoImpl();
    @Override
    public User findByUserName(String username) {
        return userDao.findByUserName(username);
    }

    @Override
    public boolean register(User user) {
        if(findByUserName(user.getUsername())!=null){
            return false;
        }
        user.setCode(UuidUtil.getUuid());
        //N代表为注册，Y代表已注册
        user.setStatus("N");
        new Thread(()->{
            String context = "<h3>尊敬的用户：您好！</h3>" +
                    "您正在提交对"+user.getEmail()+"的验证，请点击以下链接完成邮箱验证（如果不是您提交的申请，请忽略）。<br>" +
                    "验证链接： <a href='http://localhost/travel/user/activeUser?code="+user.getCode()+"'>黑马旅游网</a><br>" +
                    "如果以上链接无法点击，可以复制以上链接在浏览器打开。<br>" +
                    "<br>" +
                    "想验证邮箱，请点击直达<br>" +
                    "<br>" +
                    "黑马程序员有限公司<br>" +
                    "此为系统邮件请勿回复";
            MailUtils.sendMail(user.getEmail(),context,"黑马旅游网验证邮件");
        }).start();
        return userDao.register(user) > 0 ? true : false;
    }

    @Override
    public boolean checkEmail(String code) {
        return userDao.checkMail(code) > 0 ? true : false;
    }

    @Override
    public User login(User user) {
        return userDao.login(user.getUsername(),user.getPassword());
    }
}
