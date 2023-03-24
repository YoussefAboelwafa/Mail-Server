package com.example.gmail;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import com.fasterxml.jackson.annotation.JsonIgnore;


import java.util.ArrayList;

@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Mail
{
    private  String id;

    @Override
    public String toString() {
        return "Mail{" +
                "id='" + id + '\'' +
                ", subject='" + subject + '\'' +
                ", content='" + content + '\'' +
                ", priority=" + priority +
                ", date='" + date + '\'' +
                ", sender='" + sender + '\'' +
                ", reciever='" + reciever + '\'' +
                ", attachements=" + attachments +
                '}';
    }

    private String subject;
    private String content;
    private int priority;
    private  String date;
    private  String sender;
    private String reciever;
    private ArrayList<String> url;
    private ArrayList<String> attachments;
    // Attachments

    private Mail(MailBuilder builder)
    {
        this.id=builder.id;
        this.date=builder.date;
        this.sender=builder.sender;
        this.priority=builder.priority;
        this.content=builder.content;
        this.subject=builder.subject;
        this.attachments = builder.attachements;
        this.reciever = builder.reciever;
        this.url=builder.URLs;
        //this.reciever=builder.reciever;
    }

    public ArrayList<String> getURLs(){return this.url;}
    //All getter, and NO setter to provde immutability
    public String getSubject() {
        return subject;
    }

    public String getContent() {
        return content;
    }

    public String getPriority() {
        return String.valueOf(priority);
    }

    public String getDate() {
        return date;
    }

    public String getSender() {
        return sender;
    }

    public String getReciever() {
        return reciever;
    }

    public String getId() {
        return id;
    }
    public void setAttachments(ArrayList<String> attachements){this.attachments=attachements;}
    public void setURLs(ArrayList<String> URLs){this.url=URLs;}

    public ArrayList<String> getAttachements() {
        return attachments;
    }
    public static class MailBuilder {
        private  String id;
        private String subject;
        private String content;
        private int priority;
        private  String date;
        private  String sender;
        private String reciever;
        private ArrayList<String> attachements;
        private ArrayList<String> URLs;
        // Attachments


        public MailBuilder(String id,String date,String sender,int priority, String reciever){
            this.id=id;
            this.date=date;
            this.sender=sender;
            this.priority=priority;
            this.reciever = reciever;

        }



        public void  buildSubject(String subject){this.subject=subject;}

        public void  buildContent(String content){this.content=content;}
        public void buildAttachments(ArrayList<String> att){this.attachements = att;}
        public void buildURLs(ArrayList<String> URLs){this.URLs=URLs;}
        public Mail build(){
            Mail mail=new Mail(this);
            return mail;
        }


    }


}