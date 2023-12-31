package com.example.demo.service.System.OrderManagerAndCart;

import com.example.demo.model.Location;

import java.util.HashMap;

public class feesHandler {
    public static HashMap<Location, Double> fees = new HashMap<>() ;
    public static void addFee (Location location , Double price) {
        fees.put(location , price) ;
    }
    public static Double getFees (Location location){
        return fees.get(location) ;
    }
}
