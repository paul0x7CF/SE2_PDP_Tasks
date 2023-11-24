package org.example;

public class Main {
    public static void main(String[] args) {
        SingleTrafficLightSimulation simulation = new SingleTrafficLightSimulation();
        int simulationSteps = 10;
        simulation.simulate(simulationSteps);

        System.out.println("Simulation completed.\n\n");
    }
}
