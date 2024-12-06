package com.duckzcraft.viper_monster.antiautosoup.utils;

public class Violation {

    private int violationLevel = 0;
    private long lastNotified = 0;

    public void raiseViolationLevel() {
        violationLevel++;
    }

    public int getViolationLevel() {
        return violationLevel;
    }

    public void updateNotify() {
        lastNotified = System.currentTimeMillis();
    }

    public boolean shouldNotify() {
        return (System.currentTimeMillis() - lastNotified) >= ConfigUtils.getMessageDelay();
    }
}