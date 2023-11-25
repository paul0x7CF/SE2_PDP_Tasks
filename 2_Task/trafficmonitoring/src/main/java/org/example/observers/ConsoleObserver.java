package org.example.observers;

public class ConsoleObserver implements IObserver {
    @Override
    public void update(String notifyAbout) {
        System.out.println("ConsoleObserver: " + notifyAbout);
    }
}
