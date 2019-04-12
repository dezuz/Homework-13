package com.mateacademy.sql;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectTable {
    private String name;
    private Integer developersNumber;
    private String customer;
    private Integer price;
    private Integer cost;
    private String creationDate;
}
