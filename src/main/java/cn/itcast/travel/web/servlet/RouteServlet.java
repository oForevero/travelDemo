package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.PageBean;
import cn.itcast.travel.domain.Route;
import cn.itcast.travel.domain.User;
import cn.itcast.travel.service.FavoriteService;
import cn.itcast.travel.service.RouteService;
import cn.itcast.travel.service.impl.FavoriteServiceImpl;
import cn.itcast.travel.service.impl.RouteServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author Raven
 */
@WebServlet(name = "RouteServlet", value = "/route/*")
public class RouteServlet extends BaseServlet {
    private final static int DEFAULT_CID = 0;
    private final static int DEFAULT_RID = 1;
    private final static int DEFAULT_CURRENT_PAGE = 1;
    private final static int DEFAULT_PAGE_SIZE = 20;
    private final RouteService routeService = new RouteServiceImpl();
    private final FavoriteService favoriteService = new FavoriteServiceImpl();
    /**
     * 分页查询 默认cid为0，默认页数为1，默认展示条目为5
     * @param request
     * @param response
     */
    public void pageQuery(HttpServletRequest request, HttpServletResponse response) throws IOException {
        //接收参数
        int cid = checkPageBeanExtra(request.getParameter("cid"),DEFAULT_CID);
        int currentPage = checkPageBeanExtra(request.getParameter("currentPage"),DEFAULT_CURRENT_PAGE);
        int pageSize = checkPageBeanExtra(request.getParameter("pageSize"),DEFAULT_PAGE_SIZE);
        String rname = getReqParameterString(request,"rname");
        //进行重编码
        if("undefined".equals(rname)) {
            rname = null;
        }else {
            rname = new String(rname.getBytes(StandardCharsets.ISO_8859_1), "utf-8");
        }
        PageBean<Route> pageBean = routeService.pageQuery(cid,currentPage,pageSize,rname);
        writeValue(pageBean,response);
    }

    /**
     * 查询一个Route对象
     * @param request
     * @param response
     * @throws IOException
     */
    public void findOneRoute(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int rid = checkPageBeanExtra(request.getParameter("rid"),DEFAULT_RID);
        Route route = routeService.findOne(rid);
        writeValue(route,response);
    }

    public void isFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        int uid = 0;
        if(user!=null){
            uid = user.getUid();
        }
        boolean flag = favoriteService.isFavorite(uid,rid);
        writeValue(flag,response);
    }

    public void FavoriteLine(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        int times = favoriteService.favoriteTime(rid);
        writeValue(times,response);
    }

    public void modifyFavorite(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String rid = request.getParameter("rid");
        User user = (User) request.getSession().getAttribute("user");
        favoriteService.modifyFavorite(user.getUid(),rid,false);
    }

    /**
     * 对参数进行校验，如果正确则传入-1，反之传入
     * @param extra
     * @param defaultValue
     * @return
     */
    private int checkPageBeanExtra(String extra,int defaultValue){
        return parseIntValue(extra) == -1 ? defaultValue : parseIntValue(extra);
    }
}
