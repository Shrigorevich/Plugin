package me.medievaljob.jobs;

public class Hunter extends Job {
    public Hunter(String name) {
        super(name);
    }

    public Hunter(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }
}
