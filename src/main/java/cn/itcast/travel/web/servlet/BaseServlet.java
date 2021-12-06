package cn.itcast.travel.web.servlet;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author Raven
 */
public class BaseServlet extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String uri = req.getRequestURI();
        String methodName = uri.substring(uri.lastIndexOf("/")+1);
        try {
            //返回所有方法
            Method method = this.getClass().getMethod(methodName,HttpServletRequest.class,HttpServletResponse.class);
            method.invoke(this,req,resp);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过object mapper写出json参数
     * @param object object对象
     * @param response
     * @throws IOException
     */
    public void writeValue(Object object,HttpServletResponse response) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        mapper.writeValue(response.getOutputStream(),object);
    }

    /**
     * 通过object mapper写出json参数
     * @param object
     * @throws IOException
     */
    public String writeValueAsString(Object object) throws JsonProcessingException {
        return mapper.writeValueAsString(object);
    }

    /**
     * 转换字符串为int参数，如果不存在则返回-1代表出错
     * @param strValue
     * @return
     */
    public int parseIntValue(String strValue){
        if(strValue != null && strValue.length() > 0){
            if("NaN".equals(strValue)){
                return 1;
            }else {
                return Integer.parseInt(strValue);
            }
        }else {
            return -1;
        }
    }

    /**
     * 对空字符进行处理，默认为 undefined
     * @param request
     * @param key
     * @return
     */
    public String getReqParameterString(HttpServletRequest request,String key){
        String rname = request.getParameter(key);
        return (rname != null) ? rname : "undefined";
    }
}
