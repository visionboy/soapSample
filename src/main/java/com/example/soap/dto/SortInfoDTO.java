package com.example.soap.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SortInfoDTO implements Serializable {

    private List<String> data;

    public SortInfoDTO() {
        this.data = new ArrayList<>();
    }

    public SortInfoDTO(List<String> data) {
        this.data = data;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
