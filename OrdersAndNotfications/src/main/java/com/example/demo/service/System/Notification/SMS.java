package com.example.demo.service.System.Notification;

import com.example.demo.service.System.Message.Message;

public class SMS extends Notification {
    String phoneNumber ;
    public SMS (Message message , String phoneNumber){
        super(message);
        this.phoneNumber = phoneNumber ;
    }
    @Override
    public void send() {
        // the implementation of the send function using sms
        System.out.println("Sending using SMS");
        System.out.println("Using phone number " + phoneNumber);
        System.out.println(message);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }
}
