package com.example.demo.model;

import com.example.demo.service.System.OrderManagerAndCart.CartItem;

public class Product extends CartItem {
    String name , vendor , category ;
    public Product(Long id , double price , String name , String vendor , String category) {
        super(id , price);
        this.name = name ;
        this.category = category ;
        this.vendor = vendor ;
    }
    @Override
    public double totalPrice() {
        return this.price ;
    }

    @Override
    public String toString() {
        return "Serial Number : " + this.id +"\n" +
                "Name : " + this.name + "\n" +
                "Category : " + this.category + "\n" +
                "Vendor : " + this.vendor + "\n" +
                "Price : " + this.price;

    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public String getVendor() {
        return vendor;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }
}
