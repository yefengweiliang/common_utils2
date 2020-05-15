package com.utils.pojo;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * 分页继承类
 *
 * @author wanghongshen
 * @date 2020-03-01
 */
@AllArgsConstructor
@NoArgsConstructor
public class PageVo {
    protected int page = 1;//当前页数，默认为1
    protected int rows = 15;//每页显示条数,默认为15
    private int offset = 0;//每页起始条数
    private int limit = 15;//每页结束条数
    private String sort; //排序字段
    private String order; //排序顺序

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public int getLimit() {
        return (page - 1) * rows;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public int getOffset() {
        return rows;
    }

    public void setOffset(int offset) {
        this.offset = rows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }
}
