package com.example.demo.model;
import com.example.demo.DataBase;
import com.example.demo.service.Observable;
import com.example.demo.service.Observer;
import com.example.demo.service.System.Notification.NotificationCreator;
import com.example.demo.service.System.OrderManagerAndCart.CartItem;
import com.example.demo.service.System.OrderManagerAndCart.feesHandler;
import org.apache.catalina.User;

import java.util.ArrayList;
public class Order extends CartItem implements Observable {
    // so this class contribute in 2 design patterns should we make a handler to contribute with observer a
    // and make this class only for composite ??
    // the logic of order as a observable in observer design pattern
    ArrayList<Observer> observers ;
    ArrayList<UserAccount> users ;
    @Override
    public void AddObserver(Observer observer) {
        observers.add(observer);
    }
    @Override
    public void RemoveObserver(Observer observer) {
        observers.remove(observer) ;
    }
    @Override
    public void NotifyAll() {
        observers.forEach(observer -> observer.update(status));
    }
    public void payAll() {
        for (Observer observer : observers) {
            if (observer instanceof NotificationCreator){
                UserAccount currentCustomer = DataBase.getUserAccount(((NotificationCreator)observer).getCustomer().getId()) ;
                currentCustomer.setBalance(currentCustomer.getBalance() - ((NotificationCreator)observer).getOrder().getPrice());
            }
        }
    }
    public void deductFeeAll () {
        for (Observer observer : observers) {
            if (observer instanceof NotificationCreator){
                UserAccount currentCustomer = DataBase.getUserAccount(((NotificationCreator)observer).getCustomer().getId()) ;
                currentCustomer.setBalance(currentCustomer.getBalance() - feesHandler.getFees(currentCustomer.getAddress()));
            }
        }
    }
    public void cancelAll() {
        for (Observer observer : observers) {
            if (observer instanceof NotificationCreator){
                UserAccount currentCustomer = DataBase.getUserAccount(((NotificationCreator)observer).getCustomer().getId()) ;
                currentCustomer.setBalance(currentCustomer.getBalance() + ((NotificationCreator)observer).getOrder().getPrice());
            }
        }
        ArrayList<Product> products = getProductsOfTheOrder() ;
        products.forEach(product -> {DataBase.saveProduct(product);});
    }
    private ArrayList<Product> getProductsOfTheOrder () {
        ArrayList<Product> products = new ArrayList<>( ) ;
        for (CartItem item : items) {
            if (item instanceof Order) {
                ArrayList<Product> moreProducts = ((Order) item).getProductsOfTheOrder() ;
                moreProducts.forEach(product -> products.add(product));
            }else {
                products.add((Product) item);
            }
        }
        return products ;
    }

    // the whole logic of the order as a composite in composite design pattern
    ArrayList<CartItem> items ;
    Long userID ;
    Status status ;
    public Order () {
        super(DataBase.lastOrderID, 0.0) ;
        DataBase.lastOrderID ++ ;
        items = new ArrayList<>() ;
        observers = new ArrayList<>() ;
        users = new ArrayList<>( ) ;
    }
    public Order (ArrayList<CartItem> items) {
        super(DataBase.lastOrderID, 0.0) ;
        DataBase.lastOrderID ++ ;
        this.items = new ArrayList<>() ;
        this.observers = new ArrayList<>() ;
        items.forEach(item -> this.items.add(item));
    }
    @Override
    public double totalPrice() {
        double test = 0.0 ;
        for (CartItem i : items){
            if (i instanceof Product) {
                test += i.totalPrice() ;
            }else{
                i.totalPrice() ;
            }
        }
        this.price = test ;
        return test ;
    }
    public ArrayList<Observer> assignObservers () {
        UserAccount account = DataBase.getUserAccount(userID) ;
        if (observers.isEmpty()) {
            NotificationCreator n = new NotificationCreator(this , account , this.status);
        }
        for (CartItem item : items) {
            if (item instanceof Order) {
                ArrayList<Observer>customerInSide ;
                customerInSide = ((Order) item).assignObservers();
                customerInSide.forEach(item1 -> observers.add(item1));
            }
        }
        return observers;
    }
    public ArrayList<UserAccount> getAllUsers () {
        UserAccount account = DataBase.getUserAccount(userID) ;
        if (users.isEmpty()) {
            users.add(account) ;
        }
        for (CartItem item : items) {
            if (item instanceof Order) {
                ArrayList<UserAccount>customerInSide ;
                customerInSide = ((Order) item).getAllUsers ();
                customerInSide.forEach(item1 -> users.add(item1));
            }
        }
        return users ;
    }
    public Status getStatus() {
        return status;
    }
    public Long getUserID() {
        return userID;
    }
    public void setItems(ArrayList<CartItem> items) {
        items.forEach(item -> this.items.add(item));
    }
    public void setStatus(Status status) {
        this.status = status;
        NotifyAll();
    }
    public void setUser(Long userID) {
        this.userID = userID;
    }
    public ArrayList<CartItem> getItems() {
        return items;
    }
    public void addItem (CartItem item) {
        this.items.add(item) ;
    }
    public double getPrice() {
        return this.price ;
    }
    @Override
    public String toString() {
        return super.toString() +
                "User id : " + userID + "\n" +
                "Status : " + status + "\n" +
                "List : " + items  ;
    }
}
