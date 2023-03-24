package com.example.gmail;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.github.cliftonlabs.json_simple.JsonArray;
import com.github.cliftonlabs.json_simple.JsonException;
import com.github.cliftonlabs.json_simple.JsonObject;
import com.github.cliftonlabs.json_simple.Jsoner;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.PriorityQueue;
import com.google.gson.Gson;
import org.springframework.web.multipart.MultipartFile;

@Component
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Adapter implements IOperator

{

      private final Adapted_Operator operator=new Adapted_Operator();

   public String addMail(InfoJson data)
    {
        PriorityQueue<Mail> mails = wrapToMails(data);
        String type = data.eighth;
        return operator.addMail(mails,type);
    }
    public void totallyRemove(String user, ArrayList<String> ids){
       operator.totallyRemove(user, ids);
    }

    public void addAttachment(String email, ArrayList<String> IDs, ArrayList<MultipartFile> attachments){
        ArrayList<String> attachments_names=new ArrayList<>();

        for (MultipartFile attachment: attachments){
            String pathString="src/"+attachment.getOriginalFilename();
            Path path= Paths.get(pathString);
            File file = new File(pathString);
            try {
                Files.copy(attachment.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
                attachments_names.add(attachment.getOriginalFilename());
                operator.addAttachments(attachments_names,email,IDs);

            }
            catch (IOException e){
                System.out.println("ATTACH ERROR");
            }


        }








    }




    public Contact WrapToContact(String name, String email, String id)
    {
        Contact contact = new Contact();
        contact.addEmail(email);
        contact.setId(id);
        contact.setName(name);
        return contact;
    }



    public User wrapToUser(InfoJson data)
    {
        User user = new User();
        user.setName(data.first.get(0));
        user.setFakeName(data.second);
        user.setMyEmail(data.third);
        user.setPassWord(data.fourth);
        return user;
    }
    public PriorityQueue<Mail> wrapToMails(InfoJson data)
    {

        PriorityQueue<Mail> arrayList = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
        int i = 0;
        for(String ids: data.first)
        {
            Mail.MailBuilder mailBuilder = new Mail.MailBuilder(ids, data.fifth,
                    data.sixth.get(i), Integer.parseInt(data.fourth), data.seventh.get(i));
            mailBuilder.buildSubject(data.second);
           // System.out.println(data.seventh.get(i)+ " 464546456");
            mailBuilder.buildContent(data.third);
            mailBuilder.buildAttachments(data.ninth);
            ArrayList<ResponseEntity<UrlResource>> URLs=(ArrayList<ResponseEntity<UrlResource>>)(ArrayList<?>)(data.tenth);
//            mailBuilder.buildURLs(URLs);


            Mail mail = mailBuilder.build();
            System.out.println(mail);
            arrayList.add(mail);
            i++;
        }
        return arrayList;
    }
    public Mail JsonToMail(JsonObject jsonObject){
        Mail.MailBuilder mailBuilder = new Mail.MailBuilder((String)jsonObject.get("ID"),(String)jsonObject.get("Date"),(String)jsonObject.get("Sender"),Integer.valueOf((String)jsonObject.get("Priority")), (String)jsonObject.get("Receiver"));
        mailBuilder.buildContent((String)jsonObject.get("Content"));
        mailBuilder.buildSubject((String)jsonObject.get("Subject"));

        Gson gson =new Gson();
        mailBuilder.buildURLs(gson.fromJson((String)jsonObject.get("URLs"),ArrayList.class ));
        mailBuilder.buildAttachments(gson.fromJson((String)jsonObject.get("attachments"),ArrayList.class ));



//        String attachements=(String) jsonObject.get("attachements");
//
//        try {
//            JsonArray jsonArray=(JsonArray) Jsoner.deserialize(attachements);
//            ArrayList<String> attach=new ArrayList<>();
//            for (Object object: jsonArray){
//                String a=(String) object;
//                attach.add(a);
//            }
//           mailBuilder.buildAttachments(attach);
//
//
//        }
//        catch (JsonException e){
//
//        }


        Mail mail =mailBuilder.build();
//        mail.setSubject((String)jsonObject.get("Subject"));
//        mail.setReciever((String)jsonObject.get("Receiver"));
//        mail.setSender((String)jsonObject.get("Sender"));
//        mail.setContent((String)jsonObject.get("Content"));
//        mail.setPriority((String)jsonObject.get("Priority"));
//        mail.setDate((String)jsonObject.get("Date"));
//        mail.setId((String)jsonObject.get("ID"));
        return mail;
    }
    public JsonObject MailToJson(Mail mail){
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("ID",mail.getId());
        jsonObject.put("Subject",mail.getSubject());
        jsonObject.put("Content",mail.getContent());
        jsonObject.put("Priority",mail.getPriority());
        jsonObject.put("Date",mail.getDate());
        jsonObject.put("Sender",mail.getSender());
        jsonObject.put("Receiver",mail.getReciever());



        Gson gson=new Gson();
        String attachments=gson.toJson(mail.getAttachements());
        jsonObject.put("attachments",attachments);


//        jsonObject.put("attachements",mail.getAttachements());
//        ArrayList<String> attachements = mail.getAttachements();
//        JsonArray Jattachement=new JsonArray();
//        for (String string : attachements){
//            Jattachement.add(string);
//        }
//        String Sattachement=Jsoner.serialize(Jattachement);
//        jsonObject.put("attachements",Sattachement);
        return jsonObject;

    }
    public User JsonToUser(JsonObject userJson){
        User user=new User();
        user.setName((String)userJson.get("Name") );
        user.setId((String)userJson.get("ID"));
        user.setFakeName((String)userJson.get("FakeName") );
        user.setMail((String)userJson.get("Mail") );
        user.setMyEmail((String)userJson.get("MyEmail") );
        user.setPassWord((String)userJson.get("Password") );
        String SreceivedMails=(String)userJson.get("receivedMails");
        String SsentMails=(String)userJson.get("sentMails");
        String SdraftMails=(String)userJson.get("draftMails");
        String SdeletedMails=(String)userJson.get("deletedMails");
        String SallMails=(String)userJson.get("allMails");
        String Ssenders=(String)userJson.get("Senders");
        String Ssubjects=(String) userJson.get("Subjects");
        String Scontacts=(String) userJson.get("contacts");
        Gson gson=new Gson();
        user.folderNames=gson.fromJson((String)userJson.get("foldersNames"),ArrayList.class);
        user.folders=gson.fromJson((String)userJson.get("folders"),ArrayList.class);
        user.setMyEmails(gson.fromJson((String)userJson.get("MyEmails"),ArrayList.class ));
        try {
            JsonArray JreceivedMails=(JsonArray) Jsoner.deserialize(SreceivedMails);
            JsonArray JsentMails=(JsonArray) Jsoner.deserialize(SsentMails);
            JsonArray JdraftMails=(JsonArray) Jsoner.deserialize(SdraftMails);
            JsonArray JdeletedMails=(JsonArray) Jsoner.deserialize(SdeletedMails);
            JsonArray JallMails=(JsonArray) Jsoner.deserialize(SallMails);
            JsonArray Jsenders=(JsonArray) Jsoner.deserialize(Ssenders);
            JsonArray Jsubjects=(JsonArray) Jsoner.deserialize(Ssubjects);
            JsonArray Jcontacts=(JsonArray) Jsoner.deserialize(Scontacts);

            PriorityQueue<Mail> r=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
            PriorityQueue<Mail> s=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
            PriorityQueue<Mail> dr=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
            PriorityQueue<Mail> de=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
            PriorityQueue<Mail> a=new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
            PriorityQueue<Contact> contactsQ=new PriorityQueue<>(Comparator.comparing(Contact::getName));

            for (Object object: Jcontacts){
                JsonObject Jcontact=(JsonObject) object;
                Contact contact=JsontoContact(Jcontact);
//                if(!ExpiredDate(mail)){
                contactsQ.add(contact);
            }

            user.setMyContacts(contactsQ);
            user.setMy_Contacts(new ArrayList<>(contactsQ));


            ArrayList<String> senders=new ArrayList<>();

            for (Object object: Jsenders){
                String sender=(String) object;
                senders.add(sender);
            }
            user.setSenders(senders);

            ArrayList<String> subjects=new ArrayList<>();

            for (Object object: Jsubjects){
                String subject=(String) object;
                subjects.add(subject);
            }
            user.setSubjects(subjects);

            for (Object object: JreceivedMails){
                JsonObject JreceivedMail=(JsonObject) object;
                r.add(JsonToMail(JreceivedMail));

            }
            user.setReceivedMails(r);
            user.setReceived_Mails(new ArrayList<>(r));

            for (Object object: JsentMails){
                JsonObject JMail=(JsonObject) object;


                s.add(JsonToMail(JMail));
            }
            user.setSentMails(s);
            user.setSent_Mails(new ArrayList<>(s));

            for (Object object: JdraftMails){
                JsonObject JMail=(JsonObject) object;

                dr.add(JsonToMail(JMail));
            }
            user.setDraftMails(dr);
            user.setDraft_Mails(new ArrayList<>(dr));

            for (Object object: JdeletedMails){
                JsonObject JMail=(JsonObject) object;
                Mail mail=JsonToMail(JMail);
//                if(!ExpiredDate(mail)){
                de.add(JsonToMail(JMail));
            }

            user.setDeletedMails(de);
            user.setDeleted_Mails(new ArrayList<>(de));

            for (Object object: JallMails){
                JsonObject JMail=(JsonObject) object;
                a.add(JsonToMail(JMail));
            }
            user.setAllMails(a);
            user.setAll_Mails(new ArrayList<>(a));



        }
        catch (JsonException e){
            System.out.println("Load Error");
        }
        return user;
    }
    public JsonObject UserToJson(User user){

        JsonObject userJson=new JsonObject();
        userJson.put("ID",user.getId());
        userJson.put("Name",user.getName());
        userJson.put("Password",user.getPassWord());
        userJson.put("FakeName",user.getFakeName());
        userJson.put("MyEmail",user.getMyEmail());
        userJson.put("Mail",user.getMail());
        ArrayList<String> senders = user.getSenders();
        ArrayList<String> subjects = user.getSubjects();
        ArrayList<Contact> contacts = user.getMyContacts("0");
        PriorityQueue<Mail> receivedMails = user.getReceivedMails();
        PriorityQueue<Mail> sentMails = user.getSentMails();
        PriorityQueue<Mail> draftMails = user.getDraftMails();
        PriorityQueue<Mail> deletedMails = user.getDeletedMails();
        PriorityQueue<Mail> allMails = user.getAllMails();
        Gson gson=new Gson();
        String folders=gson.toJson(user.folders);
        String folders_names=gson.toJson(user.folderNames);
        userJson.put("folders",folders);
        userJson.put("foldersNames",folders_names);
        String MyEmails=gson.toJson(user.getMyEmails());
        userJson.put("MyEmails",MyEmails);
        JsonArray Jcontacts=new JsonArray();
        for (Contact contact : contacts){
            Jcontacts.add(contactToJson(contact));
        }
        String Scontacts= Jsoner.serialize(Jcontacts);
        userJson.put("contacts",Scontacts);
        JsonArray Jsenders=new JsonArray();
        for (String string : senders){
            Jsenders.add(string);
        }
        String Ssenders=Jsoner.serialize(Jsenders);
        userJson.put("Senders",Ssenders);

        JsonArray Jsubjects=new JsonArray();
        for (String string : subjects){
            Jsubjects.add(string);
        }
        String Ssubjects=Jsoner.serialize(Jsubjects);
        userJson.put("Subjects",Ssubjects);

        JsonArray JreceivedMails=new JsonArray();
        for (Mail receivedMail : receivedMails){
            JreceivedMails.add(MailToJson(receivedMail));
        }
        String SreceivedMails= Jsoner.serialize(JreceivedMails);
        userJson.put("receivedMails",SreceivedMails);

        JsonArray JsentMails=new JsonArray();
        for (Mail sentMail : sentMails){
            JsentMails.add(MailToJson(sentMail));
        }
        String SsentMails= Jsoner.serialize(JsentMails);
        userJson.put("sentMails",SsentMails);



        JsonArray JdraftMails=new JsonArray();
        for (Mail draftMail : draftMails){
            JdraftMails.add(MailToJson(draftMail));
        }
        String SdraftMails= Jsoner.serialize(JdraftMails);
        userJson.put("draftMails",SdraftMails);




        JsonArray JdeletedMails=new JsonArray();
        for (Mail deletedMail : deletedMails){
            JdeletedMails.add(MailToJson(deletedMail));
        }
        String SdeletedMails= Jsoner.serialize(JdeletedMails);
        userJson.put("deletedMails",SdeletedMails);



        JsonArray JallMails=new JsonArray();
        for (Mail allMail : allMails){
            JallMails.add(MailToJson(allMail));
        }
        String SallMails= Jsoner.serialize(JallMails);
        userJson.put("allMails",SallMails);

        return userJson;



    }

    public JsonObject contactToJson(Contact contact){
        JsonObject jsonObject=new JsonObject();
        jsonObject.put("name",contact.getName());
        jsonObject.put("ID",contact.getId());
        ArrayList<String> contactMails = contact.getContactMails();
        JsonArray JcontactMails=new JsonArray();
        for (String string : contactMails){
            JcontactMails.add(string);
        }
        String ScontactMails=Jsoner.serialize(JcontactMails);
        jsonObject.put("contactMails",ScontactMails);
        return jsonObject;


    }
    public Contact JsontoContact(JsonObject jsonObject){
        Contact contact=new Contact();
        contact.setName((String) jsonObject.get("name"));
        contact.setId((String) jsonObject.get("ID"));
        String contactMails=(String) jsonObject.get("contactMails");

        try {
            JsonArray jsonArray=(JsonArray) Jsoner.deserialize(contactMails);
            ArrayList<String> mails=new ArrayList<>();
            for (Object object: jsonArray){
                String a=(String) object;
                mails.add(a);
            }
            contact.setContactEMails(mails);


        }
        catch (JsonException e){

        }

        return contact;
    }


}
