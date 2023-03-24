package com.example.gmail;

import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.PriorityQueue;

public class Cache  {
    static Cache cache=null;
    Adapter adapter=new Adapter();
    ArrayList<User> cacheUsers;

    private Cache(){
        cacheUsers=new ArrayList<>();
    }

    public static Cache getInstance(){
        if(cache==null){
            cache=new Cache();
        }
        return cache;
    }
    public void RemoveExpiredMails(User user){
        try {
            PriorityQueue<Mail> deletedMails = user.getDeletedMails();
            for (Mail mail: deletedMails) {
                String date = mail.getDate();
//            String s="26/12/2022, 22:04:36";
//            String t="20-11-2022 06:07:59";
//            date=date.replaceAll("/","-").replaceAll(",","");
                Date startDate = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(date);
                Date currentDate = new Date();
                float timeMs = currentDate.getTime() - startDate.getTime();
                float days = ((((timeMs / 1000) / 60) / 60) / 24);
                if (days >= 30) {
                    deletedMails.remove(mail);
                }

            }
        }
        catch (ParseException e){
            System.out.println("Date Error");
        }

    }
    public ArrayList<User> loadUsers(){
        if(!cacheUsers.isEmpty()){return cacheUsers;}
        Path path= Paths.get("src/usersInfo.json") ;
        String jsonText=new String();
        JsonArray jsonarray=new JsonArray();
        File usersInfo=new File("src/usersInfo.json");
        if(usersInfo.length()==0){return cacheUsers;}
        try {
            jsonText = new String(Files.readAllBytes(path));
        }
        catch (IOException e){
            System.out.println("Builder Load Error");
        }
        try {
            jsonarray = (JsonArray) Jsoner.deserialize(jsonText);
        }
        catch (JsonException e){
            System.out.println("Builder Load Error");
        }
        for (Object object : jsonarray){
            JsonObject Juser=(JsonObject) object;
            cacheUsers.add(adapter.JsonToUser(Juser));
        }
        for (User user :cacheUsers){
            RemoveExpiredMails(user);
        }

        User user=cacheUsers.get(0);
//        PriorityQueue<Mail> m=user.getAllMails();
//        PriorityQueue<Mail> ms=user.getDeletedMails();
//        System.out.println(ms.toString());
//        Mail mail= m.peek();
//        ArrayList<String> senders=user.getSenders();
//        ArrayList<String> subjects=user.getSubjects();
//
//        System.out.println(mail.getContent());
//        System.out.println(senders);
//        System.out.println(subjects);
        return cacheUsers;

    }
    public void saveUsers(){
        Path path= Paths.get("src/usersInfo.json");
        File usersInfo =new File("src/usersInfo.json");
        JsonArray jsonArray=new JsonArray();
        for (User user : cacheUsers){
            jsonArray.add(adapter.UserToJson(user));
        }
        String jsonText= Jsoner.serialize(jsonArray);

        System.out.println(jsonText);
        try {

            Files.write(path,jsonText.getBytes(), StandardOpenOption.CREATE);
            Files.write(path,jsonText.getBytes(),StandardOpenOption.TRUNCATE_EXISTING);
        }
        catch (IOException e){ System.out.println("IO");
            System.out.println("Error");
        }
    }
//    public ArrayList<User> getCacheUsers(){
//       return cacheUsers;
//    }
}