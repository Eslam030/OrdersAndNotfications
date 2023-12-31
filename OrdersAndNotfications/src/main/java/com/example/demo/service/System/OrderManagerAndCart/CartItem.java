package com.example.demo.service.System.OrderManagerAndCart;

public abstract class CartItem {
    public Long id ;
    public double price ;
    public abstract double totalPrice () ;
    public CartItem(Long id , double price) {
        this.id = id ;
        this.price = price ;
    }

    public double getPrice() {
        return price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return  "id :" + id + "\n" +
                "price : " + price ;
    }
}
