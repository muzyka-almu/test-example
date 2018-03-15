package com.example.testing.service;

import org.springframework.stereotype.Component;

@Component
public class TestService {
    private String data = "default data";

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
