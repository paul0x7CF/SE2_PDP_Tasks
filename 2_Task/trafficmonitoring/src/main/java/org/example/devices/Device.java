package org.example.devices;

import org.example.observers.ConsoleObserver;
import org.example.observers.HistoryObserver;
import org.example.observers.IObserver;

import java.util.ArrayList;
import java.util.List;

public abstract class Device implements ISubject {
    private String name;
    private List<IObserver> observers = new ArrayList<>();

    public Device(String name) {
        setName(name);
        addObserver(new ConsoleObserver());
        addObserver(new HistoryObserver());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void addObserver(IObserver observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(IObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String notifyAbout) {
        for (IObserver observer : observers) {
            observer.update(notifyAbout);
        }
    }
}
