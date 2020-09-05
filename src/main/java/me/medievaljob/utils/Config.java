package me.medievaljob.utils;

import me.medievaljob.MedievalJob;
import org.bukkit.configuration.file.FileConfiguration;

public class Config {

    private static FileConfiguration config = MedievalJob.getPlugin(MedievalJob.class).getConfig();

    public static String getString(String value){
        return config.getString(value);
    }

    public static int getInt(String value){
        return config.getInt(value);
    }

    public static double getDouble(String value){
        return config.getDouble(value);
    }

    public static void print(String key){

    }

    public static void setInt(String key, int value){
        config.set(key, value);
    }
    public static void setDouble(String key, double value){
        config.set(key, value);
    }
    public static void setString(String key, String value){
        config.set(key, value);
    }

    public static void set(String key, String value){
        config.set(key, value);
    }

}
