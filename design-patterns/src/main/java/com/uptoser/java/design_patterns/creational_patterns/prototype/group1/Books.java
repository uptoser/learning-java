package com.uptoser.java.design_patterns.creational_patterns.prototype.group1;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class Books implements Cloneable, Serializable {
    private String bookName;
    @Override
    public Books clone() throws CloneNotSupportedException {
        return (Books)super.clone();
    }
}