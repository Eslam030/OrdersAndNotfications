package com.example.demo.service.System.Message;
import com.example.demo.model.Lang;
import com.example.demo.model.UserAccount;
public class ShipmentMessage extends Message{
    @Override
    public Template_lang createLangTemplate(UserAccount account  , Long orderID){
        if (account.getPreferedLang().equals(Lang.Arabic)) {
            return new ShipmentMessageArabic(account , orderID) ;
        }else if (account.getPreferedLang().equals(Lang.English)){
            return new ShipmentMessageEnglish(account , orderID) ;
        }else {
            return null ;
        }
    }
    @Override
    public String toString() {
        return super.toString();
    }
}
