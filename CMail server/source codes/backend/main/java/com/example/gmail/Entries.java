package com.example.gmail;

import java.util.ArrayList;

public class Entries implements IEntries,ICache {

    private Adapter adapter = new Adapter();


    public String addNewUser(InfoJson infoJson)
    {
        User signedUp = adapter.wrapToUser(infoJson);
        cacheUsers.add(signedUp);
        signedUp.addToMyActiveMails(infoJson.third);
        cache.saveUsers();

        return "1";
    }
    public String signIn(String email,String pass)
    {
        return "0";
    }
    public void signOut(String email){
        pool.releaseUser(email);
        signedInUsers.remove(builderHelper.getRequiredUser(signedInUsers,email));
        signedOutUsers.add(builderHelper.getRequiredUser(signedInUsers,email));
    }
}
