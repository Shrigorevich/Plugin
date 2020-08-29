package me.medievaljob.jobs;

public class Woodcutter extends Job {
    public Woodcutter(String name) {
        super(name);
    }

    public Woodcutter(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }
}
