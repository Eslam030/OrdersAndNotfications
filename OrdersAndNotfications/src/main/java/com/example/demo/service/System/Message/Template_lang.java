package com.example.demo.service.System.Message;

import com.example.demo.model.UserAccount;

public abstract class Template_lang {
    String template ;
    Long orderID ;
    UserAccount customer ;
    public Template_lang () {}
    public Template_lang (UserAccount customer , Long orderID) {
        this.customer = customer ;
        this.orderID = orderID ;
    }

    public String getTemplate() {
        return template;
    }
    public abstract void createTemplate () ;
}
