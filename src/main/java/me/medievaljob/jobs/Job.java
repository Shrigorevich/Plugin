package me.medievaljob.jobs;

import me.medievaljob.utils.Config;

public class Job {
    private String name;
    private int level;
    private int progress;
    private boolean active;

    public Job(String name, int level, int progress, boolean active) {
        this.name = name;
        this.level = level;
        this.progress = progress;
        this.active = active;
    }

    public Job(String name) {
        this.name = name;
        this.level = 1;
        this.progress = 0;
        this.active = false;
    }

    public String getName() {
        return name;
    }

    public int getProgress() {
        return progress;
    }

    public int getLevel() {
        return level;
    }

    public boolean getActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setProgress(int progress) {
        this.progress = progress;
    }

    public void updateProgress(int exp, double expBoost) {
        System.out.println("new exp " + exp);
        this.progress += (int) exp * expBoost;
        if (this.progress >= this.level *
                Config.getInt("EXP_CONST") *
                Config.getDouble("EXP_TO_LVL_UP_CF")) {
            this.level += 1;
            setProgress(0);
        }
    }
}
