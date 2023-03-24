package com.example.gmail;

import java.util.ArrayList;

public class ProxyEntries implements IEntries,ICache {
    private final IEntries entries=new Entries();
    public String addNewUser(InfoJson data){
        for(User user: cacheUsers)
        {
            if(user.getMyEmail().equals(data.third))
                return "0";
        }
        return entries.addNewUser(data);

//        saveUsers();

    }



    public String signIn(String email,String pass){
        for (User user: cacheUsers)
        {
            if(user.getPassWord().equals(pass) && user.getMyEmail().equals(email)){
                User acquiredUser= pool.acquireUser(email);
                if (acquiredUser!=null&& signedInUsers.size()< pool.getMaxPoolSize()){
                    signedInUsers.add(acquiredUser);
                    return "1";}
                else {System.out.println("System is FULL");return "2";}
            }
        }
        return entries.signIn(email,pass);
    }

    public void signOut(String email){
        entries.signOut(email);
    }
}

