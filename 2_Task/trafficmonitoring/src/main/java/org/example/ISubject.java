package org.example;

import org.example.observers.IObserver;

public interface ISubject {
    void addObserver(IObserver observer);
    void removeObserver(IObserver observer);
    void notifyObservers(String notifyAbout);
}
