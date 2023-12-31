package com.example.demo.service.System.Authentication;

import com.example.demo.DataBase;
import com.example.demo.model.Account;
import com.example.demo.model.UserAccount;

public class UserAuth extends Authorization{
    public static boolean signIn (UserAccount account) {
        UserAccount test = DataBase.getUserAccount(account.getId()) ;
        if (test != null) {
            if (test.getPassword().equals(account.getPassword()) && test.getUsername().equals(account.getUsername())) {
                DataBase.setAuthUser(test);
                return true ;
            }else {
                return false ;
            }
        }else {
            return false ;
        }
    }
    public static boolean signUp (UserAccount account) {
        if (DataBase.getUserAccount(account.getId()) != null) {
            return false ;
        }
        DataBase.saveUserAccount(account);
        return true ;
    }
}
