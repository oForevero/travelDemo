package cn.itcast.travel.domain;

import java.util.List;

/**
 * @ClassName: PageBean
 * @Description: TODO
 * @Author: Raven
 * @Date: 2021/11/28
 * @Version: 1.0
 */
public class PageBean<T> {
    /**
     * 总记录数，总页码数，当前页码，每页显示的条目，每页显示的集合
     */
    private int totalCount;
    private int totalPage;
    private int currentPage;
    private int pageSize;
    private int listLength;
    private List<T> list;

    public PageBean() {

    }

    public PageBean(int totalCount, int totalPage, int currentPage, int pageSize, List<T> list) {
        this.totalCount = totalCount;
        this.totalPage = totalPage;
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }

    public void setListLength(int listLength) {
        this.listLength = listLength;
    }

    public int getListLength() {
        return listLength;
    }
}
