package com.example.demo.service.System.Notification;

import com.example.demo.service.System.Message.Message;

public class Mail extends Notification {
    String email ;
    public Mail (Message message , String email){
        super(message);
        this.email = email ;
    }
    @Override
    public void send() {
        // the implementation of the send function using main
        System.out.println("Sending using Mail");
        System.out.println("Using mail " + email);
        System.out.println(message);
    }

    public String getEmail() {
        return email;
    }
}
