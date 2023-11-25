package org.example.strategy;

import org.example.devices.ELightStatus;
import org.example.devices.PedestrianPresenceSensor;
import org.example.devices.TrafficLightSensorActuator;
import org.example.devices.VehiclePresenceSensor;

public class FourWayIntersection implements ISimulationStrategy {

    static final int VEHICLE_AND_PEDESTRIAN_TRAFFIC_HORIZONTAL = 0;
    static final int VEHICLE_AND_PEDESTRIAN_TRAFFIC_VERTICAL = 1;
    static final int VEHICLE_WAITING_HORIZONTAL = 2;
    static final int PEDESTRIAN_WAITING_HORIZONTAL = 3;
    static final int VEHICLE_WAITING_VERTICAL = 4;
    static final int PEDESTRIAN_WAITING_VERTICAL = 5;

    int state = VEHICLE_AND_PEDESTRIAN_TRAFFIC_HORIZONTAL;
    String[] simulationStateNames = {"Vehicle and Pedestrian Traffic Horizontal", "Vehicle and Pedestrian Traffic Vertical", "Vehicle Waiting Horizontal",
            "Pedestrian Waiting Horizontal", "Vehicle Waiting Vertical", "Pedestrian Waiting Vertical"};

    PedestrianPresenceSensor pedestrianSensorHorizontal = new PedestrianPresenceSensor("Pedestrian Present Horizontal");
    PedestrianPresenceSensor pedestrianSensorVertical = new PedestrianPresenceSensor("Pedestrian Present Vertical");
    VehiclePresenceSensor vehiclePresenceSensorLeftLane = new VehiclePresenceSensor("Left Lane Vehicle Present");
    VehiclePresenceSensor vehiclePresenceSensorRightLane = new VehiclePresenceSensor("Right Lane Vehicle Present");
    VehiclePresenceSensor vehiclePresenceSensorTopLane = new VehiclePresenceSensor("Top Lane Vehicle Present");
    VehiclePresenceSensor vehiclePresenceSensorBottomLane = new VehiclePresenceSensor("Bottom Lane Vehicle Present");
    TrafficLightSensorActuator vehicleTrafficLightLeftLane = new TrafficLightSensorActuator("Traffic Light Vehicles Left Lane");
    TrafficLightSensorActuator vehicleTrafficLightRightLane = new TrafficLightSensorActuator("Traffic Light Vehicles Right Lane");
    TrafficLightSensorActuator vehicleTrafficLightTopLane = new TrafficLightSensorActuator("Traffic Light Vehicles Top Lane");
    TrafficLightSensorActuator vehicleTrafficLightBottomLane = new TrafficLightSensorActuator("Traffic Light Vehicles Bottom Lane");
    TrafficLightSensorActuator pedestrianTrafficLightsHorizontal_1 = new TrafficLightSensorActuator("Traffic Lights Pedestrians Horizontal 1");
    TrafficLightSensorActuator pedestrianTrafficLightsHorizontal_2 = new TrafficLightSensorActuator("Traffic Light Pedestrians Horizontal 2");
    TrafficLightSensorActuator pedestrianTrafficLightsVertical_1 = new TrafficLightSensorActuator("Traffic Light Pedestrians Vertical 1");
    TrafficLightSensorActuator pedestrianTrafficLightsVertical_2 = new TrafficLightSensorActuator("Traffic Light Pedestrians Vertical 2");


    private void switchToVehicleAndPedestrianTrafficHorizontal() {
        alarmVerticalCarTraffic();
        blockVerticalCarTraffic();
        blockVerticalPedestrianTraffic();

        alarmHorizontalCarTraffic();
        enableHorizontalCarTraffic();
        enableHorizontalPedestrianTraffic();

    }

    private void switchToVehicleAndPedestrianTrafficVertical() {
        alarmHorizontalCarTraffic();
        blockHorizontalCarTraffic();
        blockHorizontalPedestrianTraffic();

        alarmVerticalCarTraffic();
        enableVerticalCarTraffic();
        enableVerticalPedestrianTraffic();
    }


