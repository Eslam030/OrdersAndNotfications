
package com.example.demo;

import com.example.demo.model.Lang;
import com.example.demo.model.Location;
import com.example.demo.model.UserAccount;
import com.example.demo.service.System.Message.*;
import com.example.demo.service.System.Notification.Mail;
import com.example.demo.service.System.Notification.Notification;
import com.example.demo.service.System.Notification.NotificationManager;
import com.example.demo.service.System.Notification.SMS;
import com.example.demo.service.System.OrderManagerAndCart.Cart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
	public static void main(String[] args) {
//		SpringApplication.run(DemoApplication.class, args);
		// convert to shipping done
		// deduction main price done and
		// user add balance done
		// headed to near-by locations, done
		// check auth of the order sipping and payment done
		// order cancellation Done
		// login and register using auth not implemented in the service done
		// (fees)
		// Stat
		Message s = new ShipmentMessage() ;
		UserAccount u = new UserAccount(12L , "12" , "12" , new Cart() , "12" , "12" , Location.Dokkki , Lang.Arabic , "test") ;
		s.PrepareMessage(u , 1L);
		Notification m = new Mail(s , u.getMail()) ;
		NotificationManager.notificationQueue.add(m) ;
		m = new Mail(s , u.getMail()) ;
		s = new OrderMessage() ;
		s.PrepareMessage(u , 1L);
		NotificationManager.notificationQueue.add(m) ;
		m = new SMS(s , u.getPassword()) ;
		NotificationManager.notificationQueue.add(m) ;
		NotificationManager.GetFirstNotification(); ;
		NotificationManager.GetFirstNotification(); ;
		NotificationManager.GetFirstNotification(); ;
		NotificationManager.GetFirstNotification(); ;
		NotificationManager.GetFirstNotification(); ;
		System.out.println(NotificationManager.TemplateFrequency);
		System.out.println(NotificationManager.MailFrequency);
		System.out.println(NotificationManager.MobilFrequency);
	}
}
