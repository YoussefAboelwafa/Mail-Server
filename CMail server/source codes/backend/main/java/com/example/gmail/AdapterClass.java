package com.example.gmail;

import org.springframework.stereotype.Component;
import com.example.gmail.User;
@Component
public class AdapterClass
{
    public User wrapToUser(InfoJson data)
    {
        User user = new User();
        user.setName(data.first.get(0));
        user.setFakeName(data.second);
        user.setMyEmail(data.third);
        user.setPassWord(data.fourth);
        return user;
    }
    /*
    public Mail wrapToMail(InfoJson data)
    {
        Mail mail = new Mail();
        mail.setId(data.first);
        mail.setSubject(data.second);
        mail.setContent(data.third);
        mail.setPriority(data.fourth);
        mail.setDate(data.fifth);
        mail.setSender(data.sixth);
        mail.setReciever(data.seventh);
        return mail;
    }

     */
}
