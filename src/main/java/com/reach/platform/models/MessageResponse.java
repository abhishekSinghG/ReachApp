package com.reach.platform.models;

import java.util.List;

public class MessageResponse {
    private List<String> messages;

    public List<String> getMessages(){
        return messages;
    }

    public void setMessages(List<String> messages){
        this.messages = messages;
    }
}
