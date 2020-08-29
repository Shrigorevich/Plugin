package me.medievaljob.jobs;

public class Farmer extends Job {
    public Farmer(String name) {
        super(name);
    }

    public Farmer(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }
}
