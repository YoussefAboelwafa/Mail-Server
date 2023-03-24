package com.example.gmail;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
@Component
public class BuilderHelper {
    public User getRequiredUser(ArrayList<User> activeUsers, String email)
    {
        for(User user: activeUsers) {
            System.out.println(user.getMyEmail());
            System.out.println(email);
            if (user.getMyEmail().equals(email))
                return user;
        }
        return null;
    }
}
