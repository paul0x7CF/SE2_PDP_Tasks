package org.example;

class TrafficLightSensorActuator extends Device {
    private LightStatus lightStatus = LightStatus.RED;

    public TrafficLightSensorActuator(String name) {
        super(name);
    }

    public void setLightStatus(LightStatus status) {
        lightStatus = status;
        System.out.println("Traffic light status changed: " + status + " (Device: " + getName() + ")");
    }

    public LightStatus getLightStatus() {
        return lightStatus;
    }
}
