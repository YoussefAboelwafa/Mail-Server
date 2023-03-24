import { contact } from './../contact';
import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { Info } from '../info';
import { service } from '../service';
import { User } from '../user';
import { Router } from '@angular/router';
import { email } from '../email';
import { DomSanitizer } from '@angular/platform-browser';
@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css'],
})
export class HomeComponent implements OnInit {
  //folders: any = ['Work', 'Study', 'Social'];
  email: any;
  mails: any = [];
  email_to_folder:any
  rename_fo:any;
  mails_counter: any = this.mails.length;
  number_inbox=0;
  contacts: any = [];
  show_inbox: any = false;
  show_fo: any = false;
  show_draft: any = false;
  show_sent: any = false;
  show_trash: any = false;
  show_contacts: any = false;
  folder:any;
  hash_folder:any;
  draft_co: email = new email();
  contact_mail: any;
  inbox = true;
  starred = false;
  id: any = 1;
  priority: any = 5;
  popup_email: any;
  info: Info = new Info();
  user: User = new User();
  del_all: any = [];
  popup_id_contact: any;
  send_to_contact: any;
  attach_for_down:any;

  clear_show() {
    this.show_inbox = false;
    this.show_fo = false;
    this.show_draft = false;
    this.show_sent = false;
    this.show_trash = false;
    this.show_contacts = false;
  }

  constructor(
    private route: ActivatedRoute,
    private ser: service,
    private router: Router,
    public san: DomSanitizer,
  ) {
    this.route.paramMap.subscribe((x) => (
      this.email = x.get('name')
    ));
    this.show_mail('received', 'inbox');
    this.router.navigate([`/home/${this.email}/show/inbox`]);
    this.ser.show_folder(this.email).subscribe(
      x=>{
        this.hash_folder=x;
        console.log(this.hash_folder);
        this.folder=Object.keys(this.hash_folder);
        console.log(this.folder[0])
        
      }
    )
    
  }

  ngOnInit(): void {}

  onchange(value: any) {
    for (let i = 0; i < this.del_all.length; i++) {
      if (value == this.del_all[i]) {
        this.del_all.splice(i, 1);
        console.log(this.del_all);
        return;
      }
    }
    this.del_all.push(value);
    console.log(this.del_all);
  }

  get_search(value: any) {
    console.log(value);
  }

  get_priority(value: any) {
    this.priority = value;
  }
  set_email(value: any) {
    this.contact_mail = value;
  }
  get_email_to_fo(value:any){
    this.email_to_folder=value;

  }
  get_rename(value:any){
    this.rename_fo=value;
  }

 
  

  d_all(){
    
    let place:any;
    if(this.show_inbox==true){place="received"}
    else  if(this.show_draft==true){place="draft"}
    else  if(this.show_sent==true){place="sent"}
    else  if(this.show_trash==true){

      this.ser.totaly_remove(this.email,this.del_all).subscribe(
        x=>{}
      )

    return
    }
       else if(this.show_contacts=true){place=true}
    
    this.ser.delete(this.del_all,this.email,place,"1").subscribe(
        x=>{
          

        }
    )
this.del_all=[];

  }

addmail(to:any,sub:any,content:any,type:any,attach:any){


const toarray = to.split(",")

console.log(toarray)
for (let i = 0; i <toarray.length; i++) {
this.info.first[i]=this.id;
this.id++;
}
     let attachfile=new FormData();
   let myDate: Date = new Date();
   let x=myDate.toLocaleString("en-GB")
    this.info.second=sub;
    this.info.third=content;
    this.info.fourth=this.priority;
    this.info.fifth=x;
    this.info.sixth[0]=this.email;
    this.info.seventh=toarray;
    this.info.eighth=type;
    this.id++;
    //console.log(type)


      // for(let i=0;i<attach.length;i++){
      //   attachfile.append("attachment",attach[i]);
      // }
    
      // this.info.ninth=attachfile;

      for(let i=0;i<attach.length;i++){
         attachfile.append("attachment",attach[i]);
       }
   

    this.ser.addmail(this.info).subscribe(


      (valid)=>{

              if(attach.length!=0){ 
                console.log("1");
            this.ser.add_attach1(attachfile,this.info.first,this.email).subscribe({
    
            })
          }
      
        

      }


    );

    this.send_to_contact="";

  }

 


