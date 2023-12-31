package com.example.demo.controller;
import com.example.demo.DataBase;
import com.example.demo.model.*;
import com.example.demo.service.System.OrderManagerAndCart.Cart;
import com.example.demo.service.Services;
import com.example.demo.service.System.OrderManagerAndCart.feesHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
@RestController
public class controller {
    @Autowired
    Services services;
    public controller () {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            public void run() {
                if (services != null){
                    services.checkNotification();
                }
            }
        }, 0, 2000) ;
        Product p = new Product(12L , 12.5 , "Test" , "Vendor1" , "Sweets" ) ;
        DataBase.saveProduct(p) ;
        p = new Product(13L , 13.5 , "Test" , "Vendor2" , "Slaves" ) ;
        DataBase.saveProduct(p) ;
        p = new Product(14L , 14.5 , "Test" , "Vendor1" , "Sweets") ;
        DataBase.saveProduct(p) ;
        p = new Product(15L , 15.5 , "Test" , "Vendor2" , "Slaves") ;
        DataBase.saveProduct(p) ;
        feesHandler.addFee(Location.Dokkki , 20.0);
        feesHandler.addFee(Location.Matarya , 10.0);
        feesHandler.addFee(Location.Wayly , 5.0);
        feesHandler.addFee(Location.Ma3ady , 30.0);
    }
    @GetMapping("/getUser")
    public Account getUser (@RequestParam(value = "id" ) Long id) {
        return services.getUser(id) ;
    }
    @PostMapping("/AddUserAccount")
    public ResponseEntity<String> add (@RequestBody UserAccount ac) {
        Cart c = new Cart() ;
        ac.setCart(c);
        if (services.registerUser(ac)){
            return ResponseEntity.status(HttpStatus.OK).body("Registered Successfully") ;
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("Id Already Exists") ;
        }
    }
    @PostMapping("/LoginUser")
    public ResponseEntity<String> login (@RequestBody Account ac) {
        if (DataBase.getCurrentAuthUser() == null) {
            if (services.loginUser(ac)) {
                return ResponseEntity.status(HttpStatus.OK).body("Logged In Successfully") ;
            }else {
                return ResponseEntity.status(HttpStatus.OK).body("Wrong Credentials") ;
            }
        }else {
            if (ac.getId().equals(DataBase.getCurrentAuthUser().getId())) {
                return ResponseEntity.status(HttpStatus.OK).body("You Are Already Logged in") ;
            }else {
                return ResponseEntity.status(HttpStatus.OK).body("Can't Log in Because there is a logged in user") ;
            }
        }
    }
    @PutMapping("/Logout")
    public ResponseEntity<String> logout () {
        if (DataBase.getCurrentAuthUser() == null) {
            return ResponseEntity.status(HttpStatus.OK).body("Can't logout Because there is no Logged in user") ;
        }else {
            DataBase.setAuthUser(null);
            return ResponseEntity.status(HttpStatus.OK).body("Logged out successfully") ;
        }
    }
    @GetMapping("/CheckOut")
    public Order checkOut () {
        return services.convertCartToOrder() ;
    }
    @GetMapping("/Products")
    public List<Product> getAllProducts () {
        return services.getAllProducts() ;
    }
    @PostMapping("/AddProductToCart")
    public ResponseEntity<String>  addProductToCart (@RequestParam(value = "id") Long ProductID) {
        if (services.addProduct(ProductID)) {
            return ResponseEntity.status(HttpStatus.OK).body("Done") ;
        }else {
            return ResponseEntity.status(HttpStatus.OK).body("is not available product") ;
        }
    }
    @PostMapping("/AddOrderToCart")
    public ResponseEntity<String>  addOrderToCart (@RequestParam(value = "id") Long OrderID) {
        String response = services.addOrder(OrderID);
        return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
    @PutMapping("/PayOrder")
    public ResponseEntity<String> payOrder (@RequestParam (value = "id") Long OrderId) {
        String response = services.payOrder(OrderId);
        return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
    @PutMapping("/ShipOrder")
    public ResponseEntity<String> shipOrder (@RequestParam (value = "id") Long OrderId ) {
        String  response =  services.shipOrder(OrderId) ;
        return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
    @PostMapping("/addBalance")
    public ResponseEntity<String> addBalance(@RequestBody Double balance) {
        services.addBalance (DataBase.getCurrentAuthUser() , balance) ;
        return ResponseEntity.status(HttpStatus.OK).body("Done") ;
    }
    @GetMapping("/CurrentBalance")
    public ResponseEntity<String> getBalance () {
        Double balance = services.getBalance(DataBase.getCurrentAuthUser()) ;
        return ResponseEntity.status(HttpStatus.OK).body(balance.toString()) ;
    }
    @PutMapping("/CancelPlacement")
    public ResponseEntity<String> cancelPlacement (@RequestParam(value = "id") Long OrderID) {
        String response =  services.cancelPlacement(OrderID) ;
        return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
    @PutMapping("/CancelShipment")
    public ResponseEntity<String> cancelShipment (@RequestParam(value = "id") Long OrderID) {
        String  response =  services.cancelShipment(OrderID) ;
        return ResponseEntity.status(HttpStatus.OK).body(response) ;
    }
    @GetMapping("/GetOrder")
    public Order getOrder (@RequestParam(value = "id") Long OrderID) {
        return services.getOrder(OrderID) ;
    }
    @GetMapping("/TopMobile")
    public ArrayList<String> topMobile () {
        return services.TopMobile() ;
    }
    @GetMapping("/TopMail")
    public ArrayList<String> topMail () {
        return services.TopMail() ;
    }
    @GetMapping("TopTemplate")
    public ArrayList<String> topTemplate () {
        return services.TopTemplate() ;
    }
}

