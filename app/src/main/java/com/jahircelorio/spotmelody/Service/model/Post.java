package com.jahircelorio.spotmelody.Service.model;

import java.util.Collection;
import java.util.List;

public class Post {
    public Collection<Data> getData() {
        return data;
    }

    public void setData(List<Data> data) {
        this.data = data;
    }

    private List<Data> data;

}
