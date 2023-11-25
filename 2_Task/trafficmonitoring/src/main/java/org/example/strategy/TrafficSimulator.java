package org.example.strategy;


public class TrafficSimulator {
    private ISimulationStrategy simulationStrategy;

    public TrafficSimulator() {
        this.simulationStrategy = new SingleTrafficLight();
    }

    public void setSimulationStrategy(ISimulationStrategy simulationStrategy) {
        this.simulationStrategy = simulationStrategy;
    }

    public void simulate(int steps) {
        simulationStrategy.runSimulation(steps);
    }

}
