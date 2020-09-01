package me.medievaljob;

import com.mongodb.client.MongoDatabase;
import me.medievaljob.jobs.*;
import me.medievaljob.state.User;
import org.bson.conversions.Bson;
import org.bukkit.ChatColor;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;

import org.bson.Document;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.stream.Collectors;

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
            List<Job> jobs = new ArrayList<>();
            skills.getList("jobList", Document.class).forEach((Consumer<Document>) document1 -> {
                String name = document1.getString("name");
                int level = document1.getInteger("level");
                int progress = document1.getInteger("progress");
                boolean active = document1.getBoolean("active");
                switch (document1.getString("name")) {
                    case "miner":
                        jobs.add(new Miner(name, level, progress, active));
                        break;
                    case "woodcutter":
                        jobs.add(new Woodcutter(name, level, progress, active));
                        break;
                    case "farmer":
                        jobs.add(new Farmer(name, level, progress, active));
                        break;
                    case "breeder":
                        jobs.add(new Breeder(name, level, progress, active));
                        break;
                    case "hunter":
                        jobs.add(new Hunter(name, level, progress, active));
                        break;
                    default:
                        break;
                }
            });
            users.add(new User(document.getString("name"), jobs, skills.getDouble("expBoost")));
        });

        return users;
    }

    public void saveState(List<User> users) {
        System.out.println(ChatColor.AQUA + "SAVING DATA...");

        for (User user : users) {
            updateUser(user);
        }
    }

    public void saveUser(User user) {
        System.out.println(ChatColor.GREEN + "Saving " + user.getName());
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        List<Document> jobDocList = new ArrayList<>();
        for (Job job : user.getAll()) {
            jobDocList.add(new Document("name", job.getName())
                    .append("level", job.getLevel())
                    .append("progress", job.getProgress())
                    .append("active", job.getActive()));
        }
        Document doc = new Document("name", user.getName())
                .append("skills", new Document("jobList", jobDocList)
                        .append("expBoost", user.getExpBoost()));
        col.insertOne(doc);
    }

    public void updateUser(User user) {
        System.out.println(ChatColor.GREEN + "Updating " + user.getName());
        MongoDatabase database = mongoClient.getDatabase("jeroniya");
        MongoCollection<Document> col = database.getCollection("users");

        List<Document> jobDocList = new ArrayList<>();
        for (Job job : user.getAll()) {
            jobDocList.add(new Document("name", job.getName())
                    .append("level", job.getLevel())
                    .append("progress", job.getProgress())
                    .append("active", job.getActive()));
        }
        Document doc = new Document("name", user.getName())
                .append("skills", new Document("jobList", jobDocList)
                        .append("expBoost", user.getExpBoost()));

        Bson newData = new Document("skills",
                new Document("jobList", jobDocList).append("expBoost", user.getExpBoost()));

        col.findOneAndUpdate(new Document("name", user.getName()), new Document("$set", newData));

    }

}