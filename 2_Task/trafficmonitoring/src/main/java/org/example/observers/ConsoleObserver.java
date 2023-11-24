package org.example.observers;

import org.example.ELightStatus;

public class ConsoleObserver implements IObserver {
    @Override
    public void update(String notifyAbout) {
        System.out.println("ConsoleObserver: " + notifyAbout);
    }
}
