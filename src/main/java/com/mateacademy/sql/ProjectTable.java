package com.mateacademy.sql;


import lombok.Getter;

@Getter
public class ProjectTable {
    private String name;
    private Integer developersNumber;
    private String customer;
    private Integer price;
    private Integer cost;
    private String creationDate;

    ProjectTable(String name, Integer developersNumber, String customer, Integer price, Integer cost, String creationDate) {
        this.name = name;
        this.developersNumber = developersNumber;
        this.customer = customer;
        this.price = price;
        this.cost = cost;
        this.creationDate = creationDate;
    }

    public String getName() {
        return this.name;
    }

    public Integer getDevelopersNumber() {
        return this.developersNumber;
    }

    public String getCustomer() {
        return this.customer;
    }

    public Integer getCost() {
        return this.cost;
    }

    public Integer getPrice() {
        return this.price;
    }

    public String getCreationDate() {
        return this.creationDate;
    }

}
