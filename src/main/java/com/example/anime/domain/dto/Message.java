package com.example.anime.domain.dto;

public class Message {
    String message;

//    public Message(String message) {
//        this.message = message;
//    }

    public static Message message(String message){
        Message m = new Message();
        m.message = message;
        return m;
//        return new Message(message);
    }
}
