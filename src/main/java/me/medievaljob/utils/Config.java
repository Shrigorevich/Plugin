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

    public static boolean getBoolean(String value){
        return config.getBoolean(value);
    }
}