    @Override
    public void runSimulation(int steps) {
        for (int i = 0; i < steps; i++) {
            System.out.println("STEP: " + i + " (State: " + simulationStateNames[state] + ")");

            switch (state) {
                case VEHICLE_AND_PEDESTRIAN_TRAFFIC_HORIZONTAL -> {
                    // we use the modulo operator to have different simulation results in different steps,
                    // but keep them deterministic (else we could use a random variable)
                    if (i % 3 == 0) {
                        pedestrianSensorVertical.setPedestrianPresent(true);
                        state = PEDESTRIAN_WAITING_VERTICAL;
                    }
                    if (i % 3 != 0) {
                        // a vehicle arrived
                        if (i % 2 == 0) {
                            vehiclePresenceSensorTopLane.setVehiclePresent(true);
                        } else {
                            vehiclePresenceSensorBottomLane.setVehiclePresent(true);
                        }
                        state = VEHICLE_WAITING_VERTICAL;
                    }

                }
                case VEHICLE_AND_PEDESTRIAN_TRAFFIC_VERTICAL -> {
                    if (i % 3 == 0) {
                        pedestrianSensorHorizontal.setPedestrianPresent(true);
                        state = PEDESTRIAN_WAITING_HORIZONTAL;
                    }
                    if (i % 3 != 0) {
                        // a vehicle arrived
                        if (i % 2 == 0) {
                            vehiclePresenceSensorLeftLane.setVehiclePresent(true);
                        } else {
                            vehiclePresenceSensorRightLane.setVehiclePresent(true);
                        }
                        state = VEHICLE_WAITING_HORIZONTAL;
                    }
                }
                case VEHICLE_WAITING_HORIZONTAL, PEDESTRIAN_WAITING_HORIZONTAL -> {
                    switchToVehicleAndPedestrianTrafficHorizontal();
                    pedestrianSensorHorizontal.setPedestrianPresent(false);
                    state = VEHICLE_AND_PEDESTRIAN_TRAFFIC_HORIZONTAL;
                }
                case VEHICLE_WAITING_VERTICAL, PEDESTRIAN_WAITING_VERTICAL -> {
                    switchToVehicleAndPedestrianTrafficVertical();
                    pedestrianSensorVertical.setPedestrianPresent(false);
                    state = VEHICLE_AND_PEDESTRIAN_TRAFFIC_VERTICAL;
                }

            }
        }


    }

    private void alarmVerticalCarTraffic() {
        vehicleTrafficLightTopLane.setLightStatus(ELightStatus.ORANGE);
        vehicleTrafficLightBottomLane.setLightStatus(ELightStatus.ORANGE);
    }

    private void alarmHorizontalCarTraffic() {
        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.ORANGE);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.ORANGE);
    }

    private void blockVerticalCarTraffic() {
        vehicleTrafficLightTopLane.setLightStatus(ELightStatus.RED);
        vehicleTrafficLightBottomLane.setLightStatus(ELightStatus.RED);
    }

    private void blockHorizontalCarTraffic() {
        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.RED);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.RED);
    }

    private void blockVerticalPedestrianTraffic() {
        pedestrianTrafficLightsVertical_1.setLightStatus(ELightStatus.RED);
        pedestrianTrafficLightsVertical_2.setLightStatus(ELightStatus.RED);
    }

    private void blockHorizontalPedestrianTraffic() {
        pedestrianTrafficLightsHorizontal_1.setLightStatus(ELightStatus.RED);
        pedestrianTrafficLightsHorizontal_2.setLightStatus(ELightStatus.RED);
    }

    private void enableVerticalCarTraffic() {
        vehicleTrafficLightTopLane.setLightStatus(ELightStatus.GREEN);
        vehicleTrafficLightBottomLane.setLightStatus(ELightStatus.GREEN);
    }

    private void enableHorizontalCarTraffic() {
        vehicleTrafficLightLeftLane.setLightStatus(ELightStatus.GREEN);
        vehicleTrafficLightRightLane.setLightStatus(ELightStatus.GREEN);
    }

    private void enableVerticalPedestrianTraffic() {
        pedestrianTrafficLightsVertical_1.setLightStatus(ELightStatus.GREEN);
        pedestrianTrafficLightsVertical_2.setLightStatus(ELightStatus.GREEN);
    }

    private void enableHorizontalPedestrianTraffic() {
        pedestrianTrafficLightsHorizontal_1.setLightStatus(ELightStatus.GREEN);
        pedestrianTrafficLightsHorizontal_2.setLightStatus(ELightStatus.GREEN);
    }
}
