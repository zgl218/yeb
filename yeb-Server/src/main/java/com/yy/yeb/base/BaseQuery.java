package com.yy.yeb.base;


/**
 *
 * @author xingtong
 */
public class BaseQuery {
    //当前页
    private Integer currentPage=1;
    // 每页显示的记录数
    private Integer size=10;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
