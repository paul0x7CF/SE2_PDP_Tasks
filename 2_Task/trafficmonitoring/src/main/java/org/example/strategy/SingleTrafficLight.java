package org.example.strategy;

import org.example.devices.ELightStatus;
import org.example.devices.PedestrianPresenceSensor;
import org.example.devices.TrafficLightSensorActuator;
import org.example.devices.VehiclePresenceSensor;

public class SingleTrafficLight implements ISimulationStrategy{

    static final int VEHICLE_TRAFFIC = 0;
    static final int PEDESTRIAN_TRAFFIC = 1;
    static final int VEHICLE_WAITING = 2;
    static final int PEDESTRIAN_WAITING = 3;

    int state = VEHICLE_TRAFFIC;
    String[] simulationStateNames = {"Vehicle Traffic", "Pedestrian Traffic", "Vehicle Waiting",
            "Pedestrian Waiting"};

    PedestrianPresenceSensor pedestrianSensor = new PedestrianPresenceSensor("Pedestrian Present");
    VehiclePresenceSensor vehiclePresenceSensorLeftLane = new VehiclePresenceSensor("Left Lane Vehicle Present");
    VehiclePresenceSensor vehiclePresenceSensorRightLane = new VehiclePresenceSensor("Right Lane Vehicle Present");
    TrafficLightSensorActuator vehicleTrafficLightLeftLane = new TrafficLightSensorActuator("Traffic Light Vehicles Left Lane");
    TrafficLightSensorActuator vehicleTrafficLightRightLane = new TrafficLightSensorActuator("Traffic Light Vehicles Right Lane");
    TrafficLightSensorActuator pedestrianTrafficLight1 = new TrafficLightSensorActuator("Traffic Light Pedestrians 1");
    TrafficLightSensorActuator pedestrianTrafficLight2 = new TrafficLightSensorActuator("Traffic Light Pedestrians 2");

    private void switchToVehicleTraffic() {
        pedestrianTrafficLight1.setLightStatus(ELightStatus.RED);
        pedestrianTrafficLight2.setLightStatus(ELightStatus.RED);

        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.ORANGE);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.ORANGE);

        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.GREEN);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.GREEN);
    }

    private void switchToPedestrianTraffic() {
        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.ORANGE);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.ORANGE);

        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.RED);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.RED);

        pedestrianTrafficLight1.setLightStatus(ELightStatus.GREEN);
        pedestrianTrafficLight2.setLightStatus(ELightStatus.GREEN);
    }
    @Override
    public void runSimulation(int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("STEP: " + i + " (State: " + simulationStateNames[state] + ")");

            switch (state) {
                case VEHICLE_TRAFFIC -> {
                    // we use the modulo operator to have different simulation results in different steps,
                    // but keep them deterministic (else we could use a random variable)
                    if (i % 3 == 0) {
                        pedestrianSensor.setPedestrianPresent(true);
                        state = PEDESTRIAN_WAITING;
                    }
                }
                case PEDESTRIAN_WAITING -> {
                    switchToPedestrianTraffic();
                    pedestrianSensor.setPedestrianPresent(false);
                    state = PEDESTRIAN_TRAFFIC;
                }
                case PEDESTRIAN_TRAFFIC -> {
                    if (i % 3 != 0) {
                        // a vehicle arrived
                        if (i % 2 == 0) {
                            vehiclePresenceSensorLeftLane.setVehiclePresent(true);
                        } else {
                            vehiclePresenceSensorRightLane.setVehiclePresent(true);
                        }
                    }
                    state = VEHICLE_WAITING;
                }
                case VEHICLE_WAITING -> {
                    switchToVehicleTraffic();
                    vehiclePresenceSensorRightLane.setVehiclePresent(false);
                    vehiclePresenceSensorLeftLane.setVehiclePresent(false);
                    state = VEHICLE_TRAFFIC;
                }
            }
        }

    }
}
