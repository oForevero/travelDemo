package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.ResultInfo;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.UserService;
import cn.itcast.travel.service.impl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author Raven
 */
@WebServlet(name = "UserServlet", value = "/user/*")
public class UserServlet extends BaseServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    private final UserService service = new UserServiceImpl();

    /**
     * 注册功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String[]> parameterMap = request.getParameterMap();
        HttpSession session = request.getSession();
        //获取验证码并删除
        String checkCode = null;
        try {
            checkCode = session.getAttribute("CHECKCODE_SERVER").toString();
        } catch (Exception e) {
            ResultInfo info = ResultInfo.getResultInfo(false,"验证码错误，请检查验证码是否刷新！");
            writeValue(info,response);
        }
        session.removeAttribute("CHECKCODE_SERVER");
        if(checkCode!=null){
            if(!checkCode.equalsIgnoreCase(request.getParameter("check"))){
                ResultInfo info = ResultInfo.getResultInfo(false,"验证码错误");
                writeValue(info,response);
                return;
            }
        }
        User user = new User();
        try {
            //封装成user对象
            BeanUtils.populate(user,parameterMap);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        //封装好的ResultInfo对象，用于传递验证码错误和设置某些东西
        ResultInfo info = new ResultInfo();
        if(service.register(user)){
            info.setFlag(true);
        }else {
            info.setFlag(false);
            info.setErrorMsg("注册失败！");
        }
        writeValue(info,response);
    }

    /**
     * 登录功能
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     */
    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ClassNotFoundException {
        Map<String,String[]> map = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        HttpSession session = request.getSession();
        //获取验证码并删除
        String checkCode = null;
        try {
            checkCode = session.getAttribute("CHECKCODE_SERVER").toString();
        } catch (Exception e) {
            ResultInfo info = ResultInfo.getResultInfo(false,"验证码错误，请检查验证码是否刷新！");
            response.getWriter().write(mapper.writeValueAsString(info));
        }
        User u = service.login(user);
        ResultInfo resultInfo = null;
        if(u!=null){
            if("Y".equals(u.getStatus())) {
                if(checkCode!=null && checkCode.length()>0){
                    if(checkCode.equalsIgnoreCase(request.getParameter("check"))) {
                        resultInfo = ResultInfo.getResultInfo(true, "登录成功！");
                    }else {
                        resultInfo = ResultInfo.getResultInfo(false, "验证码输入错误！");
                    }
                }
            }else {
                resultInfo = ResultInfo.getResultInfo(false, "用户尚未进行邮箱验证！");
            }
        }else {
            resultInfo = ResultInfo.getResultInfo(false,"用户名或密码错误，请检验输入内容！");
        }
        request.getSession().setAttribute("user",u);
        writeValue(resultInfo,response);
    }

    /**
     * 登出功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //销毁自己
        request.getSession().invalidate();
        //跳转到登录页面
        response.sendRedirect(request.getContextPath()+"/login.html");
    }

    /**
     * 主页面自动登录功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void findUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        User user = (User) request.getSession().getAttribute("user");
        writeValue(user,response);
    }

    /**
     * 激活用户功能
     * @param request
     * @param response
     * @throws IOException
     */
    public void activeUser(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String code = request.getParameter("code");
        if(code != null && code.length() != 0){
            boolean checkEmail = service.checkEmail(code);
            String msg;
            if(checkEmail){
                msg = "激活成功，请<a href='../login.html'>登录</a>";
            }else {
                msg = "登陆失败，请检查是否邮件数据出错！";
            }
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(msg);
        }
    }
}
