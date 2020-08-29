package me.medievaljob.state;

import me.medievaljob.jobs.Skills;

public class User {
    private String name;
    private Skills skills;

    public User(String name, Skills skills){
        this.name = name;
        this.skills = skills;

    }

    public User(String name){
        this.name = name;
        this.skills = new Skills();
    }

    public String getName() {
        return name;
    }

    public Skills getSkills() {
        return skills;
    }

//    public void setExpBoost() {
//        Integer ActiveSkillsNumber = skills.getActiveSkills().size();
//
//        if(ActiveSkillsNumber == 0){
//            this.expBoost = 1.0;
//        }else{
//            this.expBoost = 1.0/ActiveSkillsNumber;
//        }
//    }
}
