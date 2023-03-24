package com.example.gmail;

import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;

public interface IOperator {
     String addMail(InfoJson data);
    void totallyRemove(String user, ArrayList<String> ids);
    void addAttachment(String email, ArrayList<String> IDs, ArrayList<MultipartFile> attachments);


}
