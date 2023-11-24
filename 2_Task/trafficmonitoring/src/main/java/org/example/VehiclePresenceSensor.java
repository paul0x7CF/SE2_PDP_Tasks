package org.example;

public class VehiclePresenceSensor extends Device {
    private boolean vehiclePresent = false;

    public VehiclePresenceSensor(String name) {
        super(name);
    }

    public boolean isVehiclePresent() {
        return vehiclePresent;
    }

    public void setVehiclePresent(boolean presence) {
        vehiclePresent = presence;
        System.out.println("Vehicle presence detected: " + presence + " (Device: " + getName() + ")");
    }
}
