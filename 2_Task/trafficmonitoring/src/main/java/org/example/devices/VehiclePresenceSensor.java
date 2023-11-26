package org.example.devices;

public class VehiclePresenceSensor extends Device {
    private boolean vehiclePresent = false;

    public VehiclePresenceSensor(String name) {
        super(name);
    }

    public void setVehiclePresent(boolean presence) {
        vehiclePresent = presence;
        notifyObservers("Vehicle presence detected: " + presence + " (Device: " + getName() + ")");
    }

    public boolean isVehiclePresent() {
        return vehiclePresent;
    }
}
