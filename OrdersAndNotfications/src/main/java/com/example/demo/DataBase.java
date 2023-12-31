package com.example.demo;
import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.UserAccount;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
// this is the database
public class DataBase{
    public static Account currentAuthUser = null ;
    public static Long lastCartID = 1L ;
    public static Long lastOrderID = 1L ;
//    public static HashMap<Long , Account> users = new HashMap<>() ;
    public static HashMap<Long , UserAccount> userAccounts = new HashMap<>() ;
    public static HashMap<Long , Order> orders = new HashMap<>() ;
    public static HashMap<Long , Product> products = new HashMap<>() ;
    public static HashMap<Long , ArrayList<Order>> userUnpaidOrders = new HashMap<>() ; // for storing the drafted orders per every user using user id
//    public static void saveUser (Account account) {
//        users.put(account.getId() , account) ;
//    }
    public static void saveUserAccount (UserAccount account) {userAccounts.put(account.getId() , account) ;}
    public static UserAccount getUserAccount (Long accountID) {return userAccounts.get(accountID) ;}
//    public static Account getUser (Long userID) {
//        return users.get(userID) ;
//    }
    public static Order getOrder (Long orderID) {
        return orders.get(orderID) ;
    }
    public static void saveOrder (Order order) {
        orders.put(order.getId() , order) ;
    }
    public static Product getProduct (Long productId) {
        return products.get(productId) ;
    }
    public static void saveProduct (Product product) {
        products.put(product.getId() , product) ;
    }
    public static Account getCurrentAuthUser () {
        return currentAuthUser ;
    }
    public static void deleteProduct (Long ProductId) {
        products.remove(ProductId) ;
    }
    public static List<Account> getAllUserAccounts () {
        ArrayList<Account> out = new ArrayList<>() ;
        userAccounts.forEach((k , v) -> out.add(v));
        return out ;
    }
    public static void setAuthUser (Account user){
        currentAuthUser = user ;
    }
    public static List<Order> getAllOrders () {
        List<Order> out = new ArrayList<>() ;
        orders.forEach((k , v) -> out.add(v));
        return out ;
    }
    public static List<Product> getAllProducts () {
        List<Product> out = new ArrayList<>() ;
        products.forEach((k , v) -> out.add(v));
        return out ;
    }
}
