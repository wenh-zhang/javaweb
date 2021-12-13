package com.validate.domain;

import java.sql.Timestamp;

public class Notice {
    private int id;
    private String title;
    private String details;
    private Timestamp n_time;

    public Timestamp getN_time() {
        return n_time;
    }
    public void setN_time(Timestamp n_time) {
        this.n_time = n_time;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getDetails() {
        return details;
    }
    public void setDetails(String details) {
        this.details = details;
    }


}