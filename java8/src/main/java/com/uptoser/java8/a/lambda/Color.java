package com.uptoser.java8.a.lambda;

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
