package com.example.demo.service;

public interface Observable {
    public void AddObserver (Observer observer) ;
    public void RemoveObserver (Observer observer) ;
    public void NotifyAll() ;
}
