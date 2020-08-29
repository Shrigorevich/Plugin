package me.medievaljob;

import com.mongodb.client.MongoDatabase;
import me.medievaljob.jobs.Job;
import me.medievaljob.jobs.Miner;
import me.medievaljob.jobs.Skills;
import me.medievaljob.state.User;
import org.bukkit.ChatColor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class MongoDB {

    private MongoClient mongoClient;

    public MongoDB(String mongoURI) {
        this.mongoClient = MongoClients.create(mongoURI);
    }

    public List<User> getData() {
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        List<User> users = new ArrayList<>();

        col.find().forEach((Consumer<Document>) document -> {
            Document skills = document.get("skills", Document.class);
            Document miner = skills.get("miner", Document.class);
            Document woodcutter = skills.get("woodcutter", Document.class);
            Document farmer = skills.get("farmer", Document.class);
            Document breeder = skills.get("breeder", Document.class);
            Document hunter = skills.get("hunter", Document.class);
            users.add(new User(document.getString("name"), new Skills(
                    new Miner(
                            miner.getString("name"),
                            miner.getInteger("level"),
                            miner.getInteger("progress"),
                            miner.getBoolean("active")
                    ),
                    new Job(
                            woodcutter.getString("name"),
                            woodcutter.getInteger("level"),
                            woodcutter.getInteger("progress"),
                            woodcutter.getBoolean("active")),
                    new Job(
                            farmer.getString("name"),
                            farmer.getInteger("level"),
                            farmer.getInteger("progress"),
                            farmer.getBoolean("active")),
                    new Job(
                            breeder.getString("name"),
                            breeder.getInteger("level"),
                            breeder.getInteger("progress"),
                            breeder.getBoolean("active")),
                    new Job(
                            hunter.getString("name"),
                            hunter.getInteger("level"),
                            hunter.getInteger("progress"),
                            hunter.getBoolean("active")),
                    skills.getDouble("expBoost")
            )));

        });

        return users;
    }

    public void saveState(List<User> users) {
        System.out.println(ChatColor.AQUA + "SAVING DATA...");
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        for (User user : users) {
            Skills skills = user.getSkills();
            System.out.println(ChatColor.GREEN + "Saving " + user.getName());
            Document doc = new Document("name", user.getName())
                    .append("skills", new Document(
                                    "miner", new Document("name", skills.getMiner().getName())
                                    .append("level", skills.getMiner().getLevel())
                                    .append("progress", skills.getMiner().getProgress())
                                    .append("active", skills.getMiner().getActive())
                            )
                                    .append("woodcutter", new Document(
                                            "name", skills.getWoodcutter().getName())
                                            .append("level", skills.getWoodcutter().getLevel())
                                            .append("progress", skills.getWoodcutter().getProgress())
                                            .append("active", skills.getWoodcutter().getActive()))
                                    .append("farmer", new Document(
                                            "name", skills.getFarmer().getName())
                                            .append("level", skills.getFarmer().getLevel())
                                            .append("progress", skills.getFarmer().getProgress())
                                            .append("active", skills.getFarmer().getActive()))
                                    .append("breeder", new Document(
                                            "name", skills.getBreeder().getName())
                                            .append("level", skills.getBreeder().getLevel())
                                            .append("progress", skills.getBreeder().getProgress())
                                            .append("active", skills.getBreeder().getActive()))
                                    .append("hunter", new Document(
                                            "name", skills.getHunter().getName())
                                            .append("level", skills.getHunter().getLevel())
                                            .append("progress", skills.getHunter().getProgress())
                                            .append("active", skills.getHunter().getActive()))
                                    .append("expBoost", skills.getExpBoost())
                    );

            col.replaceOne(new Document("name", user.getName()), doc);
        }
    }

    public void saveUser(User user) {
        System.out.println(ChatColor.GREEN + "Saving " + user.getName());
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        Skills skills = user.getSkills();

        Document doc = new Document("name", user.getName())
                .append("skills", new Document(
                                "miner", new Document("name", skills.getMiner().getName())
                                .append("level", skills.getMiner().getLevel())
                                .append("progress", skills.getMiner().getProgress())
                                .append("active", skills.getMiner().getActive())
                        )
                                .append("woodcutter", new Document(
                                        "name", skills.getWoodcutter().getName())
                                        .append("level", skills.getWoodcutter().getLevel())
                                        .append("progress", skills.getWoodcutter().getProgress())
                                        .append("active", skills.getWoodcutter().getActive()))
                                .append("farmer", new Document(
                                        "name", skills.getFarmer().getName())
                                        .append("level", skills.getFarmer().getLevel())
                                        .append("progress", skills.getFarmer().getProgress())
                                        .append("active", skills.getFarmer().getActive()))
                                .append("breeder", new Document(
                                        "name", skills.getBreeder().getName())
                                        .append("level", skills.getBreeder().getLevel())
                                        .append("progress", skills.getBreeder().getProgress())
                                        .append("active", skills.getBreeder().getActive()))
                                .append("hunter", new Document(
                                        "name", skills.getHunter().getName())
                                        .append("level", skills.getHunter().getLevel())
                                        .append("progress", skills.getHunter().getProgress())
                                        .append("active", skills.getHunter().getActive()))
                                .append("expBoost", skills.getExpBoost())
                );
        col.insertOne(doc);
    }

    public void updateUser(User user) {
        System.out.println(ChatColor.GREEN + "Saving " + user.getName());
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        Skills skills = user.getSkills();

        Document doc = new Document("name", user.getName())
                .append("skills", new Document(
                                "miner", new Document("name", skills.getMiner().getName())
                                .append("level", skills.getMiner().getLevel())
                                .append("progress", skills.getMiner().getProgress())
                                .append("active", skills.getMiner().getActive())
                        )
                                .append("woodcutter", new Document(
                                        "name", skills.getWoodcutter().getName())
                                        .append("level", skills.getWoodcutter().getLevel())
                                        .append("progress", skills.getWoodcutter().getProgress())
                                        .append("active", skills.getWoodcutter().getActive()))
                                .append("farmer", new Document(
                                        "name", skills.getFarmer().getName())
                                        .append("level", skills.getFarmer().getLevel())
                                        .append("progress", skills.getFarmer().getProgress())
                                        .append("active", skills.getFarmer().getActive()))
                                .append("breeder", new Document(
                                        "name", skills.getBreeder().getName())
                                        .append("level", skills.getBreeder().getLevel())
                                        .append("progress", skills.getBreeder().getProgress())
                                        .append("active", skills.getBreeder().getActive()))
                        .append("hunter", new Document(
                                "name", skills.getHunter().getName())
                                .append("level", skills.getHunter().getLevel())
                                        .append("progress", skills.getHunter().getProgress())
                                        .append("active", skills.getHunter().getActive()))
                                .append("expBoost", skills.getExpBoost())
                );
        col.replaceOne(new Document("name", user.getName()), doc);

    }
}