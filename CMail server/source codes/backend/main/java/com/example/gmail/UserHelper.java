package com.example.gmail;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.PriorityQueue;

@Component
public class UserHelper {
    ArrayList<Mail> searchForMessage(ArrayList<Mail> in, String information, String type)
    {

        ArrayList<Mail> foundOnes = new ArrayList<>();
        foundOnes.clear();
        if(type.equals("id"))
        {
            for(Mail mail: in)
                if(mail.getId().equals(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("subject"))
        {
            for(Mail mail: in)
                if(mail.getSubject().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("content"))
        {
            for(Mail mail: in)
                if(mail.getContent().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("date"))
        {
            for(Mail mail: in)
                if(mail.getDate().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("receiver"))
        {
            for(Mail mail: in)
                if(mail.getReciever().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("sender"))
        {
            for(Mail mail: in)
                if(mail.getSender().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("priority"))
        {
            for(Mail mail: in)
                if(mail.getPriority().contains(information))
                    foundOnes.add(mail);
        }
        else if(type.equals("Attachments"))
        {

        }
        return foundOnes;
    }
}
