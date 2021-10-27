package me.allink.playerlogger.client.log;

import me.allink.playerlogger.client.property.SkinProperty;

import java.util.ArrayList;

public class Log {
    ArrayList<SkinProperty> skinProperties = new ArrayList<>();
    ArrayList<String> usernames = new ArrayList<>();

    public Log() {

    }

    public Log(ArrayList<String> usernames, ArrayList<SkinProperty> skinProperties) {
        this.usernames = usernames;
        this.skinProperties = skinProperties;
    }

    public ArrayList<SkinProperty> getSkinProperties() {
        return skinProperties;
    }

    public void setSkinProperties(ArrayList<SkinProperty> skinProperties) {
        this.skinProperties = skinProperties;
    }

    public ArrayList<String> getUsernames() {
        return usernames;
    }

    public void setUsernames(ArrayList<String> usernames) {
        this.usernames = usernames;
    }

    public void addUsername(String username) {
        usernames.add(username);
    }

    public void addSkinProperty(SkinProperty property) {
        skinProperties.add(property);
    }
}
