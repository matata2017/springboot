import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppComponent } from './app.component';
import {AppRoutingModule} from "./app-routing.module";
import {AuthcComponent} from "./login/authc/authc.component";
import {ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {LoginModule} from "./login/login.module";
@NgModule({
  declarations: [
    AppComponent,
    AuthcComponent,
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,
    LoginModule,

  ],
  providers: [

  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
