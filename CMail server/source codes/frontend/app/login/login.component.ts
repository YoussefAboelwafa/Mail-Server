import { HttpErrorResponse } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { service } from '../service';
import { User } from '../user';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {


  email:any;
  pass:any;
  email_pass:any;
  constructor(private router:Router,private ser:service) { }

  ngOnInit(): void {
  }


  get_email(email:string){
    this.email=email;
  }

  get_pass(pass:any){
    this.pass=pass;
  }


  check(){

    if(!this.email.includes("@CSED.com")&&this.pass.length<8){

      alert("1: email must end with @CSED.com \n2:password must have at least 8 character")
    }
    else if(!this.email.includes("@CSED.com")){
      alert(" email must contain @CSED.com")
    }
    else if(this.pass.length<8){
      alert("password must have at least 8 character")
    }
     if(this.email.includes("@CSED.com")&&this.pass.length>=8){

     this.email_pass=this.email+"+"+this.pass;


      this.ser.login(this.email,this.pass).subscribe
      (
        
      (valid)=>{
        if(valid=="1"){
          this.router.navigate([`/home/${this.email}`])
        }
        else if(valid=="0"){
          alert("Invalid email")
        }
        else if(valid=="2"){
          alert("the system is full")
        }
       
        
        error:(error: HttpErrorResponse) =>alert(error.message);

      }

    )  
   }




  }








}
