package com.example.demo.service.System.Message;

import com.example.demo.model.UserAccount;

public class ShipmentMessageArabic extends Template_lang {
    @Override
    public void createTemplate() {
        this.template = "اصلا بكم استاذ " + this.customer.getName() +  " طلبيتك التي رقمها التعريفي " + this.orderID
                + " قد تم شحتها و نشكرك على استخدام متجرنا" ;
    }
    public ShipmentMessageArabic (UserAccount customer , Long orderID) {
        super(customer , orderID);
    }
}