  popup_draft(value: any) {
    this.draft_co = value;
  }
  popup_edit(value1: any, value2: any) {
    this.popup_email = value1;
    this.popup_id_contact = value2;
  }

  make_contact(value: any) {
    this.send_to_contact = value;
  }


  refresh(){
    if(this.show_inbox==true){this.show_mail('received', 'inbox');}
    else  if(this.show_draft==true){this.show_mail('draft', 'draft');}
    else  if(this.show_sent==true){this.show_mail('sent', 'sent');}
   else  if(this.show_trash==true){this.show_mail('trash', 'trash');}
    else if(this.show_contacts==true){this.show_con("0");}
    else if(this.show_fo==true){this.show_folder();}
    
    


    
    this.del_all=[];

  }
x2:any;
  show_mail(type: any, show: any) {
    this.clear_show();
    if (show == 'inbox') {
      this.show_inbox = true;
    } else if (show == 'draft') {
      this.show_draft = true;
    } else if (show == 'sent') {
      this.show_sent = true;
    } else if (show == 'trash') {
      this.show_trash = true;
    }

    this.ser.emails(type, this.email).subscribe(
      (x) => {
       console.log(x);
      this.mails = x;
      if(this.show_inbox==true){
      this.number_inbox=this.mails.length;
      }
    });
    this.del_all=[];

  }

  del_mail( value:any,folder:any,namefolder:any){
    let ids:any=[];
    let place:any;
    if(folder=='1'){
    const ind=this.mails.indexOf(value);
    this.mails.splice(ind,1);
    }
    ids[0]=value.id;
    if(this.show_inbox==true){place="received"}
    else  if(this.show_draft==true){place="draft"}
    else  if(this.show_sent==true){place="sent"}
    else  if(this.show_fo==true){place=namefolder}
    this.ser.delete(ids,this.email,place,folder).subscribe(

      
      (valid)=>{

        this.refresh();
      }


    );
  }

  totaly_remove(value:any){
    let ids:any=[];
    const ind=this.mails.indexOf(value);
    this.mails.splice(ind,1);
    ids[0]=value.id;
    this.ser.totaly_remove(this.email,ids).subscribe(


      (valid)=>{

      }


    );


  }

  getselect_filter(value: any) {
    if (value == 'select') {
    //  console.log(value);
      return;
    }
    let place: any;

    if (this.show_inbox == true) {
      place = 'received';
    }
    // else if(this.show_contacts=true){place=true}
    // else  if(this.show_starred=true){place=true}
    else if (this.show_draft == true) {
      place = 'draft';
    } else if (this.show_sent == true) {
      place = 'sent';
    } else if (this.show_trash == true) {
      place = 'deleted';
    }

    this.ser.filter(value, this.email, place).subscribe((x) => {
      this.mails = x;
    });
  }

  getselect_search(value:any,info:any){
    if(value=="select"){
      console.log(value);
      return;
    }
    if(info.length==0){
      console.log(info)
      return
    }
    let place:any;

    if(this.show_inbox==true){place="received"}
     else if(this.show_contacts==true){
      place="contact"
      value="name";
      this.ser.search(value,this.email,place,info).subscribe(

        x=>{
          this.contacts=x
        }
      )
      return
        }
    // else  if(this.show_starred=true){place=true}
    else  if(this.show_draft==true){place="draft"}
    else  if(this.show_sent==true){place="sent"}
    else  if(this.show_trash==true){place="deleted"}

        //console.log(info)
      this.ser.search(value,this.email,place,info).subscribe(

        x=>{
          this.mails=x
        }
      )
  }

