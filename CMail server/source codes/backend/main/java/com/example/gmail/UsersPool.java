package com.example.gmail;

import java.util.ArrayList;

public class UsersPool implements ICache {

    ArrayList<User> usersInUse;
    ArrayList<User> usersAvailable;

    public static final int DEFAULT_POOL_SIZE=50;
    private int maxPoolSize=DEFAULT_POOL_SIZE;
    protected static UsersPool usersPool=null;

    protected UsersPool(){
        usersAvailable=new ArrayList<>();
        usersInUse=new ArrayList<>();

    }
    public static UsersPool getInstance(){
        if (usersPool==null){
            usersPool=new UsersPool();
        }
        return usersPool;
    }

    public int getMaxPoolSize() {
        return maxPoolSize;
    }

    public void setMaxPoolSize(int maxPoolSize) {
        this.maxPoolSize = maxPoolSize;
    }



    public User acquireUser(String email){
        User user=null;

        if(!usersAvailable.isEmpty())   {
            user=builderHelper.getRequiredUser(cacheUsers,email);
            usersAvailable.remove(user);
            usersInUse.add(user);
            return user;

        }

        if (usersInUse.size()<maxPoolSize){
            user=builderHelper.getRequiredUser(cacheUsers,email);
            usersInUse.add(user);
            return user;
        }

        return user;
    }
    public void releaseUser(String email){
        usersAvailable.add(builderHelper.getRequiredUser(usersInUse,email));
        usersInUse.remove(builderHelper.getRequiredUser(usersInUse,email));

    }
}
