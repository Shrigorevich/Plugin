package me.medievaljob.jobs;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

public class Skills {
    private List<Job> skills;
    double expBoost;

    public Skills(List<Job> skills, Double expBoost) {
        this.skills = skills;
        this.expBoost = expBoost;
        System.out.println(skills.size() + "Skills size in constructor");
    }

    public Skills() {
        String[] jobTitles = {"miner", "woodcutter", "farmer", "breeder", "hunter"};
        List<Job> skills = new ArrayList<>();
        skills.add(new Miner(jobTitles[0]));
        skills.add(new Woodcutter(jobTitles[1]));
        skills.add(new Farmer(jobTitles[2]));
        skills.add(new Breeder(jobTitles[3]));
        skills.add(new Hunter(jobTitles[4]));
//        for(String jobTitle : jobTitles){
//            skills.add(new Job(jobTitle));
//        }
        this.skills = skills;
        this.expBoost = 1.0;
    }

    public Job getOne(String name){
        for (Job job : this.skills){
            if (job.getName().equals(name)){
                return  job;
            }
        }
        return null;
    }

    public List<Job> getAll() {
        return skills;
    }

    public List<Job> getActiveSkills() {
        List<Job> activeJobs = new ArrayList<>();
        System.out.println(skills.size() + "Skills size");
        for (Job job : skills){
            if (job.getActive()){
                activeJobs.add(job);
            }
        }
        return activeJobs;
    }

    public Double getExpBoost() {
        return expBoost;
    }

    public void setExpBoost() {

        int activeSkillsNumber = getActiveSkills().size();

        if (activeSkillsNumber == 0) {
            this.expBoost = 1.0;
        } else {
            this.expBoost = round(1.0 / activeSkillsNumber, 2);
        }

    }

    private static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(Double.toString(value));
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
