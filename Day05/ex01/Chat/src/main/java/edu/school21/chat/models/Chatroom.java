package edu.school21.chat.models;

import java.util.LinkedList;
import java.util.List;

public class Chatroom {
    private Long id;
    private String name;
    private User owner;
    private List<Message> messages;

    public Chatroom() {}

    public Chatroom(Long id, String name, User owner, List<Message> messages) {
        this.id = id;
        this.name = name;
        this.owner = owner;
        this.messages = messages;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCreator(User creator) {
        this.owner = creator;
    }

    public void addMessage(Message message) {
        if (this.messages == null) {
            this.messages = new LinkedList<>();
        }
        this.messages.add(message);
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getCreator() {
        return owner;
    }

    public List<Message> getMessages() {
        return messages;
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public String toString() {
        return  "{" + "id=" + id + "," + "name='" + name + '\'' + "," +
                "creator=" + String.format("%s (%d)", owner.getLogin(), owner.getId()) + "," +
                "messages=" + messages + "}";
    }
}