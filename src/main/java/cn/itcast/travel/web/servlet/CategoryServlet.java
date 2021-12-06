package cn.itcast.travel.web.servlet;

import cn.itcast.travel.domain.Category;
import cn.itcast.travel.service.CategoryService;
import cn.itcast.travel.service.impl.CategoryServiceImpl;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author Raven
 */
@WebServlet(name = "CategoryServlet", value = "/category/*")
public class CategoryServlet extends BaseServlet {
    private final CategoryService service = new CategoryServiceImpl();
    public void listCategory(HttpServletRequest request, HttpServletResponse response) throws IOException{
        List<Category> categoryList = service.getAllCategory();
        writeValue(categoryList,response);
    }
}
