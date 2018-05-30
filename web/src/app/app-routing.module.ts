import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {ExtraOptions, RouterModule, Routes} from '@angular/router';
import {AuthcComponent} from "./login/authc/authc.component";
import {LoginsusComponent} from "./login/loginsus/loginsus.component";

const routes: Routes = [
   //{ path: 'login', loadChildren: 'app/login/login.module#LoginModule', data: {title: '首页'}},
  { path: 'login', component: LoginsusComponent},
  { path: 'auth', component: AuthcComponent},
  { path: '', redirectTo: 'pages', pathMatch: 'full' },
  { path: '**', redirectTo: 'auth' },
];

const config: ExtraOptions = {
  useHash: false,
};

@NgModule({
  imports: [
    CommonModule,
    RouterModule.forRoot(routes, config),
  ],
  exports: [RouterModule],
  declarations: []
})
export class AppRoutingModule { }
