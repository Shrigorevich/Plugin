package me.medievaljob.jobs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Skills {
    private Miner miner;
    private Woodcutter woodcutter;
    private Farmer farmer;
    private Breeder breeder;
    private Hunter hunter;
    double expBoost;

    public Skills(Miner miner, Woodcutter woodcutter, Farmer farmer, Breeder breeder, Hunter hunter, Double expBoost) {
        this.breeder = breeder;
        this.farmer = farmer;
        this.miner = miner;
        this.hunter = hunter;
        this.woodcutter = woodcutter;
        this.expBoost = expBoost;
    }

    public Skills() {
        this.breeder = new Breeder("breeder");
        this.farmer = new Farmer("farmer");
        this.miner = new Miner("miner");
        this.hunter = new Hunter("hunter");
        this.woodcutter = new Woodcutter("woodcutter");
        this.expBoost = 1.0;
    }

    public Breeder getBreeder() {
        return breeder;
    }

    public Farmer getFarmer() {
        return farmer;
    }

    public Hunter getHunter() {
        return hunter;
    }

    public Miner getMiner() {
        return miner;
    }

    public Woodcutter getWoodcutter() {
        return woodcutter;
    }

    public Double getExpBoost() {
        return expBoost;
    }

    public void setExpBoost() {

        Integer activeSkillsNumber = getActiveSkills().size();

        if (activeSkillsNumber == 0) {
            this.expBoost = 1.0;
        } else {
            this.expBoost = round(1.0 / activeSkillsNumber, 2);
        }

    }

    public List<Job> getActiveSkills() {
        List<Job> activeJobs = new ArrayList<>(5);

        if (miner.getActive()) activeJobs.add(miner);
        if (woodcutter.getActive()) activeJobs.add(woodcutter);
        if (farmer.getActive()) activeJobs.add(farmer);
        if (breeder.getActive()) activeJobs.add(breeder);
        if (hunter.getActive()) activeJobs.add(hunter);

        return activeJobs;
    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
