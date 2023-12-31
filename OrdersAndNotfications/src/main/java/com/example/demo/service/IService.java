package com.example.demo.service;

import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.UserAccount;

import java.util.List;

public interface IService {
    public boolean registerUser (UserAccount user) ;
    public boolean loginUser (Account user) ;
    public Account getUser (Long id) ;
    public List<Account> getAllUsers () ;
    public List<Product> getAllProducts () ;
    public boolean addOrder (Long OrderId) ;
    public boolean addProduct (Long ProductID) ;
    public Order convertCartToOrder () ;
}
