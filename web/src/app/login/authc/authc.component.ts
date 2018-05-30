import { Component, OnInit } from '@angular/core';
import {FormGroup, FormBuilder, FormControl} from "@angular/forms";
import {LoginService} from "../../service/login/login.service";
@Component({
  selector: 'app-authc',
  templateUrl: './authc.component.html',
  styleUrls: ['./authc.component.css'],
  providers:[LoginService],
})
export class AuthcComponent implements OnInit {

  constructor(private fb:FormBuilder,private loginService:LoginService) { }
  // form:FormGroup;
  form: FormGroup = this.fb.group({

  });
  ngOnInit() {
    this.form.addControl('userName', new FormControl(''));
    this.form.addControl('passWord', new FormControl(''));
  }
  create(){
    console.log(this.form);
    this.loginService.save(this.form.value).then(res => {
      // this.back();
    }).catch(err => {

    });
  }
}
