package com.kiu.library.model.resouceModel;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ResourceDataInfo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("items")
    private List<ResourceInfo> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<ResourceInfo> getItems() {
        return items;
    }

    public void setItems(List<ResourceInfo> items) {
        this.items = items;
    }
}
