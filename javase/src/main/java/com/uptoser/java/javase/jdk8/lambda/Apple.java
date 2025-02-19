package com.uptoser.java.javase.jdk8.lambda;

public class Apple {

    private Color color;
    private Integer weight;
    private String country;

    public Apple(Integer weight,Color color,String country) {
        this.weight = weight;
        this.color = color;
        this.country = country;
    }
    public Apple(Integer weight) {
        this.weight = weight;
    }

    public Apple() {
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public Integer getWeight() {
        return weight;
    }


    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

}