  getselect_sort(value: any) {
    
    if (value == 'select') {
      console.log(value);
      return;
    }

    let place: any;

    if (this.show_inbox == true) {
      place = 'received';
    }
    // else if(this.show_contacts=true){place=true}
    // else  if(this.show_starred=true){place=true}
    else if (this.show_draft == true) {
      place = 'draft';
    } else if (this.show_sent == true) {
      place = 'sent';
    } else if (this.show_trash == true) {
      place = 'deleted';
    }

    this.ser.sort(value, this.email, place).subscribe((x) => {
      console.log(x);
      this.mails = x;
    });
  }

  draft_to(to: any, sub: any, content: any, type: any,attach:any) {
    let ids:any=[];
    ids[0]=this.draft_co.id;

    this.ser.delete(ids,this.email,'draft','1').subscribe(

      
      (valid)=>{
    this.ser.totaly_remove(this.email,ids).subscribe(
      (valid) => {
        this.refresh();
        this.addmail(to,sub,content,type,attach);

        

    }
    
    );
  }
    )
    
  }

  add_contact(name: any) {
    this.ser
      .add_contact(this.email, this.contact_mail, name, this.id)
      .subscribe((x) => {});
    this.id++;
  }
    sort_contact(value:any){
    if (value == 'select') {
      console.log(value);
      return;
    }   
    
    this.show_con("1");

  }

  show_con(sort:string) {
    this.clear_show();
    this.show_contacts = true;

    this.ser.show_contact(this.email,sort).subscribe((x) => {
      console.log(x);
      this.contacts = x;
    });
  }

  edit_con(newname: any) {
    this.ser
      .edit_contact(this.email, newname, this.popup_id_contact)
      .subscribe((x) => { this.refresh});
  }

  delete_con(value:any){
    let id=value.id;
    const ind=this.contacts.indexOf(value);
    this.contacts.splice(ind,1);
    
    this.ser
    .delete_contact(this.email,id)
    .subscribe((x) => {        this.refresh();
    });
    

  }
  logout(){

    this.ser.logout(this.email).subscribe(
      x=>{

      }
    )

  }

  create_folder(name:any){
    if(name==' '||name==''){
      return;
    }
    this.ser.make_folder(this.email,name).subscribe(
      x=>{
        this.refresh();

      }
    )

  }

  show_folder(){
    this.clear_show();
    this.show_fo=true;
    this.ser.show_folder(this.email).subscribe(
      x=>{
        this.hash_folder=x;
        console.log(this.hash_folder);
        this.folder=Object.keys(this.hash_folder);

      }
    )

  }

  add_to_folder(name:any){


    this.info.first[0]=this.email_to_folder.id;
    this.info.second=this.email_to_folder.subject;
    this.info.third=this.email_to_folder.content;
    this.info.fourth=this.email_to_folder.priority;
    this.info.fifth=this.email_to_folder.date;
    this.info.sixth[0]=this.email_to_folder.sender;
    this.info.seventh[0]=this.email_to_folder.reciever;
    this.info.eighth=name +"+"+this.email ;
    ;
    this.ser.addmail_to_folder(this.info).subscribe(
      x=>{
        this.refresh();

      }
    )

  }

  delete_folder(name:any){
    this.ser.delete_folder(this.email,name).subscribe(
      x=>{
        this.refresh();

      }
    )

  }

  add_acoount(value:any){
     if(!this.email.includes("@CSED.com")){
      alert(" email must contain @CSED.com")
      return
    }
      this.ser.add_email_to_me(this.email,value).subscribe(
        x=>{
          this.refresh();

        }
      )

  }


  rename_folder(newname:any){

    this.ser.edit_folder(this.email,this.rename_fo,newname).subscribe(
      x=>{
        this.refresh();

      }
    )

  }


get_attach_todwon(value:any){
  this.attach_for_down=value;
  console.log(value);
}
  download_attach(value:any){
    let sub=this.attach_for_down.subject;

    if(sub.length==0){
      sub="subject"
    }

   console.log(value+"+"+this.attach_for_down.attachements+"+"+sub)
      this.ser.download_attach(value,this.attach_for_down.attachements,sub).subscribe(x=>{})



  }



  





}
