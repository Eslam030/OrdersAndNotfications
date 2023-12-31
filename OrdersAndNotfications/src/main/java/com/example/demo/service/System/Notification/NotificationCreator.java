package com.example.demo.service.System.Notification;
import com.example.demo.model.MessageCreator;
import com.example.demo.model.Order;
import com.example.demo.model.Status;
import com.example.demo.model.UserAccount;
import com.example.demo.service.Observer;
import com.example.demo.service.System.Message.Message;

public class NotificationCreator implements Observer {
    UserAccount customer ;
    Status status ;
    Order subject ;
    public NotificationCreator(Order subject , UserAccount customer , Status status){
        this.subject = subject ;
        this.customer = customer ;
        this.status = status ;
        subject.AddObserver(this);
    }
    private void setNotificationUsingMail (Message message , String mail ) {
        NotificationManager.notificationQueue.add(new Mail(message , mail)) ;
    }
    private void sendNotificationUsingSMS (Message message , String phone ) {
        NotificationManager.notificationQueue.add(new SMS(message , phone)) ;
    }
    private void NotificationSend () {
        Message message = MessageCreator.createMessage(status) ;
        if (message != null){
            message.PrepareMessage(customer , subject.getId());
            setNotificationUsingMail(message , customer.getMail());
            sendNotificationUsingSMS(message , customer.getPhone()) ;
        }
    }
    @Override
    public void update(Status status) {
        this.status = status ;
        NotificationSend();
    }
    @Override
    public String toString() {
        return "NotificationCreator{" +
                "customer=" + customer +
                '}';
    }

    public UserAccount getCustomer() {
        return customer;
    }
    public Order getOrder() {
        return subject;
    }
}
