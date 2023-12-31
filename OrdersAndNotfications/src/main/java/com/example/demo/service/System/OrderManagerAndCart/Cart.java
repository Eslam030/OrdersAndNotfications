package com.example.demo.service.System.OrderManagerAndCart;

import com.example.demo.DataBase;
import com.example.demo.model.Order;

import java.util.ArrayList;
import java.util.List;

public class Cart {
    Long id ;
    ArrayList<CartItem> items ;
    int numberOfOrders = 0 ;
    public Cart () {
        this.id = DataBase.lastCartID ;
        DataBase.lastCartID ++ ;
        this.items = new ArrayList<>() ;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void addItem (CartItem item) {
        if (item instanceof Order) {
            numberOfOrders ++ ;
        }
        this.items.add(item);
    }

    public void setItems(ArrayList<CartItem> items) {
        this.items = items;
    }

    public ArrayList<CartItem> getItems() {
        return items;
    }

    public int getNumberOfOrders() {
        return numberOfOrders;
    }
}
