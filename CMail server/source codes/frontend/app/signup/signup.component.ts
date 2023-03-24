import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { service } from '../service';
import { User } from '../user';
@Component({
  selector: 'app-signup',
  templateUrl: './signup.component.html',
  styleUrls: ['./signup.component.css'],
})
export class SignupComponent implements OnInit {
  firstname: any;
  lastname: any;
  birthdate: any;
  email: any;
  pass: any;
  confirm_pass: any;
  phone: any;

  constructor(private router: Router, private ser: service) {}

  ngOnInit(): void {}

  get_first_name(value: any) {
    this.firstname = value;
  }
  get_last_name(value: any) {
    this.lastname = value;
  }
  get_birthdate(value: any) {
    this.birthdate = value;
  }
  get_email(value: any) {
    this.email = value;
  }
  get_pass(value: any) {
    this.pass = value;
  }
  get_confirm_pass(value: any) {
    this.confirm_pass = value;
  }
  get_phone(value: any) {
    this.phone = value;
  }

  check_sign() {
    let value: any;
    let name: any;

    if (
      this.firstname.length == 0 ||
      this.lastname.length == 0 ||
      this.birthdate.length == 0 ||
      this.email.length == 0 ||
      this.pass.length == 0 ||
      this.confirm_pass.length == 0 ||
      this.phone.length == 0
    ) {
      alert('Complete your data please!');
      return;
    } else if (this.pass.length < 8) {
      alert('password must have at least 8 character');
      return;
    } else if (this.pass != this.confirm_pass) {
      alert('confirmed passward must be the same as password');
      return;
    } else if (!this.email.includes('@CSED.com')) {
      alert(' email must contain @CSED.com');
      return;
    }

    name = this.firstname + ' ' + this.lastname;
    value =
      this.email +
      '+' +
      this.pass +
      '+' +
      name +
      '+' +
      this.birthdate +
      '+' +
      this.phone;
    console.log(value);
    let user:User=new User();
    user.first[0]=name;
    user.second=this.birthdate;
    user.third=this.email;
    user.fourth=this.pass;

    this.ser.signup(user).subscribe((valid) => {
      if (valid == '1') {
        this.router.navigate(['/login']);
      } else if (valid == '0') {
        alert('Invalid email');
      }
    });
  }
}
