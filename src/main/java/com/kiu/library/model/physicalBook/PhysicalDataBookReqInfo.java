package com.kiu.library.model.physicalBook;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.kiu.library.model.resouceModel.BookResourceInfo;
import com.kiu.library.model.resouceModel.PhysicalBookInfo;
import com.kiu.library.model.resouceModel.PhysicalBookInfoNew;

import java.util.List;

public class PhysicalDataBookReqInfo {

    @JsonProperty("total")
    private Integer total;

    @JsonProperty("items")
    private List<PhysicalBookInfoNew> items;

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public List<PhysicalBookInfoNew> getItems() {
        return items;
    }

    public void setItems(List<PhysicalBookInfoNew> items) {
        this.items = items;
    }
}
