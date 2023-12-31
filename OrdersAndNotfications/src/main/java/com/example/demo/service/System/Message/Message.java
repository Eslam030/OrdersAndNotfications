package com.example.demo.service.System.Message;
import com.example.demo.model.UserAccount;
public abstract class Message {
    public Template_lang template ;
    public abstract Template_lang createLangTemplate (UserAccount account , Long orderID) ;
    public void PrepareMessage (UserAccount customer  , Long orderID){
        template = createLangTemplate(customer , orderID);
        template.createTemplate();
    }

    @Override
    public String toString() {
        return "Message : " + template.getTemplate() ;
    }
}
