package com.example.myfamilyssuper;

public class Product {
    private String category;
    private String image;
    private String name;
    private double price;

    public Product(String category, String image, String name, double price){
        this.category=category;
        this.image=image;
        this.name=name;
        this.price=price;
    }

    public Product(){

    }
    public String getCategory(){
        return category;
    }
    public String getImage(){
        return image;
    }
    public String getName(){
        return name;
    }
    public double getPrice(){
        return price;
    }

    public void setCategory(String category){
        this.category=category;
    }
    public void setImage(String image){
        this.image=image;
    }
    public void setName(String name){
        this.name=name;
    }
    public void setPrice(double price){
        this.price=price;
    }
}
