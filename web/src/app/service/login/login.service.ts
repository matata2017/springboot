import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Login} from "../../model/login.model";

@Injectable()
export class LoginService {
  private path = '/rest/user/login';
  constructor(private http: HttpClient) { }

  public save(login: Login): Promise<Login> {
    console.log(login);
    console.log(this.path);
    return this.http.post<Login>(this.path, login).toPromise();
  }
}
