package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResourceDataBookReqInfo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("items")
    private List<BookResourceInfo> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<BookResourceInfo> getItems() {
        return items;
    }

    public void setItems(List<BookResourceInfo> items) {
        this.items = items;
    }
}
