package com.example.demo.service.System.Notification;
import java.util.*;

import com.example.demo.service.System.Message.Message;
import com.example.demo.service.System.Message.Template_lang;
import javafx.util.Pair;
public class NotificationManager {
    public static HashMap<String , Integer> MailFrequency = new HashMap<>() , MobilFrequency = new HashMap<>();
    public static HashMap<String , Integer> TemplateFrequency = new HashMap<>() ;
    public static Queue<Notification> notificationQueue = new LinkedList<>();
    public static void GetFirstNotification() {
        if (!notificationQueue .isEmpty()) {
            // front element in the queue
            notificationQueue .peek().send() ;
            if (TemplateFrequency.containsKey(notificationQueue.peek().message.getClass().getSimpleName())) {
                TemplateFrequency.put(notificationQueue.peek().message.getClass().getSimpleName() ,  TemplateFrequency.get(notificationQueue.peek().message.getClass().getSimpleName())+ 1)  ;
            }else {
                TemplateFrequency.put(notificationQueue.peek().message.getClass().getSimpleName() , 1) ;
            }
            if (notificationQueue.peek() instanceof Mail) {
                String mail = ((Mail)notificationQueue.peek()).getEmail() ;
                if (MailFrequency.containsKey(mail)) {
                    MailFrequency.put(mail , MailFrequency.get(mail) + 1) ;
                }else {
                    MailFrequency.put(mail , 1) ;
                }
            }
            if (notificationQueue.peek() instanceof SMS) {
                String phone = ((SMS)notificationQueue.peek()).getPhoneNumber() ;
                if (MobilFrequency .containsKey(phone)) {
                    MobilFrequency .put(phone , MobilFrequency .get(phone) + 1) ;
                }else {
                    MobilFrequency .put(phone , 1) ;
                }
            }
            notificationQueue .poll() ;
        }
    }
    public static ArrayList<String> getTopTemplate () {
        int maxCount = 0 ;
        ArrayList<String> answer = new ArrayList<>() ;
        for (String key : TemplateFrequency.keySet()) {
            Integer value = TemplateFrequency.get(key);
            if (value > maxCount) {
                answer.clear(); ;
                answer.add(key);
                maxCount = value ;
            }else if (value == maxCount) {
                answer.add(key) ;
            }
            System.out.println("Key: " + key + ", Value: " + value);
        }
        return answer ;
    }
    public static ArrayList<String>  getTopMail () {
        int maxCount = 0 ;
        ArrayList<String> answer = new ArrayList<>() ;
        for (String key : MailFrequency.keySet()) {
            Integer value = MailFrequency.get(key);
            if (value > maxCount) {
                answer.clear(); ;
                answer.add(key);
                maxCount = value ;
            }else if (value == maxCount) {
                answer.add(key) ;
            }
            System.out.println("Key: " + key + ", Value: " + value);
        }
        return answer ;
    }
    public static ArrayList<String>  getTopMobile () {
        int maxCount = 0 ;
        ArrayList<String> answer = new ArrayList<>() ;
        for (String key : MobilFrequency.keySet()) {
            Integer value = MobilFrequency.get(key);
            if (value > maxCount) {
                answer.clear(); ;
                answer.add(key);
                maxCount = value ;
            }else if (value == maxCount) {
                answer.add(key) ;
            }
            System.out.println("Key: " + key + ", Value: " + value);
        }
        return answer ;
    }

}
