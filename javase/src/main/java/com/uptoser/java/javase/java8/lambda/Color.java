package com.uptoser.java.javase.java8.lambda;

public enum Color {
    Green("Green"),Red("Red"),Yellow("Yellow");
    Color(String colorString){
        this.colorString = colorString;
    }
    private String colorString;

    @Override
    public String toString() {
        return this.colorString;
    }
}
