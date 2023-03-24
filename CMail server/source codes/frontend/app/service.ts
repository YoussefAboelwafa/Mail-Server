import { Injectable } from "@angular/core"
import { HttpClient } from '@angular/common/http';
import { Observable } from "rxjs";

// import {class you made} 
@Injectable({
  providedIn: 'root'
})


export class service {
  //private apiServerUrl = 'http://localhost:8080';
  constructor(private http: HttpClient) { }
     

    

   public  login(email:any,pass:any):Observable<any>{
     return this.http.get<any>(`http://localhost:8080/login/${email}/${pass}`);
    }
    public  signup(user:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/signup`,user);
     }

     public  search(type:any,email:any,place:any,info:any):Observable<any>{
      return this.http.get<any>(`http://localhost:8080/search/${type}/${email}/${place}/${info}`);
    }
    public  emails(type:any,email:any):Observable<any>{
      return this.http.get<any>(`http://localhost:8080/show_e/${type}/${email}/${0}`);
    }
    public  addmail(info:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/addMail`,info);
    }
   
    public  delete(ids:any,email:any,place:any,folder:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/delete/${ids}/${email}/${place}/${folder}`,null);
    }
    
    public  download_attach(path:any,attach:any,sub:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/download/${attach}/${sub}`,path);
    }


    public  add_attach(list:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/attach`,list);
    }

    public add_attach1(list:any,ids:any,email:any): Observable<any>{
      return this.http.post<any>(`http://localhost:8080/attachments/${ids}/${email}`,list);                       
    }


    public  filter(type:any,email:any,place:any):Observable<any>{
      return this.http.get<any>(`http://localhost:8080/filter/${type}/${email}/${place}`);
    }

    public  sort(type:any,email:any,place:any):Observable<any>{
      return this.http.get<any>(`http://localhost:8080/sort/${type}/${email}/${place}`);
    }

    
    public  totaly_remove(email:any,ids:any):Observable<any>{
      return this.http.get<any>(`http://localhost:8080/deleteFromDeleted/${email}/${ids}`);
    }
  
  

    //please el remove for all ids from one email in totaly remove and delete in add mail ya tammmman

    // contact part
    public  add_contact(email:any,added:any,name:any,id:any):Observable<any>{
      return this.http.post<any>(`http://localhost:8080/addContact/${email}/${added}/${name}/${id}`,null);
    }
    public  show_contact(email:any,sort:any):Observable<any>{  //return all contact of this email 
      return this.http.get<any>(`http://localhost:8080/showContacts/${email}/${sort}`);
    }
    public  edit_contact(email:any,name:any,id:any):Observable<any>{  //return nothing and edit contact name with new name
      return this.http.post<any>(`http://localhost:8080/updateContactName/${email}/${name}/${id}`,null);
    }
   
    public  delete_contact(email:any,id:any):Observable<any>{  // delete this contact from the open email
      return this.http.post<any>(`http://localhost:8080/deleteContact/${email}/${id}`,null);
    }
    


    //---------------------------------------------------
    public  logout(email:any):Observable<any>{ // take email and change it with new email (new pass ,new name)
      return this.http.post<any>(`http://localhost:8080/logout/${email}`,null);
     }

     
     public  add_email_to_me(email:any,added:any):Observable<any>{ // take email and change it with new email (new pass ,new name)
      return this.http.post<any>(`http://localhost:8080/addedAnotherMailForMe/${email}/${added}`,null);
    }
    public  edit_email(email:any,new_email:any):Observable<any>{ // take email and change it with new email (new pass ,new name)
      return this.http.post<any>(`http://localhost:8080/editemail/${email}/${new_email}`,null);
    }
    //folders
    public  make_folder(email:any,name:any):Observable<any>{  // make a folder in this email if name of folder is exit return 0
      return this.http.post<any>(`http://localhost:8080/addFolder/${email}/${name}`,null);
    }
    public  addmail_to_folder(info:any):Observable<any>{  // mail is info of mail with id and all information, email : the email which open ,name :is the name of folder 
      return this.http.post<any>(`http://localhost:8080/addToFolder`,info);
    }
    public  delete_folder(email:any,name_fo:any):Observable<any>{  // folder: name
      return this.http.post<any>(`http://localhost:8080/deleteFolder/${email}/${name_fo}`,null);
    }
    public  edit_folder(email:any,folder_name:any,new_name:any):Observable<any>{  //return all folder and in each folder there are emails 
      return this.http.put<any>(`http://localhost:8080/renameFolder/${email}/${folder_name}/${new_name}`,null);
    }
    public  show_folder(email:any):Observable<any>{  //return all folder and in each folder there are emails 
      return this.http.get<any>(`http://localhost:8080/showFolder/${email}`);
    }

    // if you need extra work call me please (starred)   
    
    
    
    
    
    

}