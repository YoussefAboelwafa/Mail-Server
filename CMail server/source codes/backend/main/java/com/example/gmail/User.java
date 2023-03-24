package com.example.gmail;

import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class User
{
    // This is what frontend cares about
    String name;
    String fakeName;
    String mail;
    String passWord;
    //This is backend things

    private UserHelper userHelper = new UserHelper();
    private String myEmail;
    private PriorityQueue<Mail> receivedMails = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
    private PriorityQueue<Mail> sentMails = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
    private PriorityQueue<Mail> draftMails = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
    private PriorityQueue<Mail> deletedMails = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));
    private PriorityQueue<Mail> allMails = new PriorityQueue<>(Comparator.comparing(Mail::getPriority));


    private PriorityQueue<Contact> myContacts = new PriorityQueue<>(Comparator.comparing(Contact::getName));


    private ArrayList<Mail> received_Mails = new ArrayList<>();
    private ArrayList<Mail> sent_Mails = new ArrayList<>();
    private ArrayList<Mail> draft_Mails = new ArrayList<>();
    private ArrayList<Mail> deleted_Mails = new ArrayList<>();
    private ArrayList<Mail> all_Mails = new ArrayList<>();

    private ArrayList<String> senders = new ArrayList<>();
    private ArrayList<String> subjects = new ArrayList<>();
    private ArrayList<String> myEmails = new ArrayList<>();
    private ArrayList<Contact> my_Contacts = new ArrayList<>();

    /** Folder */
    ArrayList<ArrayList<Mail>> folders = new ArrayList<>();
    ArrayList<String> folderNames = new ArrayList<>();
    // Add folder : add in the two lists
    // Add to folder : search in the second list with i++, till you find it then add to it
    // Delete folder same as 1
    // Delete from a dolfer : search then search then delete
    // Rename a folder : search then rename
    // 0, 1

    public ArrayList<String> getMyEmails() {
        return myEmails;
    }

    public void setMyEmails(ArrayList<String> myEmails) {
        this.myEmails = myEmails;
    }

    private String id;

    public String getName() {
        return name;
    }

    public ArrayList<Mail> getReceived_Mails() {
        return received_Mails;
    }

    public void setReceived_Mails(ArrayList<Mail> received_Mails) {
        this.received_Mails = received_Mails;
    }

    public ArrayList<Mail> getSent_Mails() {
        return sent_Mails;
    }

    public void setSent_Mails(ArrayList<Mail> sent_Mails) {
        this.sent_Mails = sent_Mails;
    }

    public ArrayList<Mail> getDraft_Mails() {
        return draft_Mails;
    }

    public void setDraft_Mails(ArrayList<Mail> draft_Mails) {
        this.draft_Mails = draft_Mails;
    }

    public ArrayList<Mail> getDeleted_Mails() {
        return deleted_Mails;
    }

    public void setDeleted_Mails(ArrayList<Mail> deleted_Mails) {
        this.deleted_Mails = deleted_Mails;
    }

    public ArrayList<Mail> getAll_Mails() {
        return all_Mails;
    }

    public void setAll_Mails(ArrayList<Mail> all_Mails) {
        this.all_Mails = all_Mails;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFakeName() {
        return fakeName;
    }

    public void setFakeName(String fakeName) {
        this.fakeName = fakeName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public ArrayList<String> getSenders() {
        return senders;
    }

    public void setSenders(ArrayList<String> senders) {
        this.senders = senders;
    }

    public ArrayList<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(ArrayList<String> subjects) {
        this.subjects = subjects;
    }

    public UserHelper getUserHelper() {
        return userHelper;
    }

    public void setUserHelper(UserHelper userHelper) {
        this.userHelper = userHelper;
    }

    public String getMyEmail() {
        return myEmail;
    }

    public void setMyEmail(String myEmail) {
        this.myEmail = myEmail;
    }

    public PriorityQueue<Mail> getReceivedMails() {
        return receivedMails;
    }

    public void setReceivedMails(PriorityQueue<Mail> receivedMails) {
        this.receivedMails = receivedMails;
    }

    public PriorityQueue<Mail> getSentMails() {
        return sentMails;
    }

    public void setSentMails(PriorityQueue<Mail> sentMails) {
        this.sentMails = sentMails;
    }

    public PriorityQueue<Mail> getDraftMails() {
        return draftMails;
    }

    public void setDraftMails(PriorityQueue<Mail> draftMails) {
        this.draftMails = draftMails;
    }

    public PriorityQueue<Mail> getDeletedMails() {
        return deletedMails;
    }

    public void setDeletedMails(PriorityQueue<Mail> deletedMails) {
        this.deletedMails = deletedMails;
    }

    public PriorityQueue<Mail> getAllMails() {
        return allMails;
    }

    public void setAllMails(PriorityQueue<Mail> allMails) {
        this.allMails = allMails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public void setMy_Contacts(ArrayList<Contact> contacts){this.my_Contacts=contacts;}
    /* Methods */

    @Override
    public String toString()
    {
        return "User{" +
                "name='" + name + '\'' +
                ", fakeName='" + fakeName + '\'' +
                ", myEmail='" + myEmail + '\'' +
                ", passWord='" + passWord + '\'' +
                ", receivedMails=" + receivedMails +
                ", sentMails=" + sentMails +
                ", allMails=" + allMails +
                '}';
    }

    void addMail(Mail mail, String type)
    {
            if (type.equals("sent")) {
                sentMails.add(mail);
                sent_Mails.add(mail);
            } else if (type.equals("draft")) {
                draftMails.add(mail);
                draft_Mails.add(mail);
            } else if (type.equals("deleted")) {
              //  System.out.println("sdas");
                deletedMails.add(mail);
                deleted_Mails.add(mail);
                return;
            } else if (type.equals("received")) {// Not logical yet , ask kareem how would the other user recieve it
                receivedMails.add(mail);
                received_Mails.add(mail);
            }
            allMails.add(mail);
            all_Mails.add(mail);
            if (!senders.contains(mail.getSender()))
                senders.add(mail.getSender());
            if (!subjects.contains(mail.getSubject()))
                subjects.add(mail.getSubject());

    }
    // 20/11/2022, date
    ArrayList<Mail> getMail(int id, String type) // Clicks on mail to show content
    {
        if(type.equals("sent"))
            return userHelper.searchForMessage(sent_Mails, String.valueOf(id), type);
        else if(type.equals("draft"))
            return userHelper.searchForMessage(draft_Mails, String.valueOf(id), type);
        else if(type.equals("deleted"))
            return userHelper.searchForMessage(deleted_Mails, String.valueOf(id), type);
        else if(type.equals("received")) // Not logical yet , ask kareem how would the other user receive it
            return userHelper.searchForMessage(received_Mails, String.valueOf(id), type);
        else
            return null;
    }


    /* Complete methods */
    public void deleteFromTheThree(Mail mail)
    {
        for (Mail iterator :
                allMails) {
            if (mail.getId().equals(iterator.getId())) {
                sentMails.remove(iterator);
                draftMails.remove(iterator);
                receivedMails.remove(iterator);
                received_Mails.remove(iterator);
                draft_Mails.remove(iterator);
                sent_Mails.remove(iterator);
                // Hal a3ml allMails.remove(iterator)?
            }
        }
    }

    String deleteNormalMail(String place, ArrayList<String> ids)
    {
        ArrayList<Mail> pointer = new ArrayList<>();
        PriorityQueue<Mail> pointer2 = new PriorityQueue<>();
        if (place.equals("sent")) {
            pointer =  sent_Mails;
            pointer2 = sentMails;
        }else if (place.equals("draft")) {
            pointer =  draft_Mails;
            pointer2 = draftMails;
        }else if (place.equals("received")) {
            pointer =  received_Mails;
            pointer2 = receivedMails;
        }

        danger:
        for(int i = 0; i < pointer.size(); i++)
        {
            if(pointer.get(i) == null)
                continue ;
            Mail mmail = pointer.get(i);
            for(String x: ids)
            {
                if (mmail.getId().equals(x))
                {
                    pointer.remove(mmail);
                    pointer2.remove(mmail);
                    deleted_Mails.add(mmail);
                    deletedMails.add(mmail);
                    all_Mails.remove(mmail);
                    allMails.remove(mmail);
                    i--;
                    break;
                }
            }
        }
        return "1";
    }

    ArrayList<Mail> sorting(String type, String place)
    {
        ArrayList<Mail> arrayList = new ArrayList<>();
        if (place.equals("sent"))
            arrayList = (ArrayList<Mail>) sent_Mails.clone();
        else if (place.equals("draft"))
            arrayList = (ArrayList<Mail>) draft_Mails.clone();
        else if (place.equals("deleted"))
            arrayList = (ArrayList<Mail>) deleted_Mails.clone();
        else if (place.equals("received")) // Not logical yet , ask kareem how would the other user recieve it
            arrayList = (ArrayList<Mail>) received_Mails.clone();
        else // (place.equals("all"))
            arrayList = (ArrayList<Mail>) all_Mails.clone();
        switch (type)
        {
            case "date":
            {
                arrayList.sort(Comparator.comparing(Mail::getDate));
                Collections.reverse(arrayList);
                break;
            }
            case "sender":
            {
                arrayList.sort(Comparator.comparing(Mail::getSender));
                break;
            }
            case "priority":
            {
                arrayList.sort(Comparator.comparing(Mail::getPriority));
                break;
            }
            case "subject":
            {
                arrayList.sort(Comparator.comparing(Mail::getSubject));
                break;
            }
            case "body":
            {
                arrayList.sort(Comparator.comparing(Mail::getContent));
                break;
            }
            case "receiver":
            {
                arrayList.sort(Comparator.comparing(Mail::getReciever));
                break;
            }
            // Attachments
        }
        return arrayList;
    }

    ArrayList searching(String type, String place, String information)
    {
        ArrayList<Mail> arrayList = new ArrayList<>();
        if(place.equals("contact"))
            return getContact(information, type);
        if (place.equals("sent"))
            arrayList = (ArrayList<Mail>) sent_Mails.clone();
        else if (place.equals("draft"))
            arrayList = (ArrayList<Mail>) draft_Mails.clone();
        else if (place.equals("deleted"))
            arrayList = (ArrayList<Mail>) deleted_Mails.clone();
        else if (place.equals("received")) // Not logical yet , ask kareem how would the other user recieve it
            arrayList = (ArrayList<Mail>) received_Mails.clone();
        else
            arrayList = (ArrayList<Mail>) all_Mails.clone();
        return userHelper.searchForMessage(arrayList,information,type);

    }
    ArrayList<Mail> returnTMails(String type, String priority)
    {
        System.out.println(type);
        if(priority.equals("1")) {
            if (type.equals("sent")) {
                return (ArrayList<Mail>) Arrays.asList(getSentMails().toArray(new Mail[0]));
            }
            else if (type.equals("draft"))
                return (ArrayList<Mail>) Arrays.asList(getDraftMails().toArray(new Mail[0]));
            else if (type.equals("deleted"))
                return (ArrayList<Mail>) Arrays.asList(getDeletedMails().toArray(new Mail[0]));
            else if (type.equals("received"))
                return (ArrayList<Mail>) Arrays.asList(getReceivedMails().toArray(new Mail[0]));
            else if (type.equals("all"))
                return new ArrayList<>(Arrays.asList(getAllMails().toArray(new Mail[0])));
        }
        else
        {
            return sorting("date",type);
        }
        return null;
    }

    void removeTotally(ArrayList<String> mails)
    {
        for(int i = 0; i < deletedMails.size(); i++)
        {
            if(deleted_Mails.size()==0)
                break;
            if(deleted_Mails.get(i) == null)
                continue ;
            Mail mmail = deleted_Mails.get(i);
            for(String x: mails)
            {
                if (mmail.getId().equals(x))
                {
                    deleted_Mails.remove(mmail);
                    deletedMails.remove(mmail);
                    i--;
                    break;
                }
            }
        }


    }
    String addContact(Contact contact)
    {
        for(Contact iter: myContacts)
        {
            System.out.println(iter.getId() + " " + contact.getId());
            if(iter.getId().equals(contact.getId()))  // Design decision: repeat anything but id
                return "0";
        }
        myContacts.add(contact);
        my_Contacts.add(contact);
        return "1";
    }
    public ArrayList<Contact> getMyContacts(String sorted)
    {
        System.out.println(myContacts);
        System.out.println(my_Contacts.toString());
        ArrayList<Contact> arrayList = (ArrayList<Contact>) my_Contacts.clone();
        arrayList.sort(Comparator.comparing(Contact::getName));
        if(sorted.equals("0"))
            return my_Contacts;
        else
            return arrayList;

    }
    void addToMyActiveMails(String email)
    {
        myEmails.add(email);
    }
    ArrayList<Contact> getContact(String information, String type)
    {

        ArrayList<Contact> found = new ArrayList<>();


        for(Contact contact: my_Contacts)
        {
            if(type.equals("id")) {
                if (contact.getId().equals(information))
                    found.add(contact);
            }
            else
            {
                if (contact.getName().contains(information)) {
                    found.add(contact);

                }
            }
        }
        return found; // impossible isA
    }
    void updateContact(String name, String id)
    {
        for(Contact co: myContacts)
        {
            if(co.getId().equals(id))
                co.setName(name); // old one is to zebala
        }
    }
    void deleteContact(String id)
    {
        for(Contact co: myContacts)
        {
            if(co.getId().equals(id))
            {
                myContacts.remove(co);
                my_Contacts.remove(co);
                break;// old one is to zebala
            }
        }
    }
    String deleteFromContact(ArrayList<String> toBeDeleted, String id)
    {

        Contact contact = new Contact();
        for(int i = 0; i < my_Contacts.size(); i++)
        {
            Contact co = my_Contacts.get(i);
            if(co == null)
                continue ;
            for(String iter: toBeDeleted) {
                if (co.getId().equals(iter))
                {
                    myContacts.remove(co);
                    my_Contacts.remove(co);
                    i--;
                    break;
                }
            }
        }
        return contact.deleteEmails(toBeDeleted);
    }
    void EditName(String newName)
    {
        this.setName(newName);
    }
    String addFolder(String name)
    {
        ArrayList<Mail> addMe = new ArrayList<>();
        for(String x: folderNames)
        {
            if(x.equals(name))
                return "0";
        }
        folders.add(addMe);
        folderNames.add(name);
        return "1";
    }
    void addToFolder(String name, PriorityQueue<Mail> added)
    {
        ArrayList<Mail> pointer = new ArrayList<>();
        int i = 0;
        for(String iterator: folderNames)
        {
            if(name.equals(iterator))
            {
                pointer = folders.get(i);
                break;
            }
            i++;
        }
        for(Mail mail: added)
            pointer.add(mail);
    }
    void deleteFolder(String name)
    {
        int i = 0;
        for (String iterator : folderNames) {
            if (name.equals(iterator)) {
                break;
            }
            i++;
        }
        folderNames.remove(i);
        folders.remove(i);
    }
    String deleteFromFolder(String name, ArrayList<String> ids)
    {
        ArrayList<Mail> pointer = new ArrayList<>();
        int i = 0;
        for(String iterator: folderNames)
        {
            if(name.equals(iterator))
            {
                pointer = folders.get(i);
                break;
            }
            i++;
        }
        int j = 0;
        for(String iterator : ids)
        {
            pointer.removeIf(mail -> mail.getId().equals(iterator));
        }
        return "1";
    }
    String renameFolder(String name, String newName)
    {
        int i = 0;
        for(String iterator: folderNames)
        {
            if(name.equals(iterator))
            {
                break;
            }
            i++;
        }
        for(String iterator: folderNames)
        {
            if(iterator.equals(newName))
                return "0";
        }
        folderNames.remove(i);
        folderNames.add(i, newName);
        return "1";
    }
    ArrayList<Mail> showFolder(String name)
    {
        int i = 0;
        ArrayList<Mail> arrayList = new ArrayList<>();
        for(String iterator: folderNames)
        {
            if(iterator.equals(name)) {
                arrayList = folders.get(i);
                break;
            }
            i++;
        }
        return arrayList;
    }
    public void setMyContacts(PriorityQueue<Contact> contactsQ) {
        this.myContacts=contactsQ;
    }
}
