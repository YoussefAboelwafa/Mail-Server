package com.example.gmail;

import java.util.ArrayList;

public interface ICache {
    Cache cache=Cache.getInstance();
    UsersPool pool=new UsersPool();
    BuilderHelper builderHelper=new BuilderHelper();
    ArrayList<User> cacheUsers=cache.loadUsers();
    ArrayList<User> signedInUsers=new ArrayList<>();
    ArrayList<User> signedOutUsers=new ArrayList<>();


}
