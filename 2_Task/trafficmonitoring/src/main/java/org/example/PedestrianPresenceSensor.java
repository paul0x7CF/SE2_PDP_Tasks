package org.example;

public class PedestrianPresenceSensor extends Device {
    private boolean pedestrianPresent = false;

    public PedestrianPresenceSensor(String name) {
        super(name);
    }

    public void setPedestrianPresent(boolean presence) {
        pedestrianPresent = presence;
        System.out.println("Pedestrian presence status: " + presence + " (Device: " + getName() + ")");
    }

    public boolean isPedestrianPresent() {
        return pedestrianPresent;
    }
}
