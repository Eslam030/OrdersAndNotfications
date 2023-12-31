package com.example.demo.service.System.Message;

import com.example.demo.model.UserAccount;

public class ShipmentMessageEnglish extends Template_lang {
    @Override
    public void createTemplate() {
        this.template = "Hello mr " + this.customer.getName() + " You order with id " + this.orderID + " Has been shipped " +
                "thank you for using our store" ;
    }
    public ShipmentMessageEnglish (UserAccount customer , Long orderID) {
        super(customer , orderID);
    }
}
