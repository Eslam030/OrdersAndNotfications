package com.example.demo.service;
import com.example.demo.DataBase;
import com.example.demo.model.Account;
import com.example.demo.model.Order;
import com.example.demo.model.Product;
import com.example.demo.model.UserAccount;
import com.example.demo.model.Status;
import com.example.demo.service.System.Authentication.UserAuth;
import com.example.demo.service.System.Notification.NotificationManager;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class Services {
    public boolean loginUser(Account user) {
        return UserAuth.signIn(user) ;
    }
    public boolean registerUser(UserAccount user) {
        return UserAuth.signUp(user) ;
    }
    public Account getUser(Long id) {
        return DataBase.getUserAccount(id) ;
    }
    public List<Account> getAllUsers () {
        return DataBase.getAllUserAccounts() ;
    }
    public List<Product> getAllProducts() {
        return DataBase.getAllProducts() ;
    }
    public String addOrder(Long OrderID) {
        // max 2
        // can't add order of my self
        // check location
        UserAccount customer = DataBase.getUserAccount(DataBase.getCurrentAuthUser().getId()) ;
        if (customer.getCart().getNumberOfOrders() < 2) {
            Order order = DataBase.getOrder(OrderID) ;
            if (order != null){
                UserAccount friend = DataBase.getUserAccount(order.getUserID()) ;
                if (friend.getAddress().equals(customer.getAddress())) {
                    if (order.getStatus().equals(Status.Saved)) {
                        if (!order.getUserID().equals(DataBase.currentAuthUser.getId())) {
                            DataBase.getUserAccount(DataBase.getCurrentAuthUser().getId()).getCart().addItem(order);
                            return "Done ";
                        }else {
                            return "Can't Add you order to your order";
                        }
                    }else{
                        return "The order you want to add should be saved" ;
                    }
                }else {
                    return "You and your friend should be in the same location" ;
                }
            }else {
                return "No order with this id" ;
            }
        }else {
            return "You can't add more than 2 orders" ;
        }
    }
    public boolean addProduct(Long ProductID) {
        if (DataBase.getProduct(ProductID) != null){
            UserAccount currentAccount = DataBase.getUserAccount(DataBase.getCurrentAuthUser().getId()) ;
            currentAccount.getCart().addItem(DataBase.getProduct(ProductID));
            DataBase.deleteProduct(ProductID);
            return true ;
        }else {
            return false ;
        }
    }
    public Order convertCartToOrder() {
        UserAccount currentAccount = DataBase.getUserAccount(DataBase.getCurrentAuthUser().getId()) ;
        Order order = new Order(currentAccount.getCart().getItems());
        order.setUser(currentAccount.getId());
        order.totalPrice() ;
        order.assignObservers() ;
        order.setStatus(Status.Saved);
        DataBase.orders.put(order.getId() , order) ;
        currentAccount.getCart().getItems().clear();
        return order ;
    }

    // check if there is notification to send
    public void checkNotification () {
        NotificationManager.GetFirstNotification();
    }

    // pay the order
    public String payOrder (Long OrderID) {
        // check user done
        // balance deduction done
        // fee random ??
        Order order = DataBase.getOrder(OrderID) ;
        if (order != null) {
            if (order.getUserID().equals(DataBase.getCurrentAuthUser().getId())) {
                order.setStatus(Status.Placed);
                order.payAll();
                return "Done" ;
            }else {
                return "You Don't have authorization to do this" ;
            }
        }else {
            return "No order with this id" ;
        }
    }

    public String shipOrder (Long OrderID) {
        Order order = DataBase.getOrder(OrderID) ;
        if (order != null){
            if (order.getUserID().equals(DataBase.getCurrentAuthUser().getId())) {
                if (order.getStatus().equals(Status.Placed) || order.getStatus().equals(Status.CancelledShipment)) {
                    order.setStatus(Status.Shipped);
                    order.deductFeeAll(); ;
                    return "Done" ;
                }else {
                    return "Your order should be placed or canceled its shipment before to be shipped" ;
                }
            }else {
                return "You Don't have authorization to do this" ;
            }
        }else {
            return "No order with this id" ;
        }
    }
    public void addBalance (Account account , double balance) {
        UserAccount customer = DataBase.getUserAccount(account.getId()) ;
        customer.setBalance(customer.getBalance() + balance);
    }
    public double getBalance (Account customer) {
        return DataBase.getUserAccount(customer.getId()).getBalance() ;
    }

    public String cancelPlacement (Long OrderID) {
        Order order = DataBase.getOrder(OrderID) ;
        if (order != null) {
            if (!order.getStatus().equals(Status.Saved)) {
                order.cancelAll() ;
                return "Done" ;
            }else {
                return "Yon can't cancel a non placed order" ;
            }
        }else {
            return "No order with this id" ;
        }
    }
    public String cancelShipment (Long OrderID) {
        Order order = DataBase.getOrder(OrderID) ;
        if (order != null) {
            if (order.getStatus().equals(Status.Shipped)) {
                order.setStatus(Status.CancelledShipment);
                return "Done" ;
            }else {
                return "You Can't cancel shipment for non shipped order" ;
            }
        }else {
            return "No order with this id" ;
        }
    }
    public Order getOrder (Long OrderID){
        return DataBase.getOrder(OrderID) ;
    }

    public ArrayList<String> TopMail () {
        return NotificationManager.getTopMail() ;
    }
    public ArrayList<String> TopMobile () {
        return NotificationManager.getTopMobile () ;
    }
    public ArrayList<String> TopTemplate () {
        return NotificationManager.getTopTemplate() ;
    }

}
