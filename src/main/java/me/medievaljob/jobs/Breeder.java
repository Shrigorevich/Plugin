package me.medievaljob.jobs;

public class Breeder extends Job {
    public Breeder(String name) {
        super(name);
    }

    public Breeder(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }
}
