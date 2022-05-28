package edu.school21.chat.models;

import java.util.List;
import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private List<Chatroom> createdRooms;
    private List<Chatroom> chatrooms;

    public User() {}

    public User(Long id, String login, String password, List<Chatroom> createdRooms, List<Chatroom> chatrooms) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.createdRooms = createdRooms;
        this.chatrooms = chatrooms;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setLogin(String login) {

        this.login = login;
    }

    public void setPassword(String password) {

        this.password = password;
    }

    public void addCreatedRoom(Chatroom room) {

        this.createdRooms.add(room);
    }

    public void addJoinedRoom(Chatroom room) {

        this.chatrooms.add(room);
    }

    public Long getId() {

        return id;
    }

    public String getLogin() {

        return login;
    }

    public String getPassword() {

        return password;
    }

    public List<Chatroom> getCreatedRooms() {

        return createdRooms;
    }

    public List<Chatroom> getJoinedRooms() {

        return chatrooms;
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
        return  "{" + "id=" + id + "," + "login=\"" + login + '\"' + "," +
                "password=\"" + password + '\"' + "," + "createdRooms=" + createRoomList(createdRooms) +
                "," + "rooms=" + createRoomList(chatrooms) + "}";
    }
}
