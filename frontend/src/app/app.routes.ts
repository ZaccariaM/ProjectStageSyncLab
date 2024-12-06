import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { LoginComponent } from './component/login/login.component';
import { ChatComponent } from './component/chat/chat.component';
import { RegistrationComponent } from './component/registration/registration.component';

export const routes: Routes = [
    {path: 'chat',component: ChatComponent},
    {path: 'login', component: LoginComponent},
    {path: 'new', component: RegistrationComponent},
    {path: '**', redirectTo: 'login'}
];

@NgModule({imports: [RouterModule.forRoot(routes)], exports: [RouterModule]})
export class AppRoutingModule {}
