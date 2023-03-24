package com.example.gmail;

import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.PriorityQueue;


public class Adapted_Operator implements ICache {


    void addAttachments( ArrayList<String> attachments_names, String email, ArrayList<String> IDs){
        User user=builderHelper.getRequiredUser(cacheUsers,email);
        UserHelper userHelper=new UserHelper();
        for (String id:IDs){
            ArrayList<Mail> mails=userHelper.searchForMessage(user.getSent_Mails(),id,"id");
            for (Mail mail:mails){
                mail.setAttachments(attachments_names);
//                mail.setURLs(URLs);

            }

        }

        cache.saveUsers();

    }
    public String addMail(PriorityQueue<Mail> mails, String type)
    {
        for(Mail mail: mails)
        {
            //System.out.println(mail);
            User sender = builderHelper.getRequiredUser(cacheUsers, mail.getSender());
            User receiver = builderHelper.getRequiredUser(cacheUsers, mail.getReciever());
            if (sender == null || receiver == null)
                return "0";
            sender.addMail(mail, type);
            if (type.equals("sent"))
            {
                receiver.addMail(mail, "received");
            }
        }
        cache.saveUsers();
        return "1";
    }
    void totallyRemove(String user, ArrayList<String> ids)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, user);
        ArrayList<Mail> arrayList = new ArrayList<>();
        for(String id: ids)
        {
            active.removeTotally(ids);
        }
       cache.saveUsers();
    }

}
