package com.example.gmail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.UrlResource;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.HashMap;

@Component
@RestController
@CrossOrigin
public class Controller{


    Builder builder = new Builder();
    IEntries entries=new ProxyEntries();
    IOperator operator=new Adapter();

    @ResponseBody
    @RequestMapping(value = "/signup",method = RequestMethod.POST)
    public String signUp(@RequestBody InfoJson data)
    {
        //System.out.println(data.first + " " + data.second + " " + data.third + " " + data.fourth);
       return entries.addNewUser(data);
    } // If success, Tell abolwafa&kimo to make a screen to force the user to wait a little}

    @ResponseBody
    @RequestMapping(value = "/logout/{email}",method = RequestMethod.POST)
    public void logout(@PathVariable String email)
    {

        entries.signOut(email);

    }

    @ResponseBody
    @RequestMapping(value = "/login/{email}/{pass}", method = RequestMethod.GET)
    public String signIn(@PathVariable String email,@PathVariable String pass)
    {

        return entries.signIn(email,pass);
    }

    @ResponseBody
    @RequestMapping(value = "/show_e/{type}/{email}/{priority}",method = RequestMethod.GET)
    public ArrayList<Mail> showMails(@PathVariable String type,@PathVariable String email, @PathVariable String priority) // just send me two data: received, send, etc & user Email
    {
        return builder.returnTMails(type,email,priority);
    }

    @ResponseBody
    @RequestMapping(value = "/addMail",method = RequestMethod.POST) // Mail = message, email = "assa@gmail.com"
    public String addMail(@RequestBody InfoJson data)
    {
        return operator.addMail(data);
    }
    @ResponseBody
    @CrossOrigin
    @PostMapping(value = "/download/{attach}/{sub}") // Mail = message, email = "assa@gmail.com"
    public void download_attach(@RequestBody String path,@PathVariable ArrayList<String> attach,@PathVariable String sub)
    {
        builder.Download_Attachments(path,sub,attach);
    }


    @CrossOrigin
    @PostMapping("/attachments/{ids}/{email}")
    public void addAttach(@RequestParam("attachment") ArrayList<MultipartFile> attachments,@PathVariable ArrayList<String> ids,@PathVariable String email) throws Exception {
        System.out.println(attachments.size());
        operator.addAttachment(email,ids,attachments);
    }

    /*** NEWWWWW DELLLLETE, */
    /*** NEWWWWW DELLLLETE, */
    /*** NEWWWWW DELLLLETE, */
    /*** NEWWWWW DELLLLETE, */
    /*** NEWWWWW DELLLLETE, */

    // M3lesh 34an ta5do balkom
    @ResponseBody
    @RequestMapping(value = "/delete/{ids}/{email}/{place}/{fromFolder}", method = RequestMethod.POST)
    public String delete(@PathVariable ArrayList<String> ids, @PathVariable String email,
                         @PathVariable String place, @PathVariable String fromFolder)
    {
        return builder.delete(email, place, fromFolder, ids);
    }
    @ResponseBody
    @RequestMapping(value = "/filter/{type}/{email}/{place}",method = RequestMethod.GET) // Filter: (id, sender..), (sender), (enta 48al fen)
    public ArrayList<Mail> filterMails( @PathVariable String type,@PathVariable String email,
                                        @PathVariable String place)
    {
        return builder.filter(type, email, place);
    }

    @ResponseBody
    @RequestMapping(value = "/sort/{type}/{email}/{place}",method = RequestMethod.GET) // Sort: (id, sender..), (sender), (enta 48al fen)
    public ArrayList<Mail> sortMails(@PathVariable String type,@PathVariable String email,
                                     @PathVariable String place)
    {
        return builder.sorter(type, email, place);
    }

    /** WARNING!!!!!! Search ZAY MA TALABTO */
    /** WARNING!!!!!! ZAY MA TALABTO */
    /** WARNING!!!!!! ZAY MA TALABTO */
    /** WARNING!!!!!! ZAY MA TALABTO */
    /** WARNING!!!!!! ZAY MA TALABTO */

    @ResponseBody
    @RequestMapping(value = "/search/{type}/{email}/{place}/{information}",method = RequestMethod.GET) // Sort: (id, sender..), (sender), (enta 48al fen)
    public ArrayList search(@PathVariable String type,@PathVariable String email,
                                     @PathVariable String place, @PathVariable String information)
    {
        return builder.searcher(type, email, place, information);
    }


    @ResponseBody
    @RequestMapping(value = "/deleteFromDeleted/{mail}/{ids}",method = RequestMethod.GET)
    public void deleteTotally(@PathVariable String mail,@PathVariable ArrayList<String> ids)
    {
        operator.totallyRemove(mail, ids);
    }

