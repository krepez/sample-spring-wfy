package com.komix.sample.domain;

import java.time.LocalDateTime;

/**
 * @author krepela
 */
public class Sample {
    private String name;
    private LocalDateTime date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }
}
