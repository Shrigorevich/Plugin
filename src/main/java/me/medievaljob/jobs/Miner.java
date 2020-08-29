package me.medievaljob.jobs;

public class Miner extends Job {
    public Miner(String name) {
        super(name);
    }

    public Miner(String name, int level, int progress, boolean active){
        super(name, level, progress, active);
    }
}