    //lesa
    @ResponseBody
    @RequestMapping(value = "/addedAnotherMailForMe/{email}/{added}",method = RequestMethod.POST)
    public String addAnewMailAddressToAUserInternally(@PathVariable String email, @PathVariable String added) // just send me two data: received, send, etc & user Email
    {
        return builder.addAnotherEmailForMe(email, added); // First email, second email
    }
    //done
    /** New Stuff */
    @ResponseBody
    @RequestMapping(value = "/editUserName/{email}/{newName}",method = RequestMethod.PUT)
    public void editName(@PathVariable String newName,@PathVariable String email)
    {
        builder.editName(email, newName);
    }

    @ResponseBody
    @RequestMapping(value = "/addContact/{email}/{added}/{name}/{id}",method = RequestMethod.POST)
    public String addContact(@PathVariable String email, @PathVariable String added,
    @PathVariable String name, @PathVariable String id)
    {
        return builder.addContact(email, added, name, id); // First email, second email
    }

    @ResponseBody
    @RequestMapping(value = "/showContacts/{email}/{sorted}",method = RequestMethod.GET)
    public ArrayList<Contact> showContacts(@PathVariable String email, @PathVariable String sorted) // just send me two data: received, send, etc & user Email
    {
        return builder.showContacts(email, sorted); // First email, second email
    }
/* Useless
    @ResponseBody
    @RequestMapping(value = "/searchContacts/{email}/{type}/{information}",method = RequestMethod.GET) // id of contact
    public ArrayList<Contact> searchContacts(@PathVariable String email, @PathVariable String type,
                                  @PathVariable String information) // just send me two data: received, send, etc & user Email
    {
        return builder.searchContacts(email, information, type); // First email, second email
    }
*/
    @ResponseBody
    @RequestMapping(value = "/updateContactName/{email}/{newName}/{id}",method = RequestMethod.POST) // name only
    public String EditContactName(@PathVariable String email,
                               @PathVariable String newName, @PathVariable String id)
    {
        return builder.updateContact(email, newName, id); // First email, second email
    }


    @ResponseBody
    @RequestMapping(value = "/deleteEmailsFromContact/{email}/{id}",method = RequestMethod.DELETE) // May cause errors
    public String deleteEmailsFromContact(@PathVariable String email, @RequestBody ArrayList<String> emails, @PathVariable String id)
    {
        return builder.deleteEmailsFromContact(email, emails, id); // send me the deleted mails as request body, request body zay lab el drawer
    }
    @ResponseBody
    @RequestMapping(value = "/deleteContact/{email}/{id}",method = RequestMethod.POST)
    public String deleteContact(@PathVariable String email, @PathVariable String id)
    {
        return builder.deleteContact(email, id); // WAD7A YA GED3AN
    }

    //elbaqy
    @ResponseBody
    @RequestMapping(value = "/addFolder/{email}/{name}", method = RequestMethod.POST)
    public String addFolder(@PathVariable String email, @PathVariable String name)
    {
         return builder.addFolder(email, name); // Wad7a
    }

    @ResponseBody
    @RequestMapping(value = "/addToFolder", method = RequestMethod.POST)
    public void addToFolder(@RequestBody InfoJson data)
    {
        String names[]=data.eighth.split("\\+");
        //System.out.println(names[0]);
         builder.addToFolder(names[1],names[0], data);  // send me the added mail as request body, request body zay lab el drawer
    }

    @ResponseBody
    @RequestMapping(value = "/deleteFolder/{email}/{name}", method = RequestMethod.POST)
    public void deleteFolder(@PathVariable String email, @PathVariable String name)
    {
        builder.deleteFolder(email, name);  // wad7a
    }

    @ResponseBody
    @RequestMapping(value = "/renameFolder/{email}/{name}/{newName}", method = RequestMethod.PUT)
    public String renameFolder(@PathVariable String email, @PathVariable String name,
                             @PathVariable String newName)
    {
        return builder.renameFolder(email, name, newName);  // wad7a
    }
    @ResponseBody
    @RequestMapping(value = "/showFolder/{email}", method = RequestMethod.GET)
    public HashMap<String,ArrayList<Mail>> showFolder(@PathVariable String email)
    {
        return builder.showFolder(email); // wad7a
    }

//    @ResponseBody
//    @RequestMapping(value = "/showFolder/{email}/{name}", method = RequestMethod.GET)
//    public ArrayList<Mail> showFolder(@PathVariable String email, @PathVariable String name)
//    {
//        return builder.showFolder(email, name); // wad7a
//    }
    
}