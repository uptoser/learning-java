package com.uptoser.java.design_patterns.behavioral_patterns.state.group3;

public class VehicleRestState extends State {
    public void startUp(Vehicle vehicle) {
        System.out.println(vehicle.getName() + "开始运动");
        vehicle.setState(vehicle.getMoveState());
    }

    public void stop(Vehicle vehicle) {
        System.out.println(vehicle.getName() + "已经是静止状态了");
    }
}  
