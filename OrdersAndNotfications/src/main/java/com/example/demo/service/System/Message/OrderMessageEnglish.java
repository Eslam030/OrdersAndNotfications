package com.example.demo.service.System.Message;
import com.example.demo.model.UserAccount;
public class OrderMessageEnglish extends Template_lang {
    @Override
    public void createTemplate() {
        template = "Hello MR " + customer.getName() + " Your order with id " + orderID + " is placed Successfully " +
                "it will be shipped soon Thank You for using our store" ;
        // write the template and assign the result to this.template
    }
    public OrderMessageEnglish (UserAccount customer , Long orderID) {
        super(customer , orderID);
    }
}
