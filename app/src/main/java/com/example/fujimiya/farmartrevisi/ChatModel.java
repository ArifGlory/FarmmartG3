package com.example.fujimiya.farmartrevisi;

/**
 * Created by fujimiya on 2/5/17.
 */

public class ChatModel {

    String name;
    String mesage;
    String timestamp;
    String from;
    String to;

    public ChatModel(String name, String mesage,String timestamp, String from, String to){
        this.name = name;
        this.mesage = mesage;
        this.timestamp = timestamp;
        this.from = from;
        this.to = to;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMesage() {
        return mesage;
    }

    public void setMesage(String mesage) {
        this.mesage = mesage;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
