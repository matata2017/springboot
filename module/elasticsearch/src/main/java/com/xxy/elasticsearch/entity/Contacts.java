package com.xxy.elasticsearch.entity;

import lombok.Data;

import java.util.List;

/**
 * @author xxy
 * 业主对象
 */
@Data
public class Contacts {
    /**
     * 业主名称
     */
    private String name;
    /**
     * 业主电话
     */
    private List<String> telephones;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getTelephones() {
        return telephones;
    }

    public void setTelephones(List<String> telephones) {
        this.telephones = telephones;
    }
}
