package com.example.gmail;



import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.security.PublicKey;
import java.util.*;

import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Component
public class Builder implements ICache
{



    private Adapter adapter = new Adapter();


    public void test(){
        Mail.MailBuilder mailBuilder=new Mail.MailBuilder("1111","20-12-2022 06:07:59","nagui@yahoo.com",1, "0");
        mailBuilder.buildSubject("test");
        mailBuilder.buildContent("hello");
        Mail mail=mailBuilder.build();
        User user=new User();
        user.setName("nagui");
        user.setMyEmail("nagui@yahoo.com");
        user.setPassWord("123456789");
        user.setId("7777");
        user.setFakeName("nannash");
        user.setMail("nagui@gmail.com");
        PriorityQueue<Mail> p=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
        p.add(mail);
//        user.setReceivedMails(p);
        user.setDeletedMails(p);
//        user.setDraftMails(p);
//        user.setSentMails(p);
        user.setAllMails(p);
        ArrayList<String> senders=new ArrayList<>();
        senders.add("jo");
        senders.add("ko");
        ArrayList<String> subjects=new ArrayList<>();
        subjects.add("messi");
        subjects.add("cr7");
        user.setSenders(senders);
        user.setSubjects(subjects);
//        System.out.println(cacheUsers.get(0));
        cacheUsers.add(user);
        cache.saveUsers();


    }

    String delete(String email, String place, String fromFolder, ArrayList<String> ids)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        if(fromFolder.equals("0")) {
            String x=active.deleteFromFolder(place, ids);
            cache.saveUsers();
            return x;
        }
        else {
           // System.out.println("kkkssksk");
            String x=active.deleteNormalMail(place, ids);
            cache.saveUsers();
            return x;
        }
    }


    ArrayList<Mail> returnTMails(String type, String email, String priority)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        return active.returnTMails(type, priority);
    }
    ArrayList<Mail> filter(String type, String email, String place)
    {
        CriteriaFactory factory=new CriteriaFactory();
        Criteria criteria=factory.createCriteria(type);
        return criteria.meetCriteria(cacheUsers,email,place);
    }
    ArrayList<Mail> sorter(String type, String email, String place)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        return active.sorting(type,place);
    }
    ArrayList searcher(String type, String email, String place, String information)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        return active.searching(type,place,information);
    }

    void editName(String email, String newName)
    {
        User active = builderHelper.getRequiredUser(cacheUsers, email);
        active.EditName(newName);
        cache.saveUsers();
        System.out.println(active.name);
    }
    String addAnotherEmailForMe(String eMail, String added)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, eMail);
        User second = builderHelper.getRequiredUser(cacheUsers, added);
        if(first == null || second == null)
            return "0";
        first.addToMyActiveMails(added);
        cache.saveUsers();
        return "1";
    }

    String addContact(String firstEMail, String secondEMail, String name, String id)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, firstEMail);
        User second = builderHelper.getRequiredUser(cacheUsers, secondEMail);
        if(first == null || second == null)
            return "0";
        Contact contact = adapter.WrapToContact(name,secondEMail,id);
        String x=first.addContact(contact);
        cache.saveUsers();
        return x;// A7la graph
    }

    ArrayList<Contact> showContacts(String eMail, String sorted)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, eMail);
        return first.getMyContacts(sorted);
    }

    ArrayList<Contact> searchContacts(String eMail, String information, String type)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, eMail);
        return first.getContact(information, type);
    }

    String updateContact(String firstEMail, String name, String id)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, firstEMail);
        if(first == null)
            return "0";
        //Ro7 dawarly 3la el captain deh w 5ally sa7bna y4awer 3leh
        first.updateContact(name, id);
        cache.saveUsers();
        return "1";
    }

    String deleteEmailsFromContact(String email, ArrayList<String> toBeDeleted, String id)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        String x=first.deleteFromContact(toBeDeleted, id);
                cache.saveUsers();
        return x ;
    }
    String deleteContact(String firstEMail, String id)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, firstEMail);
        if(first == null)
            return "0";
        //Ro7 dawarly 3la el captain deh w 5ally sa7bna y4awer 3leh
        first.deleteContact(id);
        cache.saveUsers();
        return "1";
    }
    String addFolder(String email, String name)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        String x=first.addFolder(name);
        cache.saveUsers();
        return x; // check for name, no repetition
    }
    void addToFolder(String email, String name,
                     InfoJson data)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        PriorityQueue<Mail> added = adapter.wrapToMails(data);
        first.addToFolder(name, added);
        cache.saveUsers();
    }
    void deleteFolder(String email, String name)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        first.deleteFolder(name); // check for name
        cache.saveUsers();
    }
    String renameFolder(String email, String name, String newName) // change sender to array
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        String x=first.renameFolder(name, newName);
        cache.saveUsers();
        return x;
    }
    HashMap<String,ArrayList<Mail>> showFolder(String email)
    {
        User first = builderHelper.getRequiredUser(cacheUsers, email);
        HashMap<String,ArrayList<Mail>> folders=new HashMap<>();
        for(int i=0;i<first.folderNames.size();i++){
            folders.put(first.folderNames.get(i),first.folders.get(i));
        }

        return folders;
    }

    public void Download_Attachments(String path,String folderName,ArrayList<String> Files_Names) {
        String[] splitter = path.split("/");
        String subPath = "/";
        for (int i = 0; i < splitter.length - 1; i++) {
            subPath = subPath + splitter[i] + "/";
        }
        String FolderPath = path + "/" + folderName;
        File folder = new File(FolderPath);
        boolean CREATED = folder.mkdir();
        if (!CREATED) {
            FolderPath = subPath + folderName;
            folder = new File(FolderPath);
            folder.mkdir();
        }
        FolderPath = FolderPath + "/";
        for (String fileName : Files_Names) {
            String target_File_Path = FolderPath + fileName;
            String source_File_Path = "src/" + fileName;
            File target_File = new File(target_File_Path);
            File source_File = new File(source_File_Path);
            System.out.println(target_File_Path);
            System.out.println(source_File_Path);
            try {
                Files.copy(source_File.toPath(), target_File.toPath(), REPLACE_EXISTING);
            } catch (IOException e) {
                System.out.println("yarab");
            }


        }
    }
}
