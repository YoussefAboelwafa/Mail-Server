package com.example.gmail;

import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class InfoJson {
    public ArrayList<String> first; // id || name
    public String second; // subject || birthDate
    public String third; // content || email
    public String fourth; // priority || password
    public String fifth; // date
    public ArrayList<String> sixth; // ArrayList<Senders>
    public ArrayList<String> seventh; // ArrayList<Receivers>
    public String eighth; // type of email : deleted, folder, sent, received, draft.
    public ArrayList<String> ninth; // ArrayList <Attachments Strings>
    public ArrayList<String> tenth;    public String eleventh;
    public String twelfth;
    public String thrteen;
    public String fourteen;

    @Override
    public String toString() {
        return "InfoJson{" +
                "first='" + first + '\'' +
                ", second='" + second + '\'' +
                ", third='" + third + '\'' +
                ", fourth='" + fourth + '\'' +
                ", fifth='" + fifth + '\'' +
                ", sixth='" + sixth + '\'' +
                ", seventh='" + seventh + '\'' +
                ", eighth='" + eighth + '\'' +
                ", ninth='" + ninth + '\'' +
                '}';
    }
}
