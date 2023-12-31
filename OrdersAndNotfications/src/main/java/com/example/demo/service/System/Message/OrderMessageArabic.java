package com.example.demo.service.System.Message;
import com.example.demo.model.UserAccount;
public class OrderMessageArabic extends Template_lang {
    @Override
    public void createTemplate() {
        template = "اهلا بك استاذ " + customer.getName() + " طلبيتك التي رقمها التعريفي  " + orderID + " تم وضعها بنجاح و سوف يتم شحنها قريبا" +
                " شكرا لك لاستخدام متجرنا " ;
        // write the template and assign the result to this.template
    }
    public OrderMessageArabic (UserAccount customer , Long orderID) {
        super(customer , orderID);
    }
}
