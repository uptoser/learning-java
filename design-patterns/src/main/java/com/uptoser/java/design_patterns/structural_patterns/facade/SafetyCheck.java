package com.uptoser.java.design_patterns.structural_patterns.facade;

/**
 * 子系统 (Subsystem)
 * 子系统是若干个类的集合，这些类的实例协同合作为用户提供所需要的功能，子系统中任何类都不包含外观类的实例引用。
 */
public class SafetyCheck {
    String clientPacket;

    public SafetyCheck(String clientPacket) {
        this.clientPacket = clientPacket;
    }

    public boolean isSave() {
        if (clientPacket.contains("汽油"))
            return false;
        else
            return true;
    }
}
