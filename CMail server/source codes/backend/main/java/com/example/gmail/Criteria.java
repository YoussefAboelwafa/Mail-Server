package com.example.gmail;

import java.util.ArrayList;
import java.util.PriorityQueue;

public interface Criteria {
   BuilderHelper builderHelper=new BuilderHelper();
   public ArrayList<Mail> meetCriteria(ArrayList<User> cacheUsers,String email, String place);
}
