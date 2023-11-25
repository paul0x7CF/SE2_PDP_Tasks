package org.example.devices;

public class TrafficLightSensorActuator extends Device {
    private ELightStatus lightStatus = ELightStatus.RED;

    public TrafficLightSensorActuator(String name) {
        super(name);
    }

    public void setLightStatus(ELightStatus status) {
        lightStatus = status;
        notifyObservers("Traffic light status changed: " + status + " (Device: " + getName() + ")");
    }

    public ELightStatus getLightStatus() {
        return lightStatus;
    }
}
