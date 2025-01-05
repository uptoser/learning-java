package com.uptoser.java.design_patterns.behavioral_patterns.state.group3;

public class Vehicle {
    static State moveState, restState;
    static State state;
    String name;

    Vehicle(String name) {
        this.name = name;
        moveState = new VehicleMoveState();
        restState = new VehicleRestState();
        state = restState;
    }

    public void startUp() {
        state.startUp(this);
    }

    public void stop() {
        state.stop(this);
    }

    public void setState(State state) {
        this.state = state;
    }

    public State getMoveState() {
        return moveState;
    }

    public State getRestState() {
        return restState;
    }

    public String getName() {
        return name;
    }
}
