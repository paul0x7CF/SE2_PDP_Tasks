package org.example;

import org.example.strategy.FourWayIntersection;
import org.example.strategy.TrafficSimulator;

public class Main {
    public static void main(String[] args) {
        TrafficSimulator simulation = new TrafficSimulator();
        int simulationSteps = 10;
        simulation.setSimulationStrategy(new FourWayIntersection());
        simulation.simulate(simulationSteps);
        System.out.println("Simulation completed.\n\n");
    }
}
