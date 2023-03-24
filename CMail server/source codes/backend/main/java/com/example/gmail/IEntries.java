package com.example.gmail;

public interface IEntries {

    String signIn(String email,String pass);
    String addNewUser(InfoJson infoJson);
    void signOut(String email);
}
