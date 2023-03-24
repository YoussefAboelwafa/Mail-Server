package com.example.gmail;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class CriteriaSender implements Criteria{

    @Override
    public ArrayList<Mail> meetCriteria(ArrayList<User> cacheUsers, String email, String place){
        ArrayList<Mail> arrayList = new ArrayList<>();
        arrayList.clear();
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        ArrayList<String> senders=active.getSenders();
        System.out.println(senders);
        for (String x : senders)
        {
            ArrayList<Mail> arrayList1 = new ArrayList<>();
            arrayList1.clear();
            if (place.equals("sent"))
                arrayList1 = searchForMessage(active.getSent_Mails(), x);
            else if (place.equals("draft"))
                arrayList1 = searchForMessage(active.getDraft_Mails(), x);
            else if (place.equals("deleted"))
                arrayList1 = searchForMessage(active.getDeleted_Mails(), x);
            else if (place.equals("received")) // Not logical yet , ask kareem how would the other user recieve it
                arrayList1 = searchForMessage(active.getReceived_Mails(), x);
            else // (place.equals("all"))
                arrayList1 = searchForMessage(active.getAll_Mails(), x);
            for(Mail mail: arrayList1)
                arrayList.add(mail);
        }
        return arrayList;
    }
    ArrayList<Mail> searchForMessage(ArrayList<Mail> in, String information)
    {
        ArrayList<Mail> foundOnes = new ArrayList<>();
        foundOnes.clear();
        for(Mail mail: in)
            if(mail.getSender().equals(information))
                foundOnes.add(mail);
        return foundOnes;
    }
}
