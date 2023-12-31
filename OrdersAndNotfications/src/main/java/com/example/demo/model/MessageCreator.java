package com.example.demo.model;

import com.example.demo.service.System.Message.Message;
import com.example.demo.service.System.Message.OrderMessage;
import com.example.demo.service.System.Message.ShipmentMessage;

public class MessageCreator {
    public static Message createMessage (MessageType messageType)  {
        if (messageType.equals(MessageType.Shipping)) {
            return new ShipmentMessage() ;
        }
        if (messageType.equals(MessageType.Order)) {
            return new OrderMessage() ;
        }
        return null ;
    }
    public static Message createMessage (Status status) {
        if (status.equals(Status.Shipped)) {
            return new ShipmentMessage() ;
        }
        if (status.equals(Status.Placed)) {
            return new OrderMessage() ;
        }
        return null ;
    }
}
