import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginsusComponent} from "./loginsus/loginsus.component";
const routes: Routes = [
  {path: '',
    component: LoginsusComponent,
    data: {title: '登录成功'},
    children: [
      // {path: 'sus', data: {title: '登录成'}, component: LoginsusComponent},
    ]
  },
];
@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class LoginRoutingModule { }
